package viikingit.emusic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import io.github.jeemv.springboot.vuejs.VueJS;
import viikingit.emusic.models.Cartes;
import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Parent;
import viikingit.emusic.repository.IEnfantRepository;
import viikingit.emusic.repository.IParentRepository;

@Controller
public class ParentController {
	
	@Autowired
	IParentRepository parRepo;
	
	@Autowired
	IEnfantRepository enfRepo;

	@Autowired
	private VueJS vue;
	
	@ModelAttribute("vue")
	public VueJS getVue() {
		return this.vue;
	}
	
	@GetMapping("account/{id}")
	public String editAccountPage(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		Optional<Parent> opt = parRepo.findById(authUser.getId());
		opt.ifPresent(orga -> model.put("parent",orga));
		model.put("userCo", authUser);
		return "/user/account";
	}
	
	
	@PostMapping("account/edit/{id}")
	public RedirectView edited(@ModelAttribute Parent parent,@PathVariable int id) {
		Parent toSave = parRepo.findById(id).get();;
		toSave = parent;
		parRepo.save(toSave);
		return new RedirectView("/");
	}
	
	@GetMapping("myKids/{id}")
	public String myKids(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		Optional<Parent> opt =parRepo.findById(authUser.getId());
		opt.ifPresent(orga -> model.put("enfant",orga));
		
		
		
		model.put("userCo", authUser);
		return "user/myKids";
	}
	
	@GetMapping("myKids/add/{id}")
	public String formAddKid(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "user/addKids";
	}
	
	@PostMapping("myKids/add")
	public RedirectView addKid(@ModelAttribute Enfant enfant,@AuthenticationPrincipal Parent authUser) {
		authUser.addKid(enfant);
		enfRepo.save(enfant);
		return new RedirectView("/");
	}
	
	
	
}
