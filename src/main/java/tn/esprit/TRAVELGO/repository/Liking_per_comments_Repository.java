package tn.esprit.TRAVELGO.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Liking_per_comments;

import java.util.List;



@Repository
public interface Liking_per_comments_Repository extends CrudRepository<Liking_per_comments,Long>{
	
	
	@Query("SELECT l FROM Liking_per_comments l WHERE l.users.id =:id order by l.reactDate desc")
	public List<Liking_per_comments>getLikesByUserId1(@Param("id") long id);
	
	
	@Query("SELECT l FROM Liking_per_comments l WHERE l.comments.id =:id order by l.reactDate desc")
	public List<Liking_per_comments>getLikesByCommentId(@Param("id") long id);
	
	
	@Query("SELECT COUNT(1) from Liking_per_comments l WHERE l.users.id =:idUser and l.comments.id =:idComment")
	public int isLikeExists1(@Param("idComment") long idComment ,@Param("idUser") long idUser);

}
