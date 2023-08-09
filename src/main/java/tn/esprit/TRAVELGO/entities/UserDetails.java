package tn.esprit.TRAVELGO.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name = "user_details")
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String username;

    @Column(name = "email", columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(12)", nullable = false)
    private String phoneNumberUser;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
