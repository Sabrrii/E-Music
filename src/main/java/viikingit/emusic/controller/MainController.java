package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Parent;
import viikingit.emusic.pojo.ActiveUser;
import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.service.DbUserLoginService;
import viikingit.emusic.service.EmailServiceImpl;

@Controller
public class MainController{

	@Autowired
	private IParentRepository parentRepo;

	@Autowired
	private UserDetailsService uService;

	@Autowired
	private EmailServiceImpl email;

	private ActiveUser activeUser=new ActiveUser();

	@GetMapping("")
	public String index(ModelMap model) {
		activeUser.connect(model);
		return "index";
	}

	@GetMapping("signup")
	public String signUp() {
		return "/user/signUp";
	}

	@PostMapping("signup")
	public RedirectView addParent(@ModelAttribute Parent parent) {
		Parent par = ((DbUserLoginService) uService).createUser(parent);
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

}
