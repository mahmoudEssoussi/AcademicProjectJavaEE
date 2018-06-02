package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface BillServiceRemote extends IGenericDAO<Bill> {
	public void addbill(Bill B) ;
	public void delete(Bill B) ;
	public List<Bill> afficherbill();
	public Bill FindById(int id);
	public List<Bill> displayArchive();
}
