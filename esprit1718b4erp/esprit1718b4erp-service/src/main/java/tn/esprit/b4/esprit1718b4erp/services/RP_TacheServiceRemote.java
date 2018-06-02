package tn.esprit.b4.esprit1718b4erp.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Tache;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface RP_TacheServiceRemote extends IGenericDAO<RP_Tache> {

	public  boolean AssegnedTo(int id);
	public RP_Tache getTaskById(int id);


}
