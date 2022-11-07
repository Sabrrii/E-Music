package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Parent;

@Controller
public class UserController {
	
	
	@Autowired
	private VueJS vue;

	@RequestMapping("account")
	public String accountPage(ModelMap model) {
		Parent parent =(Parent)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.put("parent", parent);
		vue.addData("isActive","account");
		vue.addData("active","disable");
		return "/user/account";
	}
	
}
