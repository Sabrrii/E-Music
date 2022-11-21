package viikingit.emusic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Cours;
import viikingit.emusic.models.Instruments;
import viikingit.emusic.models.Parent;
import viikingit.emusic.models.TypeCours;
import viikingit.emusic.repository.ICoursRepository;
import viikingit.emusic.repository.IInstrumentsRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;
import viikingit.emusic.service.CheckDataService;

@Controller
public class CoursController {

	@GetMapping("newCours")
	public String newCours(ModelMap model) {
		model.addAttribute("cours", new Cours());
		return "cours/formNewCours";
	}

	@Autowired
	private CheckDataService dataService;

	@Autowired
	private ICoursRepository courRepo;

	@Autowired
	private ITypeCoursReposiroty typecoursRepo;

	@Autowired
	private IInstrumentsRepository instruments;

	@GetMapping("newCours")
	public String newCours(ModelMap model, @AuthenticationPrincipal Parent authUser) {

		Iterable<TypeCours> coursTypes = dataService.checkTypeCours();
		Iterable<Instruments> Instruments = dataService.checkInstrument();

		model.addAttribute("cours", new Cours());
		model.addAttribute("type_cours", coursTypes);
		model.addAttribute("instruments", Instruments);
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
		model.put("type_cours", typecoursRepo.findAll());
		model.put("instruments", instruments.findAll());
		model.put("userCo", authUser);
		return "cours/list_cours";
	}

	@PostMapping("newCours")
	public RedirectView newSubmitCours(@ModelAttribute Cours cours, @ModelAttribute TypeCours typecours) {
		courRepo.save(cours);
		return new RedirectView("/listCours");
	}

	@GetMapping("instruments")
	public String AllerAInstruments(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "cours/list_instruments";
	}

	@GetMapping("editCours/{id}")
	public String formEditCours(ModelMap model, @PathVariable int id) {
		Optional<Cours> cour = courRepo.findById(id);
		cour.ifPresent(cours -> model.put("cours", cours));
		model.put("type_cours", typecoursRepo.findAll());
		return "cours/formEditCours";
	}

	@GetMapping("delete/{id}")
	public RedirectView deleteAction(@PathVariable int id, RedirectAttributes attrs) {
		courRepo.findById(id);
		courRepo.deleteById(id);
		return new RedirectView("/listCours");
	}

}
