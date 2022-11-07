package viikingit.emusic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IParentRepository;

public class DbUserLoginService implements UserDetailsService, UserDetails{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private IParentRepository parRepo;
	
  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Parent> opt =  parRepo.findByUsername(username);
       if(opt.isPresent()) {
    	   return opt.get();
       }
       throw new UsernameNotFoundException(username + "n'existe pas"); 
    }
  
  
    
    public Parent createUser(Parent parent) {
        Parent u = new Parent();
        u.setUsername(parent.getUsername());
        u.setPassword(passwordEncoder.encode(parent.getPassword())); // (3)
        return parRepo.save(u);
    }
    
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


}
