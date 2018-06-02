package tn.esprit.b4.esprit1718b4erp.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.Notification;
import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.User;

/**
 * Session Bean implementation class NotificationServices
 */
@Stateless
@LocalBean
public class NotificationServices implements NotificationServicesRemote, NotificationServicesLocal {

	



		
	   @PersistenceContext
	   EntityManager em ;
    /**
     * Default constructor. 
     */
    public NotificationServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void notif(int id, String text) {
		User u =em.find(User.class, id)	;
	
		Notification n=new Notification();
		n.setDate(new Date());
		n.setNotified(u);
		n.setText("Dear "+u.getLastname()+" \n"+text);
		n.setState(0);
		em.persist(n);
	
		;
		
	}

	@Override
	public List<Notification> getnotif(int id) {
		String req="select n from Notification n where n.state=0 and n.notified.id="+id;
		 List<Notification> ln =em.createQuery(req, Notification.class).getResultList();
		 for(Notification n :ln)
		 {
			 n.setState(1);
			 em.merge(n);
		 }
		 return ln;
	}
	
	
	@Override
	public List<Notification> DisplayAllnotif(int id) {
		TypedQuery<Notification> q=em.createQuery("select n from Notification n where n.state=0 and n.notified.id="+id, Notification.class);
		List<Notification> res=q.getResultList();
	return res;
	}

	@Override
	public List<Notification> DisplayAll(int id) {
		TypedQuery<Notification> q=em.createQuery("select n from Notification n where  n.notified.id="+id, Notification.class);
		List<Notification> res=q.getResultList();
		
		 for(Notification n :res)
		 {
			 n.setState(1);
			 em.merge(n);
		 }
		 return res;
	
		
		
		
	}


}
