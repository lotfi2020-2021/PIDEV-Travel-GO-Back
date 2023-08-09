package tn.esprit.TRAVELGO.service;



import tn.esprit.TRAVELGO.entities.Liking_per_posts;

import java.util.List;



public interface ILiking_per_posts_Service {

	
	public Boolean addLiking(Liking_per_posts like, long idUser, long idPost);
	public void deleteLiking(long id);
	
	public List<Liking_per_posts>getLikingsByPostId(long id);
	public List<Liking_per_posts>getLikingsByUserId(long id);
	
	public boolean isLikeExists(long idUser, long idPost);
	
	public int countLikingByPost(int id);
	
	
	

}
