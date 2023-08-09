package tn.esprit.TRAVELGO.service;
import tn.esprit.TRAVELGO.entities.ImageUser;

public interface IImageUserService {
	
	void affectationImageToUser(int idImageUser, long id);
	
	public Iterable<ImageUser> retreiveAllImage();

}
