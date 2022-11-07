package viikingit.emusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import viikingit.emusic.models.Cours;

@Controller
public class CoursController {

	@GetMapping("newCours")
	public String newCours(ModelMap model) {
		model.addAttribute("cours", new Cours());
		return "cours/formNewCours";
	}

}
