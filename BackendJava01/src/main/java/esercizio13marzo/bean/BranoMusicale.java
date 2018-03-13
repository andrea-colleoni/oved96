package esercizio13marzo.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Il bean BranoMusicale rappresenta un brano musicale ed è annotato con le
 * annotazione JPA per renderlo persistente su DB Oracle (vedi anche il file META-INF/persistence.xml)
 * 
 * @author andre
 *
 */
@Entity
public class BranoMusicale {
	
	/**
	 * ID è il campo chiave dell'entity. In oracle la generazione della chiave è ottenuta
	 * interrogando una sequence che è definita utilizzando un SequenceGenerator di JPA
	 */
	@Id
	@GeneratedValue(generator="brano_seq") 
    @SequenceGenerator(name="brano_seq",sequenceName="BRANO_SEQ", allocationSize=5) 
	private int id;
	
	private String titolo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Autore autore;
	
	private int annoPubblicazione;
	private int giudizio;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Autore getAutore() {
		return autore;
	}
	public void setAutore(Autore autore) {
		this.autore = autore;
	}
	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}
	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	public int getGiudizio() {
		return giudizio;
	}
	public void setGiudizio(int giudizio) {
		this.giudizio = giudizio;
	}
	
	

}
