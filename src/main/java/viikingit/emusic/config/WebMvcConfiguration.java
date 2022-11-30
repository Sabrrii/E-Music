package viikingit.emusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("logout");
		registry.addViewController("/403").setViewName("/errors/403");
		registry.addViewController("/404").setViewName("/errors/404");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
