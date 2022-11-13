package viikingit.emusic.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import viikingit.emusic.models.Cours;
import viikingit.emusic.models.Parent;

@Controller
public class CoursController {

	@GetMapping("newCours")
	public String newCours(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		model.addAttribute("cours", new Cours());
		model.put("userCo", authUser);
		return "cours/formNewCours";
	}
	
	@GetMapping("myLesson")
	public String myLesson(ModelMap model,@ModelAttribute Parent parent,@AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "cours/myLesson";
	}
	
	@GetMapping("listCours")
	public String listCours(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "cours/list_cours";
	}

}
