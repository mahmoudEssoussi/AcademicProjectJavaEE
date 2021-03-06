package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface RP_ProjetServiceLocal extends IGenericDAO<RP_Projet> {
	public void RP_addProjet(RP_Projet projet);
	public List getProjectsByPM(String pm);
	public RP_Projet getProjectById(int id);


}
