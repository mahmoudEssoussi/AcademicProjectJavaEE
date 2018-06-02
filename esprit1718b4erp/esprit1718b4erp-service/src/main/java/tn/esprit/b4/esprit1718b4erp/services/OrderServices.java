package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

/**
 * Session Bean implementation class OrderServices
 */
@Stateless
@LocalBean
public class OrderServices implements OrderServicesRemote, OrderServicesLocal {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public OrderServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addOrder(Order B) {
		em.persist(B);
		
	}

	@Override
	public void delete(Order B) {
		em.remove(em.merge(B));
		
	}

	@Override
	public void update(Order B) {
		B.setBill(B.getQuantity()*B.getSellingPriceUnit());
		em.merge(B);
		
	}

	@Override
	public Order FindById(Long id) {
		 return	em.find(Order.class, id) ;
	}

	@Override
	public List<Order> DisplayAllOrder() {
		TypedQuery<Order> q=em.createQuery("SELECT p FROM Order p", Order.class);
		List<Order> res=q.getResultList();
		
		
		return res;
	}
	
	

}
