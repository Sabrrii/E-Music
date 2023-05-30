package viikingit.emusic.pojo;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Parent;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUser {
     
    @NonNull
    private Authentication authentication;

    
    public void connect(ModelMap model){
        Object responsable = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (responsable instanceof Parent) {
			Parent authUser = (Parent) responsable;
			model.put("userCo", authUser);
		}else if (responsable instanceof Enfant){
			Enfant authUser = (Enfant) responsable;
			model.put("userCo", authUser);
		}
    }

    public Parent getActivePar(){
      return (Parent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Enfant getActiveEnf(){
      return (Enfant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

