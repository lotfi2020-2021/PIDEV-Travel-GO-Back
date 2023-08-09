package tn.esprit.TRAVELGO.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.esprit.TRAVELGO.entities.News;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.model.FileDB;
import tn.esprit.TRAVELGO.repository.FileDBRepository;
import tn.esprit.TRAVELGO.repository.NewsRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class NewsServise implements  INewsService{
    @Autowired
    UserRepository ur ;
    @Autowired
    NewsRepository nr;
    @Autowired
    EmailService emailService;
    @Autowired
    private FileDBRepository fileDBRepository;
    @Override
    public News saveNews(News news,User user,String id) {
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
            news.setIdCompany(user1.getId());
            FileDB file = fileDBRepository.findById(id).orElse(null);
            news.setFile(file);
            nr.save(news);
            log.info("saving new news");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user1.getEmail());
            mailMessage.setSubject("publication");
            mailMessage.setFrom("ahmed.zarrai@esprit.tn");
            mailMessage.setText("your publication has been successfully uploaded :");
            emailService.sendEmail(mailMessage);
        }
        return news;
    }

    @Override
    public List<News> getNewss() {
        log.info("get newss");
        return (List<News>) nr.findAll();
    }

    @Override
    public List<News> retrieveNewsByTitle(String title) {
        return nr.findByTitle(title);
    }






}
