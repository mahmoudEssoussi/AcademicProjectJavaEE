package tn.esprit.b4.esprit1718b4erp.services;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;


/**
 * Session Bean implementation class BillService
 */
@Stateless
@LocalBean
public class BillService extends GenericDAO<Bill> implements BillServiceLocal, BillServiceRemote {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public BillService() {
    	
    		super(Bill.class);
    	
    }

	@Override
	public void addbill(Bill B) {
		em.persist(B);
		
	}

	@Override
	public void delete(Bill B) {
	em.remove(em.merge(B));
		
	}
	//public List<Bill> findAll() {
	//	TypedQuery<Bill> query=em.createQuery("SELECT p FROM Bill p ",Bill.class);
		
	//	return (query.getResultList());
	//}
	

public List<Bill> afficherbill() {
		
	TypedQuery<Bill> query=em.createQuery("SELECT e FROM"
			+ " Bill e WHERE e.to_pay_before >= current_date()    ",Bill.class);
	
	
	return query.getResultList();
}

public Bill FindById(int id) {
	 return	em.find(Bill.class, id) ;
}


@Override
public List<Bill> displayArchive() {

	TypedQuery<Bill> query=em.createQuery("SELECT e FROM"
			+ " Bill e WHERE e.to_pay_before < current_date()",Bill.class);
	
	
	return query.getResultList();
}
				

	//@Override
	//public List<Bill> FindAll() {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

/*	@Override
	public void update(Bill B) {
		em.merge(B);
		
	}

	@Override
	public BillService FindById(int id) {
		 return	em.find(Bill.class, id) ;
	}

	@Override
	public List<Bill> DisplayAllBill() {
		TypedQuery<Bill> q=em.createQuery("SELECT p FROM Bill p", Bill.class);
		List<Bill> res=q.getResultList();
		
		
		return res;
	}*/
}
