package tn.esprit.TRAVELGO.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private long idDomain;
   private String title;
   private String description;
    private long IdCompanyN;

    @OneToMany (mappedBy="domain")
    List<User> users;
}
