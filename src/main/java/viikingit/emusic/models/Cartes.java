package viikingit.emusic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cartes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private long NumCarte;

	private String DateExp;

	private String nom;

	private int Cvv;

	@OneToOne
	private Parent user;

}
