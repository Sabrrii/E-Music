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
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Cartes;
import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.ICartesRepository;
import viikingit.emusic.repository.IParentRepository;

@Controller
public class CartesControlleur {

	@Autowired
	private ICartesRepository carteRepo;

	@Autowired
	private IParentRepository parentRepo;

	@GetMapping("formCarte")
	public String formCarteLog() {
		return "/user/FormCartes";
	}

	@PostMapping("formCarte")
	public RedirectView addCarte(@AuthenticationPrincipal Parent authUSer, @ModelAttribute Cartes carte) {
		carte.setUser(authUSer);
		carteRepo.save(carte);

		return new RedirectView("/");
	}

	@GetMapping("edit/{id}")
	public String editCarte(ModelMap model, @PathVariable int id) {
		Optional<Cartes> carte = carteRepo.findById(id);
		model.put("carte", carte.get());
		return "/user/editCarte";
	}

	@PostMapping("editCarte")
	public RedirectView updateCard(@PathVariable int id, @ModelAttribute Cartes carte) {
		carteRepo.save(carte);
		return new RedirectView("/");
	}
}
