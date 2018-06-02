package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;

@Stateless
public class RP_ProjetService extends GenericDAO<RP_Projet> implements RP_ProjetServiceLocal,RP_ProjetServiceRemote {

	public RP_ProjetService() {
		super(RP_Projet.class);		
	}

	@Override
	public void RP_addProjet(RP_Projet projet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getProjectsByPM(String pm) {
		
		List l = new ArrayList<>();
		List l1 = new ArrayList<>();
		String name="";
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Projet p=(RP_Projet) l.get(i);
			if(p.getProjectManager().equals(pm))
			{
				l1.add(p);
			}
			
			
		}
		return l1;
	}

	@Override
	public RP_Projet getProjectById(int id) {
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			RP_Projet p=(RP_Projet) l.get(i);
				if(p.getIdProjet()==id)
					return p;
		}
		return null;
	}

}
