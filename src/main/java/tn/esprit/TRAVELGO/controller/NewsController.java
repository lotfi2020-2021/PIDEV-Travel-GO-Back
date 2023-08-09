package tn.esprit.TRAVELGO.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.TRAVELGO.entities.News;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.ImageNewsRepository;
import tn.esprit.TRAVELGO.repository.NewsRepository;
import tn.esprit.TRAVELGO.service.NewsServise;
import java.net.URI;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    NewsServise nu ;
    @Autowired
    NewsRepository nr ;
    @Autowired
    ImageNewsRepository iurx;
    @Autowired
    NewsRepository ur;


    /**********************************************************************************/
    @GetMapping("/company/newss")
    public ResponseEntity<List<News>>getUsers(){
        return ResponseEntity.ok().body(nu.getNewss());
    }
    /**********************************************************************************/
    @PostMapping("/company/saveNews/{file_id}")
    public ResponseEntity<News>saveNews(@RequestBody News news, User user, @PathVariable(value = "file_id") String id){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/news/save").toUriString());
        return ResponseEntity.created(uri).body(nu.saveNews(news,user,id));
    }
    /**********************************************************************************/
    @DeleteMapping("/company/delete/{idNe}")
    public ResponseEntity<?> deleteNews(@PathVariable(value = "idNe") Long idNe) {

        return nr.findById(idNe).map(news -> {
            nr.delete(news);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException(" News not found with id " + idNe));
    }
    /**********************************************************************************/
@PutMapping("/company/update/{idNe}")
	public News updateNews( @PathVariable(value = "idNe") Long idNe,@RequestBody News newsRequest) {
		return nr.findById(idNe).map(news -> {
			news.setDescriptionNews(newsRequest.getDescriptionNews());
            news.setTitle (newsRequest.getTitle());
			return nr.save(news);
		}).orElseThrow(() -> new IllegalArgumentException("idNe "  + "not found"));
	}

    /**********************************************************************************/
    /**
     * @return********************************************************************************/

    @GetMapping("/retrieve-news-by-title/{news-title}")
    @ResponseBody
    public List<News> retrieveUserByTitle(@PathVariable("news-title") String title) {
        return nu.retrieveNewsByTitle(title);
    }


}
