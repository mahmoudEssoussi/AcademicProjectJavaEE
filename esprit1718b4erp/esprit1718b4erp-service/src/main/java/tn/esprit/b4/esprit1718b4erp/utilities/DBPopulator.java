package tn.esprit.b4.esprit1718b4erp.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;


import tn.esprit.b4.esprit1718b4erp.entities.Client;


import tn.esprit.b4.esprit1718b4erp.entities.Congé;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Tache;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;

import tn.esprit.b4.esprit1718b4erp.services.ClientServiceLocal;


import tn.esprit.b4.esprit1718b4erp.services.CongéServiceLocal;
import tn.esprit.b4.esprit1718b4erp.services.RHServiceLocal;


import tn.esprit.b4.esprit1718b4erp.services.RP_NoteServiceLocale;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceLocal;
import tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceLocal;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceLocal;

@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private ClientServiceLocal clientServiceLocal;

	
	@EJB
	private RHServiceLocal rHServiceLocal;
	@EJB
	private CongéServiceLocal congéServiceLocal;
	
	@EJB
	private RP_ProjetServiceLocal rpProjetServiceLocal;
	
	@EJB
	private RP_NoteServiceLocale rpNoteServiceLocale;
	
	@EJB
	private RP_TacheServiceLocal rpTacheServiceLocal;

	public DBPopulator() {
	}

	@PostConstruct
	public void init() {
		User user = new User(0, null, null, null, null, null, null, 0, null, false, null, null, null, null, null, null, 0, 0 , null, 0, 0);
		User user1 = new User(2,"majdi", "majdi", "majdi", "majdi", "majdi", "majdi", 0, "majdi", false, "majdi","majdi", "single", null, null, "rp", 0, 0 ,null, 0, 0);
        User u1=new User("firas", "saadaoui", "firas", "firas", "humain ressource");
        User u2=new User("melik", "barketi", "melik", "melik", "crm");
        User u3=new User("majdi", "chihi", "majdi", "majdi", "responsable project");
        User u4=new User("firas", "guizeni", "foussi", "foussi", "finance ressource");
        User u5=new User("mahmoud", "soussi", "soussi", "soussi", "finance ressource");

        User u66=new User("mahmoud", "koukou", "koukou", "koukou", "rh_responsable");
      

        User u6=new User("admin", "admin", "admin", "admin", "ad");
        User u7=new User("majdii", "chihii", "majdii", "majdii", "rp");
        User u8=new User("majdiii", "chihiii", "majdiii", "majdiii", "rp");
        User u9=new User("eng1", "eng1", "eng1", "eng1", "eng", "talents1", 0,1,"1");
        User u10=new User("eng2", "eng2", "eng2", "eng2", "eng", "talents2", 0,1,"1");
        User u11=new User("eng3", "eng3", "eng3", "eng3", "eng", "talents3", 0,1,"1");
        User u12=new User("eng4", "eng4", "eng4", "eng4", "eng", "talents4", 0,1,"1");
        //userServiceLocal.update(u1);

    
 userServiceLocal.update(u1);
        userServiceLocal.update(u2);
       userServiceLocal.update(u3);
        userServiceLocal.update(u4);
       userServiceLocal.update(u5);
       userServiceLocal.update(u66);


        /*Congé c = new Congé(null,null,null,true);
       // congéServiceLocal.update(c);
        userServiceLocal.update(u7);
        userServiceLocal.update(u8);
        userServiceLocal.update(u9);
        userServiceLocal.update(u10);
        userServiceLocal.update(u11);
        userServiceLocal.update(u12);
		//userServiceLocal.update(user1);
		//System.err.println(userServiceLocal.getRoleByLoginAndPass("majdi", "majdi"));
		//System.err.println(userServiceLocal.getIdUserByLoginAndPass("firass", "firass"));
        //System.err.println(userServiceLocal.getUserById(1));
		
		
		
		//RP_projet

	/*	RP_Projet projet = new RP_Projet();
		rpProjetServiceLocal.save(projet);
		System.out.println(projet);
		//Recrutement_attente
		Recrutement_attente ra = new Recrutement_attente();
		rHServiceLocal.save(ra);
*/
	

	/*	RP_Projet projet = new RP_Projet();
		rpProjetServiceLocal.save(projet);*/
		
		
	/*	List l = new ArrayList<>();
		l=userServiceLocal.getAllENGUsers();
		
		for(int i=0;i<l.size();i++)
		{
			User u=(User) l.get(i);
			System.out.println(u);
		}*/
        
        
        RP_Projet p=new RP_Projet("identifiantProjet", "identifiantClient", "nomProjet", "description"," plannedStartDate", "plannedEndDate", "type", 3, 3);
        RP_Projet p1=new RP_Projet("identifiantProjet1", "identifiantClient1", "nomProjet1", "description1"," plannedStartDate1", "plannedEndDate1", "type1", 3, 3);
        RP_Projet p2=new RP_Projet("identifiantProjet2", "identifiantClient2", "nomProjet2", "status2","majdii chihii", "descriptioné", "priority2", "plannedStartDate2", "plannedEndDate2", "actualEndDate2", "type2", "creationDate2", 3, 3);
        RP_Projet p3=new RP_Projet("identifiantProjet3", "identifiantClient3", "nomProjet3", "status3","majdi chihi", "description3", "priority3", "plannedStartDate3", "plannedEndDate3", "actualEndDate3", "type3", "creationDate3", 3, 3);
        		
        		
    //   rpProjetServiceLocal.save(p);
      //  rpProjetServiceLocal.save(p1);
     /*   
        rpProjetServiceLocal.save(p3);
        rpProjetServiceLocal.save(p2);
       
  
        RP_Note n= new RP_Note();
        rpNoteServiceLocale.save(n);*/

/*Client c1=new Client();
 clientServiceLocal.save(c1);*/
     /*   RP_Tache t= new RP_Tache();
        rpTacheServiceLocal.save(t);*/

	}
}
