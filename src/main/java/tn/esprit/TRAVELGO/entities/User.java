package tn.esprit.TRAVELGO.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String username;
    private String password;
    private String companyName;
    private String email;
    private String localisationCompany;
    private String adressUser;
    private int phoneNumberUser;
    private boolean isEnable;
    Long idCompany;
    @Enumerated(EnumType.STRING)
    private SexeType sexeUser;
    @ManyToMany(fetch = FetchType.EAGER )
    private Collection<Role> roles = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ImageUser> ImageUser1;

    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;

    /******************************************************WISSEM****************************************/
    @OneToMany(mappedBy="user")
    List<Business> businesses;
    @OneToOne
    Salary salary ;
    @OneToOne
    Warn warn;
    /******************************************************WISSEM****************************************/


    /******************************************************WAEL****************************************/
    @ManyToMany
    List<Reunion> reunions;
    @OneToMany (mappedBy="user")
    List<Reunion> ReunionsCompany;
    @ManyToOne
    Domain domain;
    @ManyToOne
    Profession profession;
    @OneToMany(mappedBy="user")
    List<Formation> formations;
    /******************************************************WAEL******************************************/

    /******************************************************lotfi******************************************/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="users")
    private List<Post> posts;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="followed")
    private List<User> followed;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="followers")
    private List<User> followers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="users")
    private List<Comment> Comments;


    /******************************************************lotfi******************************************/
}
