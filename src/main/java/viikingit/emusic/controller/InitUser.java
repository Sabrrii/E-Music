package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;

import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IEnfantRepository;
import viikingit.emusic.repository.IParentRepository;

@Controller
public class InitUser {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IParentRepository parRepo;
	
	@Autowired
	private IEnfantRepository enfRepo;
	
	@GetMapping("init/{cParent}/{cEnfant}")
	public @ResponseBody String initAction(
			@PathVariable int cParent, @PathVariable int cEnfant) {
		for(int o = 0; o < cParent; o++) {
			Parent par = new Parent();
			Faker fake = new Faker();
			par.setNom(fake.name().lastName());
			par.setPrenom(fake.name().firstName());
			par.setUsername(par.getNom()+"."+par.getPrenom()+"@gmail.fr");
			par.setPassword(passwordEncoder.encode("0000"));
			par.setQuotientFamilial(fake.number().randomDigit());
			par.setTel1(fake.phoneNumber().phoneNumber());
			par.setVille(fake.address().cityName());
			par.setAdresse(fake.address().fullAddress());
			par.setAuthorities("PARENT");
			parRepo.save(par);
			 for (int g = 0; g < cEnfant; g++) {
				 Enfant enf = new Enfant();
				 enf.setNom(fake.name().lastName());
				 enf.setPrenom(fake.name().firstName());
				 enf.setUsername(enf.getPrenom()+"."+enf.getNom());
				 enf.setPassword(passwordEncoder.encode("0000"));
				 enf.setAuthorities("ENFANT");
				 enf.setAge(fake.number().numberBetween(5, 17));
				 enf.setParent(par);
				 enfRepo.save(enf);
			 }
		}
		return "init ok";
	}
}
