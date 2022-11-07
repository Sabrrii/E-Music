package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IParentRepository;

@Controller
public class MainController {

	@Autowired
	private IParentRepository parentRepo;

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
		parentRepo.save(parent);
		return new RedirectView("");
	}
	
	@GetMapping("login")
	public String login() {
		return "/user/login";
	}
	
	/*@PostMapping("login")
	public  log() {
		
	}*/

	@GetMapping("cours")
	public String AllerACours() {
		return "cours/list_cours";
	}

}
