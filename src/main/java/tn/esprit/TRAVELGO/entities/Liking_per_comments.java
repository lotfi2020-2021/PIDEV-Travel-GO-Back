package tn.esprit.TRAVELGO.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity

public class Liking_per_comments  implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReact;
	

	@JoinColumn(name = "user_id")
	@ManyToOne
	User users;
	
	

	@JoinColumn(name = "comment_id")
	@ManyToOne
	Comment comments;
	
	
	private LocalDateTime reactDate;
	
	@Enumerated(EnumType.STRING)
	private ReactType reactType;

	public Long getIdReact() {
		return idReact;
	}

	public void setIdReact(Long idReact) {
		this.idReact = idReact;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	


	public Comment getComments() {
		return comments;
	}

	public void setComments(Comment comments) {
		this.comments = comments;
	}

	public LocalDateTime getReactDate() {
		return reactDate;
	}

	public void setReactDate(LocalDateTime reactDate) {
		this.reactDate = reactDate;
	}

	public ReactType getReactType() {
		return reactType;
	}

	public void setReactType(ReactType reactType) {
		this.reactType = reactType;
	}

	public Liking_per_comments(Long idReact, User users, Comment comments, LocalDateTime reactDate,
			ReactType reactType) {
		super();
		this.idReact = idReact;
		this.users = users;
		this.comments = comments;
		this.reactDate = reactDate;
		this.reactType = reactType;
	}

	public Liking_per_comments() {
		super();
	}
	


}
