package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;

/**
 * Session Bean implementation class PrimaryMaterialsStockServices
 */
@Stateless
@LocalBean
public class PrimaryMaterialsStockServices implements PrimaryMaterialsStockServicesRemote, PrimaryMaterialsStockServicesLocal {
	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
	
	@EJB
	private NotificationServicesRemote notificationService ;
	
    public PrimaryMaterialsStockServices() {
    
    }

	@Override
	public void addPstock(PrimaryMaterialsStock B) {
		em.persist(B);
		
	}

	@Override
	public void delete(PrimaryMaterialsStock B) {
		em.remove(em.merge(B));
		
	}

	@Override
	public void update(PrimaryMaterialsStock B) {
		
		if (B.getFinalStock()<20){notificationService.notif(12, "the Stock of "+B.getItemName()+" is going to be exhausted ");}
		else if (B.getFinalStock()==0){notificationService.notif(12, "the Stock of "+B.getItemName()+" is  exhausted ");}
		if (B.getFinalStock()>=0){em.merge(B);}
		
		
	}

	@Override
	public PrimaryMaterialsStock FindById(Long id) {
		 return	em.find(PrimaryMaterialsStock.class, id) ;
	}

	@Override
	public List<PrimaryMaterialsStock> DisplayAllPstock() {
		TypedQuery<PrimaryMaterialsStock> q=em.createQuery("SELECT p FROM PrimaryMaterialsStock p", PrimaryMaterialsStock.class);
		List<PrimaryMaterialsStock> res=q.getResultList();
		
		
		return res;
	}

	@Override
	public List<PrimaryMaterialsStock> FindByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrimaryMaterialsStock> FindByName(String itemName) {
	
				return null;
			
	}
	@Override
	public void SuperUpdate(PrimaryMaterialsStock B, String newIn, String newOut) {

		
		int Final = B.getFinalStock();
		int Initial = B.getInitialStock();
		int In = B.getStockIn();

		int UnitPrice = B.getUnitPrice();
		int StockPrice = B.getStockPrice();

		int newUnitPrice = Integer.parseInt(newOut);

		float UP = ((Integer.parseInt(newIn) * newUnitPrice) + (UnitPrice * Final))
				/ (Final + Integer.parseInt(newIn));

		In = In + Integer.parseInt(newIn);
		Final = Initial + In;

		B.setUnitPrice((int) UP);
		B.setStockPrice((int) (UP * Final));

		B.setStockIn(In);
		B.setFinalStock(Final);
		 
		
		em.merge(B);
		// TODO Auto-generated method stub
		
	}

}
