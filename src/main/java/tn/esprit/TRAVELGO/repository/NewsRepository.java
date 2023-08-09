package tn.esprit.TRAVELGO.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.TRAVELGO.entities.News;
import tn.esprit.TRAVELGO.entities.User;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findByTitle(String title);

}

