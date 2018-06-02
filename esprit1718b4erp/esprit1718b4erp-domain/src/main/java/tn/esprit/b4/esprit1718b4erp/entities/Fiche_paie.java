package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fiche_paie")
public class Fiche_paie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_fichePaie")
	private int id_fichePaie;
	@Column(name = "salaire")
	private int salaire;
	public int getSalaire() {
		return salaire;
	}
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	@Column(name = "date_payement")
	private String date_payement;
	@Column(name = "indicateurPayement")
	private boolean indicateurPayement ;
	
	@OneToOne
	private User user ;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId_fichePaie() {
		return id_fichePaie;
	}
	public void setId_fichePaie(int id_fichePaie) {
		this.id_fichePaie = id_fichePaie;
	}
	public String getDate_payement() {
		return date_payement;
	}
	public void setDate_payement(String date_payement) {
		this.date_payement = date_payement;
	}
	public boolean isIndicateurPayement() {
		return indicateurPayement;
	}
	public void setIndicateurPayement(boolean indicateurPayement) {
		this.indicateurPayement = indicateurPayement;
	}


}
