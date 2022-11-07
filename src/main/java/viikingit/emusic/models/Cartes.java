package viikingit.emusic.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cartes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int NumCarte;

	private String DateExp;

	private String nom;

	private int Cvv;
}
