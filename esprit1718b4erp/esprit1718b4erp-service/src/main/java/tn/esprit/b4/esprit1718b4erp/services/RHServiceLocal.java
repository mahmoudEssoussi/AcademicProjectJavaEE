package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface RHServiceLocal extends IGenericDAO<Recrutement_attente> {
	public void Add_to_waiting_list(Recrutement_attente ra);
	//public List<Congé> findCongéByUserId (int user_id);

}
