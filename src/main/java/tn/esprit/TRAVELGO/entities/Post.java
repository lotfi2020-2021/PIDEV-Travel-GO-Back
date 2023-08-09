package tn.esprit.TRAVELGO.entities;

import lombok.*;
import tn.esprit.TRAVELGO.model.FileDB;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Size(max = 100)
    @Column(unique = true)
    private String title;
     Long idUser;

    @Size(max = 250)
    private String description;

   
    @OneToMany(cascade = CascadeType.ALL, mappedBy="posts")
	private List <Liking_per_posts> likings;
    
    @Lob
    private String content;
    @ManyToOne
    private User users;
    
    private int views ;


    
    public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@ManyToOne
    private FileDB file;
 
    

	


    
    
    
    
    
    
}