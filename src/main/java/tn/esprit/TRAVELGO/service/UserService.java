package tn.esprit.TRAVELGO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.TRAVELGO.entities.*;
import tn.esprit.TRAVELGO.message.ResponseMessage;
import tn.esprit.TRAVELGO.repository.BusinessRepository;
import tn.esprit.TRAVELGO.repository.ConfirmationTokenRepository;
import tn.esprit.TRAVELGO.repository.RoleRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService implements IUserservice, UserDetailsService {

    @Autowired
    UserRepository ur ;
    @Autowired
    RoleRepository rr;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
     ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
     EmailService emailService;
    @Autowired
    BusinessRepository businessrepository;
    @Override


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = ur.findByUsername(username);
       if(user==null){
           log.info("user noy found");
           throw new UsernameNotFoundException("user not found");
       }else {
           log.info("username :{}",username);
       }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       user.getRoles().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }

    /************************************----- company --------*********************************************
     * @return*******************/
    //------------------- company wil create account -------------------
    public ResponseEntity<ResponseMessage> registerCompany(User user) {
        User existingUser = ur.findByUsername(user.getUsername());
        if(existingUser != null)
        {log.info("This username already exists!");}
        else
        {
            Role roleCompany = new Role("ROLE_COMPANY");
            rr.save(roleCompany);
            user.getRoles().add(roleCompany);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (ur.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.badRequest().body(new ResponseMessage("Error: Email is already taken!"));
            }
            ur.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Confirm Registration!");
            mailMessage.setFrom("ahmed.zarrai@esprit.tn");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8089/api/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);
            log.info("successfulRegisteration");
        }
        return null;
    }
    //------------------- active company account -------------------
    public void confirmUserAccountCompany(String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null)
        {
            User user = ur.findByUsername(token.getUserEntity().getUsername());
            user.setEnable(true);
            ur.save(user);
            log.info("accountVerified");
        }
        else
        {
            log.info("The link is invalid or broken!");
        }
    }

    //-------- send invitation  to employee ---------------//
    @Override
    public ResponseEntity<ResponseMessage> saveUser(User user) {
        User existingUser = ur.findByUsername(user.getUsername());
        User user1 = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user1= ur.findByUsername(username);
        } else {
            String username = principal.toString();
            user1= ur.findByUsername(username);
        }
        if(existingUser != null)
        {
            log.info("This email already exists!");
        }else {
            Role role = new Role("ROLE_USER");
            rr.save(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEmail(user.getEmail());
            user.getRoles().add(role);
            user.setIdCompany(user1.getId());

            if  (ur.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.badRequest().body(new ResponseMessage("Error: Email is already taken!"));
            }

            ur.save(user);
            log.info("saving new user");


            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Activé compte");
            mailMessage.setFrom("ahmed.zarrai@esprit.tn");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8089/api/active-account?token="+confirmationToken.getConfirmationToken()+"---------------------------" +
                    ""+"username "+":"+user.getUsername()+"----------"+"password "+":"+user.getPassword());
            emailService.sendEmail(mailMessage);

            SimpleMailMessage mailMessage1 = new SimpleMailMessage();
            mailMessage1.setTo(user1.getEmail());
            mailMessage1.setSubject("Invitation Status");
            mailMessage1.setFrom("ahmed.zarrai@esprit.tn");
            mailMessage1.setText("your invitation has been successfully send to  : "+user.getEmail());
            emailService.sendEmail(mailMessage1);
        }
        return null;
    }




    //---------company will  add new role ----------------
    @Override
    public Role saveRole(Role role) {
        log.info("saving new role");
        return rr.save(role);
    }

    // --------campany will add role to user ------------
    @Override
    public void addRoleToUser(String username, String name) {
        log.info("add role to usser");
        User user = ur.findByUsername(username);
        Role role = rr.findByName(name);
        user.getRoles().add(role);
        ur.save(user);

    }

    // ------------campany get user by username --------------------
    @Override
    public User getUser(String username) {
        log.info("get user");
        return ur.findByUsername(username);
    }

    // ------------campany get all user by username sexeUser adressUser  Email----------------
    @Override
    public List<User> getUsers() {
        log.info("get users");
        return ur.findAll();
    }

    @Override
    public List<User> retrieveUserBySexe(SexeType sexeUser) {
        return ur.findBySexeUser(sexeUser);
    }

    @Override
    public List<User> retrieveUserByAdress(String adressUser) {
        return ur.findByAdressUser(adressUser);
    }


    @Override
    public User findByEmail(String email) {
        return ur.findByEmail(email);
    }

    @Override
    public List<String> findByUserCompany(Long  id) {

        return ur.findByUserCompany(id);
    }



    /**********************------employee area-------*****************/

    /********* ------------ app will active account empolyee -------------*/

    public void activeUserAccount(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null)
       {
            User user = ur.findByUsername(token.getUserEntity().getUsername());
            user.setEnable(true);
            ur.save(user);
            log.info("accountVerified");
       }
       else {log.info("The link is invalid or broken!");}
    }



    /****************************** REST PASSWORD ********************************/

    /*****----------------------Demande To Rest PWD-----------------------*******/
    public void demandToRestPassword(@PathVariable("name") String username){
        User user = ur.findByUsername(username);
        if(user !=null){
            ConfirmationToken restPwdToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(restPwdToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("rest password ! ");
            mailMessage.setFrom("ahmed.zarrai@esprit.tn");
            mailMessage.setText("To rest your password, please click here : "
                    +"http://localhost:8089/api/confirm-password?token="+ restPwdToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);
            log.info("/rest-password sended");
        } else {log.info("does not exist");}
    }
  /*******----------------------REST PWD --------------------------------*********/
    public void RestPassword(String confirmationToken, String NewPassword ,String ConfirmPassword ){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token!=null){
            User user = token.getUserEntity();

            user.setPassword(passwordEncoder.encode(NewPassword));
            ur.save(user);
            log.info("done!");
        }
        else {log.info("The link is invalid or broken!");}
    }

    /****************************** REST PASSWORD ********************************/



                         /*************************** Scheduler *********************************/
/*
 @Scheduled(fixedRate = 60000)
 public  void reminderEmployeesToActiveAccount(){
     List<User> users = ur.findAll();
     List<User> usersdisable = new ArrayList<>();
     usersdisable = users.stream().filter(e->!e.isEnable()).collect(Collectors.toList());
     for (User u: usersdisable) {
         log.info("{}",u.getUsername());
         ConfirmationToken restPwdToken = new ConfirmationToken(u);
         confirmationTokenRepository.save(restPwdToken);
         SimpleMailMessage mailMessage = new SimpleMailMessage();
         mailMessage.setTo(u.getEmail());
         mailMessage.setSubject("reminder to confirm account ! ");
         mailMessage.setFrom("ahmed.zarrai@esprit.tn");
         mailMessage.setText("To confirm your account, please click here : "
                 +"http://localhost:8089/api/active-account?token="+ restPwdToken.getConfirmationToken());
         emailService.sendEmail(mailMessage);
         log.info("/rest-password sended");
     }
 }*/
                              /*************************** Scheduler *********************************/







    /******************************************************WISSEM**************************************************
     * @return****************************************/
    @Override
    public Business retrieveBusiness(Long id) {
        Business business=businessrepository.findById(id).get();
        return  business;
    }
    @Override
    public void Acceptation(Long id) {
        Business business=businessrepository.findById(id).get();
        business.setAcceptation(true);

        businessrepository.save(business);
    }
    @Override
    public void Rejection(Long id) {
        Business business=businessrepository.findById(id).get();
        business.setAcceptation(false);
        businessrepository.save(business);
    }
    /******************************************************WISSEM****************************************/



    /******************************************************lotfi****************************************/
    public User findUserById(Long id) {return ur.findById(id).orElse(null);}
    public String getUserByUsername(String username){return ur.findByUsername(username).getUsername();}
    public User isUsernameUnique(String usr){
        return ur.findByUsername(usr);
    }
    public boolean isUserIdValid(Long userId){
        return ur.existsById(userId);
    }
    public List<User>getAllUsers(){return (List<User>) ur.findAll();};
    /******************************************************lotfi****************************************/

}
