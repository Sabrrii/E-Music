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
import viikingit.emusic.models.Parent;
import viikingit.emusic.models.TypeCours;
import viikingit.emusic.repository.ICoursRepository;
import viikingit.emusic.repository.IInscriptionsRepository;
import viikingit.emusic.repository.IInstrumentsRepository;
import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;

@Controller
public class CoursController {

	@Autowired
	private ICoursRepository courRepo;

	@Autowired
	private ITypeCoursReposiroty typecours;

	@Autowired
	private IInstrumentsRepository instruments;

	@Autowired
	IParentRepository parRepo;

	@Autowired
	IInscriptionsRepository insRepo;


	@GetMapping("listCours")
	public String listCours(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		Iterable<Cours> cours = courRepo.findAll();
		model.put("cours", cours);
		model.put("type_cours", typecours.findAll());
		model.put("instruments", instruments.findAll());
		model.put("userCo", authUser);
		return "cours/list_cours";
	}

	@GetMapping("newCours")
	public String newCours(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		model.addAttribute("cours", new Cours());
		model.addAttribute("type_cours", typecours.findAll());
		model.addAttribute("instruments", instruments.findAll());
		model.put("userCo", authUser);
		return "cours/formNewCours";
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
		model.put("type_cours", typecours.findAll());
		model.put("instruments", instruments.findAll());
		return "cours/formEditCours";
	}

	@PostMapping("editCours/{id}")
	public RedirectView coursEdited(@ModelAttribute Cours cour, @PathVariable int id) {
		Cours toSave = courRepo.findById(id).get();
		toSave = cour;
		courRepo.save(toSave);
		return new RedirectView("/listCours");
	}

	@GetMapping("delete/{id}")
	public RedirectView deleteAction(@PathVariable int id, RedirectAttributes attrs) {
		courRepo.findById(id);
		courRepo.deleteById(id);
		return new RedirectView("/listCours");
	}

	@GetMapping("emploi_du_temps")
	public String show_edt(@AuthenticationPrincipal Parent currentUser) {
		return "cours/edt";
	}

}
