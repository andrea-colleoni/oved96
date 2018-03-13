package esercizio13marzo.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import esercizio13marzo.bean.BranoMusicale;

@Controller
@RequestMapping("/branomusicale")
public class BranoMusicaleController {
	
	@Autowired
	private EntityManager em;
	
	@RequestMapping(path="{id}", method=RequestMethod.GET)
	public @ResponseBody BranoMusicale byKey(@PathVariable("id") int id) {
		return em.find(BranoMusicale.class, id);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<BranoMusicale> all() {
		List<BranoMusicale> l = em
				.createQuery("select b from BranoMusicale b", BranoMusicale.class)
				.getResultList();
		return l;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody boolean insert(@RequestBody BranoMusicale bm) {
		em.getTransaction().begin();
		em.persist(bm);
		em.getTransaction().commit();
		return true;
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable("id") int id) {
		BranoMusicale bm = byKey(id);
		if (bm != null) {
			em.getTransaction().begin();
			em.remove(bm);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody boolean update(@RequestBody BranoMusicale bm) {
		if (byKey(bm.getId()) != null) {
			em.getTransaction().begin();
			em.merge(bm);
			em.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

}
