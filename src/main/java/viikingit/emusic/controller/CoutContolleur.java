package viikingit.emusic.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import viikingit.emusic.models.Parent;

@Controller
public class CoutContolleur {

	@GetMapping("montant/{id}")
	public String viewMontant(@AuthenticationPrincipal Parent authUSer,@PathVariable int montant) {
		if() {
			 switch(Parent.quotientFamilial){
			   
		       case : 
		           System.out.println("Bonjour");
		           break;
		   
		       case 2:
		           System.out.println("Hello");
		           break;
		   
		       case 3:
		           System.out.println("Buenos dias");
		           break;
		       default:
		           System.out.println("Choix incorrect");
		           break;
		   }

		}else if(){
			
		}
		
	}

}
