package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;

@Stateless
public class CongéService extends GenericDAO<Congé> implements CongéServiceLocal,CongéServiceRemote {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
	public CongéService() {
		super(Congé.class);		
	}
	@Override
	public void assignCongéToUser(Integer idUser, Integer idCongé) {
		User userManagedEntity=em.find(User.class, idUser);
		Congé congéManagedEntity=em.find(Congé.class, idCongé);
		userManagedEntity.getCongés().add(congéManagedEntity);	
		congéManagedEntity.setUser(userManagedEntity);
	}
	@Override
	public Congé getCongéById(int id) {
		Congé u=new Congé();
		List l = new ArrayList<>();
		l=this.findAll();	
		for(int i=0;i<l.size();i++)
		{
			
			
			Congé u1=(Congé) l.get(i);
			if(u1.getId_congé()==id)
				return u1;
		}
		return u;

	}


	



}
