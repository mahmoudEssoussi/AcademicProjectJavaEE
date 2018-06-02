package tn.esprit.b4.esprit1718b4erp.services;

import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;

@Stateless
public class RP_NoteService extends GenericDAO<RP_Note> implements RP_NoteServiceLocale,RP_NoteServiceRemote {
	
	public RP_NoteService() {
		super(RP_Note.class);
	}

}
