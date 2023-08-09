package tn.esprit.TRAVELGO.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.entities.Liking_per_posts;

import java.util.List;


@Repository
public interface Liking_per_posts_Repository extends JpaRepository<Liking_per_posts,Long>{
	
	
	@Query("SELECT l FROM Liking_per_posts l WHERE l.users.id =:id order by l.reactDate desc")
	public List<Liking_per_posts>getLikesByUserId(@Param("id") long id);
	
	
	@Query("SELECT l FROM Liking_per_posts l WHERE l.posts.id =:id order by l.reactDate desc")
	public List<Liking_per_posts>getLikesByPostId(@Param("id") long id);
	
	
	@Query("SELECT COUNT(1) from Liking_per_posts l WHERE l.users.id =:idUser and l.posts.id =:idPost")
	public int isLikeExists(@Param("idPost") long idPost ,@Param("idUser") long idUser);

}
