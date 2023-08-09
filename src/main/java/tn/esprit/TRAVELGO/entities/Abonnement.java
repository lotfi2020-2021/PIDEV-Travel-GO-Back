package tn.esprit.TRAVELGO.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString

@AllArgsConstructor
@Table(name = "Abonnements")
public class Abonnement {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	Long idUser;
    @ManyToOne
    private User folowers;
    @ManyToOne
    private User followed;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getFolowers() {
		return folowers;
	}
	public void setFolowers(User folowers) {
		this.folowers = folowers;
	}
	public User getFollowed() {
		return followed;
	}
	public void setFollowed(User followed) {
		this.followed = followed;
	}
	public Abonnement(Long id, User folowers, User followed) {
		super();
		this.id = id;
		this.folowers = folowers;
		this.followed = followed;
	}
	
	public Abonnement() {
		super();
		
	}
	
	
	
	
	

    
}