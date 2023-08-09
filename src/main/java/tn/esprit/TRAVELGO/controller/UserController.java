package tn.esprit.TRAVELGO.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.Role;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.payload.request.LoginRequest;
import tn.esprit.TRAVELGO.payload.response.JwtResponse;
import tn.esprit.TRAVELGO.repository.ConfirmationTokenRepository;
import tn.esprit.TRAVELGO.repository.RoleRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import tn.esprit.TRAVELGO.service.EmailService;
import tn.esprit.TRAVELGO.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import tn.esprit.TRAVELGO.security.jwt.JwtUtils;
import tn.esprit.TRAVELGO.security.services.UserDetailsImpl;


@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

    /****************************************************/
    @Autowired
    UserService us ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RoleRepository rr;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;



    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /******************************************************-----AHMED-----************************************************************************************/

    
    @GetMapping("/users")
    public List<User> getAllUsers( ) {
        return userRepository.findAll();
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    /********************************----crud user -----*********************/

    @GetMapping("/company/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(us.getUsers());
    }


        @PostMapping("/company/save")
        @PreAuthorize("hasRole('ROLE_COMPANY')")
        public void SaveUser(@RequestBody User user) {
            us.saveUser(user);
        }


    @PostMapping("/company/Role/save")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<Role>SaveRole(@RequestBody Role role){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(us.saveRole(role));

    }

    @PostMapping("/company/Role/addtouser")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        us.addRoleToUser(form.getUsername(),form.getName());
        return ResponseEntity.ok().build();
    }
    /********************************----refresh Token-----*********************/
@GetMapping("/token/refresh")
    public void refreshtoken(HttpServletRequest request , HttpServletResponse response) throws IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        try {
            String refrech_token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refrech_token);
            String username = decodedJWT.getSubject();
            User user = us.getUser(username);
            String access_token = JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
            Map<String ,String> tokens= new HashMap<>();
            tokens.put("access_token",access_token);
            tokens.put("refrech_token",refrech_token);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),tokens);
        } catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error", exception.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    } else {
        throw new RuntimeException("Refressh token failed");
    }
}
/********************************----register as company-----*********************/
    @PostMapping("/register")
    public void registerCompany(@RequestBody User user) {
        us.registerCompany(user);
    }

    @GetMapping("/confirm-account")
    public void confirmUserAccountCompany( @RequestParam("token")String confirmationToken) {
        us.confirmUserAccountCompany(confirmationToken);
    }
/**********************************************************************************/

@GetMapping("/active-account")
public void activeUserAccount( @RequestParam("token")String confirmationToken) {
    us.activeUserAccount(confirmationToken);
}
    /******************************REST PASSWORD ********************************/
    @GetMapping("/rest-password/{name}")
    public void demandToRestPassword(@PathVariable("name") String username){
       us.demandToRestPassword(username);
    }
    @GetMapping("/confirm-password/{new}/{confirm}")
    public void RestPassword( @RequestParam("token")String confirmationToken, @PathVariable("new") String NewPassword ,@PathVariable("confirm") String ConfirmPassword ){
       us.RestPassword(confirmationToken,NewPassword,ConfirmPassword);
    }
/********************************************************************************************/



    @GetMapping("/retrieve-user-by-sexe/{user-sexe}")
    @ResponseBody
    public List<User> retrieveUserBySexe(@PathVariable("user-sexe") tn.esprit.TRAVELGO.entities.SexeType sexeUser) {
        return us.retrieveUserBySexe(sexeUser);
    }
    @GetMapping("/retrieve-user-by-adress/{user-adress}")
    @ResponseBody
    public List<User> retrieveUserByAdress(@PathVariable("user-adress") String adressUser) {
        return us.retrieveUserByAdress(adressUser);
    }

    @GetMapping("/admin/number-women")
    public float nomberWomen() throws Exception {
        return userRepository.getNumberWomen();
    }

    @GetMapping("/admin/number-men")
    public float nomberMen() throws Exception {
        return userRepository.getNumberMen();
    }


    @GetMapping("/admin/number-company")
    public float nomberCompany() throws Exception {
        return rr.getNumberCOMPANY();
    }

    @GetMapping("/admin/number-user")
    public float nomberUser() throws Exception {
        return rr.getNumberUser();
    }

    @GetMapping("/retrieve-user-by-email/{user-email}")
    @ResponseBody
    public User retrieveUserByEmail(@PathVariable("user-email") String Email) {
        return us.findByEmail(Email);
    }


   @GetMapping("/retrieve-user-by-company/{company-name}")
    @ResponseBody
    public List<String> findByUserCompany(@PathVariable("company-name") Long id ) {return us.findByUserCompany(id);
    }


    /**********************************************************************************/
    @PutMapping("/company/update/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User userRequest) {
        return userRepository.findById(id).map(user -> {
            user.setCompanyName(userRequest.getCompanyName());
            user.setAdressUser (userRequest.getAdressUser());
            user.setPhoneNumberUser (userRequest.getPhoneNumberUser());
            user.setSexeUser (userRequest.getSexeUser());
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("idNe "  + "not found"));
    }

    /**********************************************************************************/

    /******************************************************-----AHMED-----************************************************************************************/



    /******************************************************-----WISSEM-----****************************************/
    @PutMapping("/users/accept-business/{idbusi}")
    @ResponseBody
    public void Accept( @PathVariable("idbusi") Long id) {
        us.Acceptation(id);
    }
    @PutMapping("/users/reject-business/{idbusi}")
    @ResponseBody
    public void Reject( @PathVariable("idbusi") Long id) {
        us.Rejection(id);
    }
    /******************************************************-----WISSEM-----****************************************/


}

@Data
class RoleToUserForm{
    private String username;
    private String name;
}
