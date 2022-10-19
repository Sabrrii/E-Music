package viikingit.emusic.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
public class Enfant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 30)
	private String nom;

	@Column(length = 30)
	private String prenom;

	private Date dateNaiss;

	@ManyToOne()
	private Parent parent;

	@OneToMany(mappedBy = "enfant")
	private List<Inscriptions> inscriptions;

	public Enfant() {
		this.nom = "";
		this.prenom = "";
	}

	public Enfant(String nom, String prenom, Date naiss) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = naiss;
	}
}
