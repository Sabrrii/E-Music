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
@RequiredArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Instruments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Exclude
	private int id;

	@NonNull
	private String intitule;

	@Exclude
	@OneToMany(mappedBy = "instruments")
	private List<Cours> iCours;

}
