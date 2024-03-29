package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import viikingit.emusic.models.Cartes;
import viikingit.emusic.models.Parent;
import viikingit.emusic.pojo.ActiveUser;
import viikingit.emusic.repository.ICartesRepository;

@Controller
public class CartesControlleur {

	@Autowired
	private ICartesRepository carteRepo;
	private ActiveUser activeUser = new ActiveUser();

	@GetMapping("myCard/{id}")
	public String formCarteLog(ModelMap model) {
		Parent par = activeUser.getActivePar();
		if (par.getCarte() == null) {
			Cartes cb = new Cartes();
			cb.setUser(par);
			par.setCarte(cb);
			carteRepo.save(cb);
		}

		activeUser.connect(model);
		return "/user/FormCartes";
	}

	@PostMapping("myCard/edit")
	public RedirectView addCarte(ModelMap model,@ModelAttribute Cartes carte) {
		Parent authUSer = activeUser.getActivePar();
		int toChange = authUSer.getCarte().getId();
		Cartes toAdd = carte;
		toAdd.setId(toChange);
		toAdd.setUser(authUSer);
		authUSer.setCarte(toAdd);
		carteRepo.save(toAdd);
		return new RedirectView("/");
	}

}
