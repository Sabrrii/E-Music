package viikingit.emusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfosController {

	@GetMapping("politique")
	public String politique() {
		return "infos_site/politique";
	}
}
