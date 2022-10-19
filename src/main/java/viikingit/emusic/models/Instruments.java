package viikingit.emusic.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	@ManyToMany
	private List<Cours> cour;
}
