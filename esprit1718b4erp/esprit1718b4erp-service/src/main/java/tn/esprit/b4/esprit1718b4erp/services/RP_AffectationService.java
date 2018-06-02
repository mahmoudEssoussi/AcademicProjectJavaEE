package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Affectation;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;

@Stateless
public class RP_AffectationService extends GenericDAO<RP_Affectation> implements RP_AffectationServiceLocal,RP_AffectationServiceRemote {

	public RP_AffectationService() {
		super(RP_Affectation.class);		
	}

	@Override
	public boolean isAffected(int idUser) {
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Affectation a=new RP_Affectation();
			a=(RP_Affectation) l.get(i);
			if(a.getIdUser()==idUser)
				return true;
		}
		return false;
	}

	@Override
	public RP_Affectation getAffectationTeamLeader(int idProjet) {
		List l = new ArrayList<>();
		RP_Affectation a=new RP_Affectation();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			
			a=(RP_Affectation) l.get(i);
			if(a.getIdProjet()==idProjet)
				if(a.getJeton()==2)
				return a;
		}
		return a;
	}

	@Override
	public RP_Affectation getAffectationByIdPrjoctAndIdUser(int idProject, int idUser) {
		List l = new ArrayList<>();
		 
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			
			RP_Affectation	a=(RP_Affectation) l.get(i);
			if((a.getIdProjet()==idProject)&&(a.getIdUser()==idUser))
				return a;
		}
		
		return null;
	}

	@Override
	public List<RP_Affectation> getAffectationByIdPrjoct(int idProject) {
		List l = new ArrayList<RP_Affectation>();
		List l1 = new ArrayList<RP_Affectation>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Affectation a=(RP_Affectation) l.get(i);
			
			if(a.getIdProjet()==idProject)
			{
				//System.err.println(u);
				l1.add(a);
				
			}
		}
		return l1;
	}

	@Override
	public int getIdUserByFullName(String fn) {
		List l = new ArrayList<RP_Affectation>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Affectation a=(RP_Affectation) l.get(i);
			
			if(a.getFullName().equals(fn))
				return a.getIdUser();
		}
		return 0;
	}
}
