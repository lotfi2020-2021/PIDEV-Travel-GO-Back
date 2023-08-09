package tn.esprit.TRAVELGO.entities;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Warn {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idWarn;
	private String text;
	private String destination;
	@OneToOne(mappedBy="warn")
	User user;
	

	
}
