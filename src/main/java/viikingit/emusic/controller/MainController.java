package viikingit.emusic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.service.DbUserLoginService;
import viikingit.emusic.service.EmailServiceImpl;

@Controller
public class MainController {

	@Autowired
	private IParentRepository parentRepo;
	
	@Autowired
	private UserDetailsService uService;
	
	@Autowired
	private EmailServiceImpl email;

	@GetMapping("")
	public String index(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		Iterable<Parent> parents =  parentRepo.findAll();
		model.put("userCo", parents);
		return "index";
	}


	@GetMapping("signup")
	public String signUp() {
		return "/user/signUp";
	}

	@PostMapping("signup")
	public RedirectView addParent(@ModelAttribute Parent parent) {
		Parent par = ((DbUserLoginService)uService).createUser(parent);
		parentRepo.save(par);
		email.sendSimpleMessage(parent.getUsername(), "Confirmation d'email", "Ceci est un test");
		return new RedirectView("/");
	}
	
	
	@GetMapping("login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("logout")
	public RedirectView logout() {
		
		return new RedirectView("/");
	}
	

	@GetMapping("cours")
	public String AllerACours() {
		return "cours/list_cours";
	}

}
