package viikingit.emusic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IParentRepository;

public class DbUserLoginService implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private IParentRepository parRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Parent> opt = parRepo.findByUsername(username);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new UsernameNotFoundException(username + "n'existe pas");
	}

	public Parent createUser(Parent parent) {
		Parent u = new Parent();
		u.setUsername(parent.getUsername());
		u.setPassword(passwordEncoder.encode(parent.getPassword()));
		u.setAdresse(parent.getAdresse());
		u.setNom(parent.getNom());
		u.setPrenom(parent.getPrenom());
		u.setQuotientFamilial(parent.getQuotientFamilial());
		u.setTel1(parent.getTel1());// (3)
		u.setTel2(parent.getTel2());// (3)
		u.setVille(parent.getVille());
		return parRepo.save(u);
	}
}
