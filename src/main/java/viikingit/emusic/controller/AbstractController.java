package viikingit.emusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.github.jeemv.springboot.vuejs.VueJS;
import viikingit.emusic.pojo.ActiveUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AbstractController {
    @Autowired
    private VueJS vue;

    @ModelAttribute("vue")
    public VueJS getVue() {
        return this.vue;
    }

    @ModelAttribute("activeUser")
    public ActiveUser getActiveUsername() {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        return new ActiveUser(auth);
    }
}
