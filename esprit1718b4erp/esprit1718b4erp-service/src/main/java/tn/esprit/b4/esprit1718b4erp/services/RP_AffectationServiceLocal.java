package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Affectation;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface RP_AffectationServiceLocal extends IGenericDAO<RP_Affectation> {

	public boolean isAffected(int idUser); 
	public RP_Affectation getAffectationTeamLeader(int idProjet);
	public RP_Affectation getAffectationByIdPrjoctAndIdUser(int idProject,int idUser);
	public List<RP_Affectation> getAffectationByIdPrjoct(int idProject);
	public int getIdUserByFullName(String fn);

}
