package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface CongéServiceLocal extends IGenericDAO<Congé> {

	public void assignCongéToUser(Integer idUser , Integer idCongé) ;
public Congé getCongéById(int id);

}
