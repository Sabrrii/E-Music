package viikingit.emusic.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String libelle;
	private int ageMini;
	private int ageMaxi;
	private int nbPlaces;

	@ManyToOne
	private TypeCours typeCour;

	@ManyToMany(mappedBy = "cour")
	private List<Instruments> instrument;

	@OneToMany(mappedBy = "cours")
	private List<Inscriptions> inscrit;
}
