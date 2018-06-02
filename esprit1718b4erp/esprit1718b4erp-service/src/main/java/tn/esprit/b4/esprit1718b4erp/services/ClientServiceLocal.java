package tn.esprit.b4.esprit1718b4erp.services;


import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface ClientServiceLocal extends IGenericDAO<Client> {
	public void AddClient(Client client);
	public List<Client> afficherclient();
	public void supprimer(Client c);
	 public List<Client> searchemail(String ch);
	 public List<Client> searchname(String ch);
	 public List<RP_Projet> clientprojet(String ch);
	 public int calculnbprojet(String x);
	 public void ajoutprojetclient(RP_Projet p);
	 public void deleteprojetclient(RP_Projet p);

}
