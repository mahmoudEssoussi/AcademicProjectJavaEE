package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.OrderDetails;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

/**
 * Session Bean implementation class ElementProductServices
 */
@Stateless
@LocalBean
public class ElementProductServices implements ElementProductServicesRemote, ElementProductServicesLocal {

	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
	
	@EJB
	PrimaryMaterialsStockServicesRemote I;
	@EJB
	ProductServicesRemote P;
    /**
     * Default constructor. 
     */
    public ElementProductServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void add(ElementProduct B) {
		em.persist(B);
		
	}

	@Override
	public void delete(ElementProduct B) {
		em.remove(em.merge(B));
		
	}

	@Override
	public void update(ElementProduct B) {
		em.merge(B);
		
	}

	

	@Override
	public List<ElementProduct> DisplayAllPstock() {
		TypedQuery<ElementProduct> q=em.createQuery("SELECT p FROM ElementProduct p", ElementProduct.class);
		List<ElementProduct> res=q.getResultList();
		
		
		return res;
	}

	@Override
	public ElementProduct FindById(Long id) {
		 return	em.find(ElementProduct.class, id) ;
	}

	@Override
	public void SuperAdd(ElementProduct B, Product pr, PrimaryMaterialsStock pm, Integer quantity) {
	      int Final = pm.getFinalStock();
	        int Out = pm.getStockOut();
	        
	        if (Final < quantity) {
	            System.out.println("stock insufisant");

	        } else {
	            // mis a jour du matiere premiere
	            pm.setStockOut(Out + quantity);
	            pm.setFinalStock(Final - quantity);
	            I.update(pm);
	            // mis a jour du Produit
	            int a = pr.getCostPraimaryMaterials();
	            int CostPraimaryMaterials = (pm.getUnitPrice() * quantity) + a;
	            int unitprice = (pr.getHrCost() + pr.getManifacturCost() + CostPraimaryMaterials) / pr.getUnitInStock();
	            pr.setCostPraimaryMaterials(CostPraimaryMaterials);
	            pr.setUnitPrice(unitprice);
	            System.out.println(unitprice);
	            P.update(pr);
	            // ajout de matiere premiere a un produit
	            B.setMaterials(pm);
	            B.setProduct(pr);
	            B.setQuantity(quantity);
	            em.persist(B);

	        }

		
	}


}
