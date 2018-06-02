package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;

/**
 * Session Bean implementation class CaisseService
 */
@Stateless
@LocalBean
public class CaisseService extends GenericDAO<Caisse> implements CaisseServiceRemote, CaisseServiceLocal {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
 
    /**
     * Default constructor. 
     */
    public CaisseService() {
    	super(Caisse.class);
    }
	@Override
	public void save(Caisse entity) {
	em.persist(entity);
		
	}
	@Override
	public void delete(Caisse entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Caisse update(Caisse entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Caisse find(int entityID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addbill(Caisse C) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Caisse> afficherCaisse() {
		TypedQuery<Caisse> query=em.createQuery("SELECT e FROM"
				+ " Caisse e WHERE e.date = current_date()    ",Caisse.class);
		return query.getResultList();
	}
	
	


}
