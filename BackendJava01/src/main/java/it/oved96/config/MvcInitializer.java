package it.oved96.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * Questa classe inizializza l'ApplicationContext di Spring definendo
 * quale sia la classe di configurazione iniziale da caricare (in questo caso
 * SpringMvcConfig.class) e quale sia l'indirizzo del contesto radice
 * in cui Spring MVC funziona (in questo caso / )
 * 
 * @author andre
 *
 */
public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringMvcConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
