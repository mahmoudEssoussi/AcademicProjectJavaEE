package tn.esprit.b4.esprit1718b4erp.mBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.services.ClientService;

@ManagedBean
@SessionScoped
public class clientBean {
	private String nom;
	private String rec;
	public String getRec() {
		return rec;
	}
	public void setRec(String rec) {
		this.rec = rec;
	}
	private String prenom;
	private String adresse;
	private String numt;
	private String numc;
	private String email;
	private int nbprojet;
	private boolean verif;
	@EJB
	ClientService s;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNumt() {
		return numt;
	}
	public void setNumt(String numt) {
		this.numt = numt;
	}
	public String getNumc() {
		return numc;
	}
	public void setNumc(String numc) {
		this.numc = numc;
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
	public void ajoutclient()
	{
		Client c =new Client();
		c.setNom(nom);
		c.setAdresse(adresse);
		c.setNbprojet(0);
		c.setNumcompte(numc);
		c.setNom(nom);
		c.setNumtel(numt);
		c.setEmail(email);
		s.AddClient(c);
		System.out.println(nom);
		System.out.println(adresse);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ClientService getS() {
		return s;
	}
	public void setS(ClientService s) {
		this.s = s;
	}
	public List<Client> affclient()
	{
	return s.afficherclient();	
	}

}
