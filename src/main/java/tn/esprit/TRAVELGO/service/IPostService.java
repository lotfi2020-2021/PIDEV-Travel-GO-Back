package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.model.FileDB;

public interface IPostService {
    Post addPost(Post p, User user, String id);
}
