package viikingit.emusic.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@AllArgsConstructor
@NoArgsConstructor
public class Enfant implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String username;
	private String password;

	@Column(length = 30)
	private String nom;

	@Column(length = 30)
	private String prenom;

	private int age;

	@ManyToOne()
	private Parent parent;

	@OneToMany(mappedBy = "enfant")
	private List<Inscriptions> inscriptions;

	private String authorities = "ENFANT";

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] auths = authorities.split(",");
		List<SimpleGrantedAuthority> authoritiesObjects = new ArrayList<SimpleGrantedAuthority>();
		for (String enfant : auths) {
			authoritiesObjects.add(new SimpleGrantedAuthority("ROLE_" + enfant));
		}
		return authoritiesObjects; // (3)
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
