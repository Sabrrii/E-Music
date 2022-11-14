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
public class TypeCours {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	// Le libelle est soit "individuel" ou "collectif"
	// private enum libelleType {
	// individuel, collectif
	// }

	private String libelleType;

	@OneToMany(mappedBy = "typeCour")
	private List<Cours> tCours;

	@OneToMany(mappedBy = "typeCours")
	private List<Cout> couts;
}
