package it.oved96.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.oved96.Persona;

@Controller
public class PrimoController {
	
	@Autowired
	private EntityManager em;

	@RequestMapping("/hello")
	public String hello() {
		return "index";
	}
	
	@RequestMapping("/elencoPersone")
	public ModelAndView elencoPersone() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elencoPersone");
		mv.addObject("listaPersone", persone());
		
		return mv;
	}
	
	@RequestMapping("/personeJSON")
	public @ResponseBody List<Persona> persone() {
		List<Persona> lista = em
				.createQuery("select p from Persona p", Persona.class)
				.getResultList();
		
		return lista;
	}
	
	@RequestMapping(path="aggiungiPersona", method=RequestMethod.POST)
	public @ResponseBody boolean aggiungiPersona(@RequestBody Persona p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		return true;
	}
}
