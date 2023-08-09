package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Business;
import tn.esprit.TRAVELGO.entities.Comment;
import tn.esprit.TRAVELGO.entities.User;

public interface IcommentService {

    Comment addCommentt(Comment c, User user,Long id);
    Comment addCommentt2(Comment c, User user,Long id,Long idc);
}
