package it.oved96.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configurazione dei bean presenti nell'AppplicationContext di Spring.
 * Questa configurazione avvia un contesto Spring in cui sono abilitate le funzionalità
 * di Spring WEB MVC.
 * 
 * @author andre
 *
 */
@EnableWebMvc
@ComponentScan(basePackages = { "it.oved96.controller", "esercizio13marzo.controller" })
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

	/**
	 * Configura il gestore delle risorse statiche: accessibili all'URL
	 * /resources/** e memorizzate nella directory /resources/ sotto a 
	 * src/main/webapplication
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/");
	}

	/**
	 * Configura la directory dove Spring individua le pagine JSP
	 * e il suffisso (estensione) utilizzato per le pagine quando
	 * Spring usa la generazione completa del contenuto tramite View Engine
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver
                         = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * Definisce un EntityManagerFactory e la rende disponibile
	 * come singleton nell'ApplicationContext
	 * 
	 * @return
	 */
	@Bean
	@Scope("singleton")
	public EntityManagerFactory emFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BackendJava01");
		return emf;
	}
	
	/**
	 * Definisce un prototipo di EntityManager, ottenuto dall'EntityManagerFactory
	 * presente nell'ApplicationContext
	 * 
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public EntityManager entityManager() {
		return emFactory().createEntityManager();
	}
	
}
