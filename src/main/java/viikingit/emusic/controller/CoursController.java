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
import viikingit.emusic.repository.IInstrumentsRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;
import viikingit.emusic.ui.UIMessage;

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
		return new RedirectView("/listCours");
	}

	@GetMapping("instruments")
	public String AllerAInstruments(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "cours/list_instruments";
	}

	/*
	 * Optional<Parent> opt = parRepo.findById(authUser.getId()); opt.ifPresent(orga
	 * -> model.put("parent",orga)); model.put("userCo", authUser);
	 */

	@GetMapping("editCours/{id}")
	public String formEditCours(ModelMap model, @AuthenticationPrincipal Parent authUser, @PathVariable int id) {
		Optional<Cours> cour = courRepo.findById(id);
		cour.ifPresent(orga -> model.put("cours", orga));
		model.put("type_cours", typecours.findAll());
		model.put("userCo", authUser);
		return "cours/formEditCours";
	}

	@PostMapping("editCours/{id}")
	public String edited(ModelMap model, @PathVariable int id) {
		courRepo.findById(id).ifPresent(cours -> model.put("cour", cours));
		return "/listCours";

		// Cours toSave = courRepo.findById(id).get();
		// toSave.setLibelle(cours.getLibelle());
		// toSave.setAgeMaxi(cours.getAgeMaxi());
		// toSave.setAgeMini(cours.getAgeMini());
		// toSave.setNbPlaces(cours.getNbPlaces());
		// toSave.setInstrument(cours.getInstrument());
		// toSave.setTypeCour(cours.getTypeCour());
		// courRepo.save(toSave);
		// return new RedirectView("/listCours");
	}

	@GetMapping("delete/{id}")
	public RedirectView deleteAction(@PathVariable int id, RedirectAttributes attrs) {
		Optional<Cours> opt = courRepo.findById(id);
		if (opt.isPresent()) {
			attrs.addFlashAttribute("msg",
					UIMessage.errorConfirmation("Suppression",
							"Voulez-vous supprimer <strong>`" + opt.get() + "`</strong>", "/cours/delete/force/" + id,
							"/cours"));
		}
		return new RedirectView("/listCours");

	}
}
