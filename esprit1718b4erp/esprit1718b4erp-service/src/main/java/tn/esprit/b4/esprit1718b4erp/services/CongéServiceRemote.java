package tn.esprit.b4.esprit1718b4erp.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.User;

import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface CongéServiceRemote extends IGenericDAO<Congé> {

	public void assignCongéToUser(Integer idUser , Integer idCongé) ;
}
