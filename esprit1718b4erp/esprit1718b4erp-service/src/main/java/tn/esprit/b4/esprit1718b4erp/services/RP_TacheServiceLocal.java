package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Tache;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface RP_TacheServiceLocal extends IGenericDAO<RP_Tache> {
	public  boolean AssegnedTo(int id);
	public RP_Tache getTaskById(int id);
	public List<RP_Tache> getTasksByProject(int idProjet);

}
