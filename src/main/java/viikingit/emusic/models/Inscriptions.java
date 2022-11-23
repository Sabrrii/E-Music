package viikingit.emusic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inscriptions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int nombre_de_paiements;

	@ManyToOne
	private Enfant enfant;

	@ManyToOne
	private Cours cours;
	
	@ManyToOne 
	private Parent parent;

}
