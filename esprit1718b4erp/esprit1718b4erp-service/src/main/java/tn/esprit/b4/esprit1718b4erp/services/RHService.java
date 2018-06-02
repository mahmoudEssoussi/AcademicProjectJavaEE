package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;
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
public class RHService extends GenericDAO<Recrutement_attente> implements RHServiceLocal,RHServiceRemote {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
	public RHService() {
		super(Recrutement_attente.class);		
	}



	@Override
	public void Add_to_waiting_list(Recrutement_attente ra) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Recrutement_attente> findrecrutementById(int IdRec) {
		TypedQuery<Recrutement_attente> query=em.createQuery("SELECT p FROM Recrutement_attente p WHERE p.cin =:s",Recrutement_attente.class);
		query.setParameter("s",IdRec);
		return (query.getResultList());
	}

	@Override
	public byte[] loadImage(int id) {
		Recrutement_attente u=new Recrutement_attente();
		List l = new ArrayList<>();
		l=this.findAll();	
		for(int i=0;i<l.size();i++)
		{
			
			
			Recrutement_attente u1=(Recrutement_attente) l.get(i);
			if(u1.getId()==id)
				return u1.getPicture();
		}
		return u.getPicture();
	}



	@Override
	public void removeUser(int User_id) {
		Recrutement_attente user=em.find(Recrutement_attente.class, User_id);
		em.remove(user);				
	}



}
