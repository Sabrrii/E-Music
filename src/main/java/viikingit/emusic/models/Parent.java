package viikingit.emusic.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Parent implements UserDetails {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String nom;

	private String prenom;
	private String password;

	private String adresse;
	private String ville;
	private String username;
	private int quotientFamilial;
	private String tel1;
	private String tel2;

	@OneToMany(mappedBy = "parent")
	private List<Enfant> enfants = new ArrayList<>();

	@OneToOne(mappedBy = "user")
	private Cartes carte;
	
	
	@OneToMany(mappedBy = "parent")
	private List<Inscriptions> inscriptions;
	

	public void addKid(Enfant enf) {
		Enfant temp = enf;
		if(!enfants.contains(temp)) {
			temp.setParent(this);
			enfants.add(temp);
		}
	}


	private String authorities = "PARENT";



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] auths = authorities.split(",");
		List<SimpleGrantedAuthority> authoritiesObjects = new ArrayList<SimpleGrantedAuthority>();
		for (String parent : auths) {
			authoritiesObjects.add(new SimpleGrantedAuthority("ROLE_" + parent));
		}
		return authoritiesObjects; // (3)
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
