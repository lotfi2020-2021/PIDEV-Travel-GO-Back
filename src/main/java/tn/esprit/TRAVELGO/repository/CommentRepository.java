package tn.esprit.TRAVELGO.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.TRAVELGO.entities.Comment;
import tn.esprit.TRAVELGO.entities.CommentResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	 List<Comment> findByPostId(Long postId);
	    Optional<Comment> findByIdAndPostId(Long id, Long postId);
	    Set<CommentResponse> findByParentCommentIsNullAndPostId(@Param("post_id") Long id);
}