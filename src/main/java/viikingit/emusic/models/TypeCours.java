package viikingit.emusic.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class TypeCours {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Exclude
	private int id;

	// Le libelle est soit "individuel" ou "collectif"

	@NonNull
	private String libelleType;

	@Exclude
	@OneToMany(mappedBy = "typeCour")
	private List<Cours> tCours;

	@Exclude
	@OneToMany(mappedBy = "typeCours")
	private List<Cout> couts;
}
