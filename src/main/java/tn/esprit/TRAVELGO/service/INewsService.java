package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.News;
import tn.esprit.TRAVELGO.entities.User;

import java.util.List;

public interface INewsService {
    News saveNews (News news , User user, String id);
    List<News> getNewss();
    List<News> retrieveNewsByTitle(String title);

}
