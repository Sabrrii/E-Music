package viikingit.emusic.models;

import java.util.ArrayList;
import java.util.Date;
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
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nom;

	private String prenom;
	private Date dateNaiss;
	private String adresse;
	private String ville;
	private String email;
	private int quotienFamilial;
	private String tel1;
	private String tel2;

	@OneToMany(mappedBy = "parent")
	private List<Enfant> enfants = new ArrayList<>();

	public Parent(String nom, String prenom, Date naiss, String ville, String email, int qFam, String tel,
			String telbis) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = naiss;
		this.ville = ville;
		this.email = email;
		this.quotienFamilial = qFam;
		this.tel1 = tel;
		this.tel2 = telbis;
	}

}
