package viikingit.emusic.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Instruments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String intitule;

	@OneToMany(mappedBy = "instruments")
	private List<Cours> iCours;
}
