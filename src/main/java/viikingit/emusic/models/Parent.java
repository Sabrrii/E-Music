package viikingit.emusic.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String nom;

	private String prenom;
	private String password ;

	private String adresse;
	private String ville;
	private String email;
	private int quotientFamilial;
	private String tel1;
	private String tel2;

	@OneToMany(mappedBy = "parent")
	private List<Enfant> enfants = new ArrayList<>();
	

}
