package viikingit.emusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping
	public String index() {
		return "index";
	}

	@GetMapping("cours")
	public String AllerACours() {
		return "cours/list_cours";
	}

}
