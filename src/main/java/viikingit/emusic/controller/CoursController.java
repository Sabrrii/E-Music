package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Cours;
import viikingit.emusic.models.Parent;
import viikingit.emusic.models.TypeCours;
import viikingit.emusic.repository.ICoursRepository;
import viikingit.emusic.repository.IInstrumentsRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;

@Controller
public class CoursController {

	@Autowired
	private ICoursRepository courRepo;

	@Autowired
	private ITypeCoursReposiroty typecours;

	@Autowired
	private IInstrumentsRepository instruments;

	@GetMapping("newCours")
	public String newCours(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		model.addAttribute("cours", new Cours());
		model.addAttribute("type_cours", typecours.findAll());
		model.addAttribute("instrument", instruments.findAll());
		model.put("userCo", authUser);
		return "cours/formNewCours";
	}

	@GetMapping("myLesson")
	public String myLesson(ModelMap model, @ModelAttribute Parent parent, @AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "cours/myLesson";
	}

	@GetMapping("listCours")
	public String listCours(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		Iterable<Cours> cours = courRepo.findAll();
		model.put("cours", cours);
		model.put("type_cours", typecours.findAll());
		model.put("userCo", authUser);
		return "cours/list_cours";
	}

	@PostMapping("newCours")
	public RedirectView newSubmitCours(@ModelAttribute Cours cours, @ModelAttribute TypeCours typecours) {
		courRepo.save(cours);
		return new RedirectView("/");
	}

	@GetMapping("instruments")
	public String AllerAInstruments(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "cours/list_instruments";
	}
}
