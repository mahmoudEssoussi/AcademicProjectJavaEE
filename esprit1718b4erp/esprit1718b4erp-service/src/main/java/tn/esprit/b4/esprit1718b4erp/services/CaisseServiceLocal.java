package tn.esprit.b4.esprit1718b4erp.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface CaisseServiceLocal extends IGenericDAO<Caisse> {

}
