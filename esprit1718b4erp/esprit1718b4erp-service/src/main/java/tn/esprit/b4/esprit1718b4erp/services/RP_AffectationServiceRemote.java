package tn.esprit.b4.esprit1718b4erp.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Affectation;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface RP_AffectationServiceRemote extends IGenericDAO<RP_Affectation> {
	public boolean isAffected(int idUser);
	public RP_Affectation getAffectationTeamLeader(int idProjet);
	public RP_Affectation getAffectationByIdPrjoctAndIdUser(int idProject,int idUser);


}
