package esercizio13marzo.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Il bean Autore rappresenta un autore ed è annotato con le
 * annotazioni JPA per renderlo persistente su DB Oracle (vedi anche il file META-INF/persistence.xml)
 * 
 * @author andre
 *
 */
@Entity
public class Autore {

	/**
	 * ID è il campo chiave dell'entity. In oracle la generazione della chiave è ottenuta
	 * interrogando una sequence che è definita utilizzando un SequenceGenerator di JPA
	 */
	@Id
	@GeneratedValue(generator="autore_seq") 
    @SequenceGenerator(name="autore_seq",sequenceName="AUTORE_SEQ", allocationSize=5) 	
	private int id;
	
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="autore", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<BranoMusicale> brani;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<BranoMusicale> getBrani() {
		return brani;
	}

	public void setBrani(List<BranoMusicale> brani) {
		this.brani = brani;
	}
	
	
	
}
