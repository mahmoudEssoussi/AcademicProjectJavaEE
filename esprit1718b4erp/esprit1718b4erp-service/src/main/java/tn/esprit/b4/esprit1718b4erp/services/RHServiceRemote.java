package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface RHServiceRemote extends IGenericDAO<Recrutement_attente> {
	public void Add_to_waiting_list(Recrutement_attente ra);
	public List<Recrutement_attente> findrecrutementById(int IdRec);
	//public List<Congé> findCongéByUserId (int user_id);

	public void removeUser(int User_id);

	public byte[] loadImage(int id) ;

}
