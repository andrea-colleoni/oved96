package it.oved96;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class PersonaServlet
 */
@WebServlet("/persona")
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BackendJava01");
		EntityManager em = emf.createEntityManager();
		
//		Persona p = new Persona();
//		p.setNome("Mario");
//		p.setCognome("Verdi");
//		p.setEmails(new ArrayList<>());
//		p.getEmails().add("mario@verdi.com");
//		p.getEmails().add("verdi@mario.com");
		
		List<Persona> lista = em
				.createQuery("select p from Persona p", Persona.class)
				.getResultList();
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().append(mapper.writeValueAsString(lista));
	}

}
