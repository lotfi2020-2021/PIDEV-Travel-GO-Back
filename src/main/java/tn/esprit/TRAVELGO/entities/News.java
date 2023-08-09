package tn.esprit.TRAVELGO.entities;

import lombok.*;
import tn.esprit.TRAVELGO.model.FileDB;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNe ;
    private String descriptionNews ;
    private String title;
    private long idCompany;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ImageNews> ImageNews1;

    @ManyToOne
    private FileDB file;


}
