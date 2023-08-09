package tn.esprit.TRAVELGO.service;


import tn.esprit.TRAVELGO.entities.Liking_per_comments;

import java.util.List;




public interface ILiking_per_comments_Service {

	
	public Boolean addLiking1(Liking_per_comments like, long idUser, long idComment);
	public void deleteLiking1(long id);
	
	public List<Liking_per_comments>getLikingsByCommentId(long id);
	public List<Liking_per_comments>getLikingsByUserId1(long id);
	
	public boolean isLikeExists1(long idUser, long idComment);
	
	public int countLikingByComment(int id);
	
	
	

}
