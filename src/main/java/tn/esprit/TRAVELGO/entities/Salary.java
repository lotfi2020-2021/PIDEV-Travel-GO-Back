package tn.esprit.TRAVELGO.entities;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long idSal;
	private float amount;
	 private float increase;
	 private float reduction;
	 @OneToOne(mappedBy="salary")
	 User user;


	 
}
