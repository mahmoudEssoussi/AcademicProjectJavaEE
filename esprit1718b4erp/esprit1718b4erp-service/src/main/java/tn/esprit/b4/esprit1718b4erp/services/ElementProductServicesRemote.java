package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

@Remote
public interface ElementProductServicesRemote {
	public void add(ElementProduct B) ;
	public void delete(ElementProduct B) ;
	public void update(ElementProduct B);
	public ElementProduct FindById(Long id);
	public List<ElementProduct> DisplayAllPstock();
	public void SuperAdd(ElementProduct B,Product pr,PrimaryMaterialsStock pm,Integer quatity);
	

}
