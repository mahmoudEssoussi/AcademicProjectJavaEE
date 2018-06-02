package tn.esprit.b4.esprit1718b4erp.services;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;

@Remote
public interface PrimaryMaterialsStockServicesRemote {
	public void addPstock(PrimaryMaterialsStock B) ;
	public void delete(PrimaryMaterialsStock B) ;
	public void update(PrimaryMaterialsStock B);
	public PrimaryMaterialsStock FindById(Long id);
	public List<PrimaryMaterialsStock> DisplayAllPstock();
	public List<PrimaryMaterialsStock> FindByDate(String date );
	public List<PrimaryMaterialsStock> FindByName(String itemName);
	void SuperUpdate(PrimaryMaterialsStock B, String newIn, String newOut);

}
