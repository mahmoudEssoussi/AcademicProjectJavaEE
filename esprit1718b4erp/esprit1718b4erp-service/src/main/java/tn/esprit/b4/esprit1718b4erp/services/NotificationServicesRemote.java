package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Notification;
import tn.esprit.b4.esprit1718b4erp.entities.User;

@Remote
public interface NotificationServicesRemote {
	 public List<Notification> getnotif(int id);
	  public void notif(int id ,String text);
	  public List<Notification> DisplayAllnotif(int id) ;
	    public List<Notification> DisplayAll(int id);
	

}
