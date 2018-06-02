package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

/**
 * Session Bean implementation class ProductServices
 */
@Stateless
@LocalBean
public class ProductServices implements ProductServicesRemote, ProductServicesLocal {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ProductServices() {
    	
    }

	@Override
	public void addPRoduct(Product B) {
		em.persist(B);
	}

	@Override
	public void delete(Product B) {
		em.remove(em.merge(B));
		
	}

	@Override
	public void update(Product B) {
		em.merge(B);
		
	}

	@Override
	public Product FindById(Long id) {
		 return	em.find(Product.class, id) ;
		
	}

	@Override
	public List<Product> DisplayAllPstock() {
		TypedQuery<Product> q=em.createQuery("SELECT p FROM Product p", Product.class);
		List<Product> res=q.getResultList();
		
		
		return res;
	}

}
