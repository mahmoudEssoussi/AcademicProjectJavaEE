package tn.esprit.b4.esprit1718b4erp.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface RP_NoteServiceRemote extends IGenericDAO<RP_Note> {

}
