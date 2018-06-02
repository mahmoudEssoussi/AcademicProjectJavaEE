package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Affectation;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Tache;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;


@Stateless
public class RP_TacheService extends GenericDAO<RP_Tache> implements RP_TacheServiceLocal,RP_TacheServiceRemote {
    
	public RP_TacheService() {
		super(RP_Tache.class);
	}

	@Override
	public boolean AssegnedTo(int id) {
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Tache t=new RP_Tache();
			t=(RP_Tache) l.get(i);
			if((t.getAssegnedTo()==id))
				return true;
		}
		return false;
	}

	@Override
	public RP_Tache getTaskById(int id) {
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Tache t=new RP_Tache();
			t=(RP_Tache) l.get(i);
			if(t.getIdTache()==id)
				return t;
		}
		return null;
	}

	@Override
	public List<RP_Tache> getTasksByProject(int idProjet) {
		List l = new ArrayList<RP_Tache>();
		List l1 = new ArrayList<RP_Tache>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Tache t=(RP_Tache) l.get(i);
			
			if(t.getIdProjet()==idProjet)
				l1.add(t);
		}
		return l1;
	}
}
