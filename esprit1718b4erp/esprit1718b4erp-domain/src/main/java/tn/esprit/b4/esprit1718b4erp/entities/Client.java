package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="client")
public class Client implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_client;
	@Column(name = "nom")
	private String nom;
	@Column(name = "adresse")
	private String adresse ;
	@Column(name = "email")
	private String email ;
	@Column(name = "nbprojet")
	private int nbprojet ;
	@Column(name = "verif")
	private  boolean verif ;
	@Column(name = "numtel")
	private String numtel ;
	@Column(name = "numcompte")
	private String numcompte ;
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNbprojet() {
		return nbprojet;
	}
	public void setNbprojet(int nbprojet) {
		this.nbprojet = nbprojet;
	}
	public boolean isVerif() {
		return verif;
	}
	public void setVerif(boolean verif) {
		this.verif = verif;
	}
	public String getNumtel() {
		return numtel;
	}
	public void setNumtel(String string) {
		this.numtel = string;
	}
	public String getNumcompte() {
		return numcompte;
	}
	public void setNumcompte(String string) {
		this.numcompte = string;
	}
	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", adresse=" + adresse + ", email=" + email
				+ ", nbprojet=" + nbprojet + ", verif=" + verif + ", numtel=" + numtel + ", numcompte=" + numcompte
				+ "]";
	}
	public Client() {
		super();
	}
	
	
}
