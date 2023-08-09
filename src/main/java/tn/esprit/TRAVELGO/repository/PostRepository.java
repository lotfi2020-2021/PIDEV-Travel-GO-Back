package tn.esprit.TRAVELGO.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.TRAVELGO.entities.Post;
import tn.esprit.TRAVELGO.entities.User;

import java.util.List;

import javax.transaction.Transactional;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	
	

	
	
	
	
	
	@Query("SELECT COUNT(p) FROM Post p WHERE p.users.id =:id")
	public int countPostUser(@Param("id")Long postid);
	
	
	@Modifying
	@Transactional   
	@Query("UPDATE Post p SET p.views = :views+1 WHERE p.id=:id")
	public int updateViewCountPost(@Param("views")int view , @Param("id")long id);
	
	@Query("FROM Post a WHERE a.users = :user")
	List<Post> findFollowersPost(User user);
	
	
	
}