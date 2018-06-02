package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface CaisseServiceRemote extends IGenericDAO<Caisse> {
	
	public void addbill(Caisse C) ;
	public void delete(Caisse C) ;
	public List<Caisse> afficherCaisse();
	//public Caisse FindById(int id);

}
