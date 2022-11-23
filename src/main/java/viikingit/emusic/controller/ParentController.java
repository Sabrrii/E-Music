package viikingit.emusic.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import io.github.jeemv.springboot.vuejs.VueJS;
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
	public String editAccountPage(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		Optional<Parent> opt = parRepo.findById(authUser.getId());
		opt.ifPresent(orga -> model.put("parent", orga));
		model.put("userCo", authUser);
		return "/user/account";
	}

	@PostMapping("account/edit/{id}")
	public RedirectView edited(@ModelAttribute Parent parent, @PathVariable int id) {
		Parent toSave = parRepo.findById(id).get();
		toSave = parent;
		parRepo.save(toSave);
		return new RedirectView("/");
	}

	@GetMapping("myKids/{id}")
	public String myKids(ModelMap model,@AuthenticationPrincipal Parent authUser) {
		Optional<Parent> opt =parRepo.findById(authUser.getId());
		List<Enfant> enfs= enfRepo.findByParent(authUser);
		opt.ifPresent(orga -> model.put("enfant",orga));
		model.put("enf", enfs);

		
		

		model.put("userCo", authUser);
		return "user/myKids";
	}

	@GetMapping("myKids/add/{id}")
	public String formAddKid(ModelMap model, @AuthenticationPrincipal Parent authUser) {
		model.put("userCo", authUser);
		return "user/addKids";
	}

	@PostMapping("myKids/add")
	public RedirectView addKid(@ModelAttribute Enfant enfant,@AuthenticationPrincipal Parent authUser) {
		Optional<Parent> par = parRepo.findById(authUser.getId());
		Parent Test = par.get();
		Test.addKid(enfant);
		enfRepo.save(enfant);
		return new RedirectView("/");
	}
	
	
	
	@GetMapping("/account/delete/{id}")
	public RedirectView deleteConfirm(@PathVariable int id ,HttpServletRequest request) {
		parRepo.deleteById(id);
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession(false);
		return new RedirectView("/"); 
	}
	
	@GetMapping("/signUpLesson")
	public RedirectView signUpLesson() {
		
		return new RedirectView("/myLesson");
	}
	
	
}
