package viikingit.emusic.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends Parent {

	private String authorities = "ADMIN";

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] auths = authorities.split(",");
		List<SimpleGrantedAuthority> authoritiesObjects = new ArrayList<SimpleGrantedAuthority>();
		for (String admin : auths) {
			authoritiesObjects.add(new SimpleGrantedAuthority("ROLE_" + admin));
		}
		return authoritiesObjects; // (3)
	}

}
