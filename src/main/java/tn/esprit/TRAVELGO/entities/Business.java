package tn.esprit.TRAVELGO.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Business {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBus;
	
	private String DescriptionBus;
	@Temporal(TemporalType.DATE)
	private Date StartDateBus;
	@Temporal(TemporalType.DATE)
	private Date EndDateBus;
	private float Budget;
	private boolean Acceptation;
	private long IdCompanyN;
	@ManyToOne
	User user;



	

}
