package viikingit.emusic.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import io.github.jeemv.springboot.vuejs.VueJS;
import viikingit.emusic.models.Cours;
import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Inscriptions;
import viikingit.emusic.models.Parent;
import viikingit.emusic.pojo.ActiveUser;
import viikingit.emusic.repository.ICoursRepository;
import viikingit.emusic.repository.IEnfantRepository;
import viikingit.emusic.repository.IInscriptionsRepository;
import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.service.DbUserLoginService;

@Controller
public class ParentController {

	@Autowired
	IParentRepository parRepo;

	@Autowired
	IEnfantRepository enfRepo;

	@Autowired
	IInscriptionsRepository insRepo;

	@Autowired
	ICoursRepository courRepo;

	@Autowired
	private UserDetailsService uService;

	@Autowired
	private VueJS vue;

	@ModelAttribute("vue")
	public VueJS getVue() {
		return this.vue;
	}

	private ActiveUser activeUser=new ActiveUser();

	@GetMapping("account/{id}")
	public String editAccountPage(ModelMap model) {
		Optional<Parent> opt = parRepo.findById(activeUser.getActivePar().getId());
		opt.ifPresent(orga -> model.put("parent", orga));
		activeUser.connect(model);
		return "/user/account";
	}

	@PostMapping("account/edit/{id}")
	public RedirectView edited(@ModelAttribute Parent parent) {
		Parent par =(activeUser.getActivePar());
		par.setNom(parent.getNom());
		par.setPrenom(parent.getPrenom());
		par.setUsername(parent.getUsername());
		par.setAdresse(parent.getAdresse());
		par.setVille(parent.getVille());
		par.setQuotientFamilial(parent.getQuotientFamilial());
		par.setTel1(parent.getTel1());
		par.setTel2(parent.getTel2());
		parRepo.save(par);
		return new RedirectView("/");
	}

	@GetMapping("myKids/{id}")
	public String myKids(ModelMap model) {
		Optional<Parent> opt = parRepo.findById(activeUser.getActivePar().getId());
		List<Enfant> enfs = enfRepo.findByParent(activeUser.getActivePar());
		opt.ifPresent(orga -> model.put("enfant", orga));
		model.put("enf", enfs);
		activeUser.connect(model);
		return "user/myKids";
	}

	@GetMapping("myKids/add/{id}")
	public String formAddKid(ModelMap model) {
		activeUser.connect(model);
		return "user/addKids";
	}

	@PostMapping("myKids/add")
	public RedirectView addKid(@ModelAttribute Enfant enfant) {
		Optional<Parent> par = parRepo.findById(activeUser.getActivePar().getId());
		Parent ParentCreateur = par.get();
		Enfant enfantCree = enfant;
		ParentCreateur.addKid(enfantCree);
		((DbUserLoginService) uService).createEnf(enfantCree);

		return new RedirectView("/");
	}

	@GetMapping("myKids/delete/{id}")
	public RedirectView deleteActionKid(@PathVariable int id, RedirectAttributes attrs) {
		enfRepo.findById(id);
		enfRepo.deleteById(id);
		return new RedirectView("/myKids/{id}");
	}

	@GetMapping("/account/delete/{id}")
	public RedirectView deleteConfirm(@PathVariable int id, HttpServletRequest request) {
		parRepo.deleteById(id);
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession(false);
		return new RedirectView("/");
	}

	@GetMapping("/signUpLesson/{id}")
	public RedirectView ajouterCours(@PathVariable int id) {
		Cours courToSave = courRepo.findById(id).get();
		Inscriptions signIn = new Inscriptions();
		signIn.setNombre_de_paiements(4);
		signIn.setCours(courToSave);
		signIn.setParent(activeUser.getActivePar());
		insRepo.save(signIn);
		return new RedirectView("/myLesson");
	}

}
