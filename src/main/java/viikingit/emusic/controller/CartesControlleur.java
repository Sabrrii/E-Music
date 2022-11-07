package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Cartes;
import viikingit.emusic.repository.ICartesRepository;

@Controller
public class CartesControlleur {

	@Autowired
	private ICartesRepository carteRepo;

	@GetMapping("formCarte")
	public String formCarteLog() {
		return "/user/FormCartes";
	}

	@PostMapping("formCarte")
	public RedirectView addCarte(@ModelAttribute Cartes carte) {
		carteRepo.save(carte);
		return new RedirectView("");
	}
}
