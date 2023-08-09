package tn.esprit.TRAVELGO.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity

public class Liking_per_posts  implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReact;
	
	
	 @JoinColumn
	@ManyToOne
	User users;
	
	@JsonIgnore

	
	
	@JoinColumn
	@ManyToOne
	Post posts;
	
	
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

	public Post getPosts() {
		return posts;
	}

	public void setPosts(Post posts) {
		this.posts = posts;
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

	public Liking_per_posts(Long idReact, User users, Post posts, LocalDateTime reactDate,
			ReactType reactType) {
		super();
		this.idReact = idReact;
		this.users = users;
		this.posts = posts;
		this.reactDate = reactDate;
		this.reactType = reactType;
	}

	public Liking_per_posts() {
		super();
	}
	


}
