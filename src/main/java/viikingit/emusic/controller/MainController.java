package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.service.DbUserLoginService;

@Controller
public class MainController {

	@Autowired
	private IParentRepository parentRepo;

	@Autowired
	private UserDetailsService uService;

	@GetMapping("")
	public String index() {
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
		return new RedirectView("");
	}

	@GetMapping("login")
	public String login() {
		return "/user/login";
	}

	/*
	 * @PostMapping("login") public log() {
	 * 
	 * }
	 */

}
