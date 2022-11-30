package viikingit.emusic.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cours {

	public Cours() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String libelle;
	private int ageMini;
	private int ageMaxi;
	private int nbPlaces;
	private String jour;
	private int heure;
	private int duree = 1;/* en heure */

	@ManyToOne
	private TypeCours typeCour;

	@ManyToOne
	private Instruments instruments;

	@OneToMany(mappedBy = "cours")
	private List<Inscriptions> inscrit;
}
