package tn.esprit.b4.esprit1718b4erp.services;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.TypedQuery;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.SendMailTLS;
import tn.esprit.b4.esprit1718b4erp.entities.reclamation;
import tn.esprit.b4.esprit1718b4erp.entities.typerec;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class ClientService  extends GenericDAO<Client> implements ClientServiceRemote, ClientServiceLocal {
	@PersistenceContext(unitName="erp-ejb")
	private EntityManager em;

	

	public ClientService() {
		super(Client.class);
	}




	
	public void AddClient(Client client) {
		em.persist(client);
		
	}
	@Override
	public List<Client> afficherclient() {
		
		List l = new ArrayList<Client>();
		l=this.findAll();
		return l;
		
	}
	public void supprimer(Client c){
		
	}
		
	 public List<Client> searchemail(String ch)
		{
			TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.email =:email",Client.class);
			query.setParameter("email", ch);
			return query.getResultList();
		}
	    public List<Client> searchname(String ch)
		{
			TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.nom =:nom",Client.class);
			query.setParameter("nom", ch);
			return query.getResultList();
		}
	    public List<RP_Projet> clientprojet(String ch)
	    {
	    	TypedQuery<RP_Projet> query = em.createQuery("SELECT c FROM RP_Projet c WHERE c.identifiantClient =:nom",RP_Projet.class);
			query.setParameter("nom", ch);
			return query.getResultList();
	    }
	    public int calculnbprojet(String x)
	    {
	    	TypedQuery<Long> query = em.createQuery("SELECT COUNT(c.idProjet) FROM RP_Projet c WHERE c.identifiantClient =:nom",Long.class);
			query.setParameter("nom", x);
			
			return query.getSingleResult().intValue();
	    }
public void ajoutprojetclient(RP_Projet p)
{
	em.persist(p);
}
public void deleteprojetclient(RP_Projet p)
{
	
	}
public void addtr(typerec r)
{
	em.persist(r);}
public List<typerec> afficherret()
{
	TypedQuery<typerec> query = em.createQuery("SELECT c FROM typerec c ",typerec.class);
	
	return query.getResultList();
	}
public void ajourec(reclamation r)
{
	em.persist(r.getC());
	em.persist(r.getP());
	em.persist(r);
}
public int calculnbreclamation(String x)
{
	TypedQuery<Long> query = em.createQuery("SELECT COUNT(c.idr) FROM reclamation c WHERE c.typerec =:nom",Long.class);
	query.setParameter("nom", x);
	
	return query.getSingleResult().intValue();
}
public void sendm(int id)
{
	int i;
	Date d=new Date();
	Date date=new Date();
	String ch=Integer.toString(id);
	 List< RP_Projet>proj = this.clientprojet(ch);
     for(i=0;i<proj.size();i++)
     {
     	String ch2;
     	String ch3 = null;
     	String ch4 = null;
     	int j=0;
     	String ch1=proj.get(i).getPlannedEndDate();
     	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       

         try {

             date = formatter.parse(ch1);
             

         } catch (ParseException e) {
             e.printStackTrace();
         }

     
     	if(d.getMonth()==date.getMonth())
     	{
     		if(d.getYear()==date.getYear())
     		{
     			if((d.getDay()==date.getDay()))
     			{
     				String mail="mohamedmalek.barkati@esprit.tn";
     		     	  String pass="Mm09615363";
     		     	  String[] to={"mohamedmalek.barkati@esprit.tn"};
     		     	  String subject="Projet terminé";
     		     	  String body="We're sending  you this mail to inform you that your projet will end in "+proj.get(i).getPlannedEndDate();
     		     	  SendMailTLS sendMail = new SendMailTLS();
     		     	  sendMail.sendFromGMail(mail,pass,to,subject,body);
     			}
     		}
     	}
     	}
	}
}
