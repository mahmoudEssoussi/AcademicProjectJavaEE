package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

@Remote
public interface ProductServicesRemote {
	public void addPRoduct(Product B) ;
	public void delete(Product B) ;
	public void update(Product B);
	public Product FindById(Long id);
	public List<Product> DisplayAllPstock();
	
	
}
