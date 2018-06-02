package tn.esprit.b4.esprit1718b4erp.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



import tn.esprit.b4.esprit1718b4erp.entities.Cellule_excel;
import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.GenericDAO;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService extends GenericDAO<User> implements UserServiceRemote, UserServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserService() {
		super(User.class);
	}

	@Override
	public User login(String login, String password) {
		User user = null;
		try {
			user = entityManager.createQuery("SELECT u FROM User u WHERE u.login=:l AND u.password=:p", User.class)
					.setParameter("l", login).setParameter("p", password).getSingleResult();
		} catch (Exception e) {
		}
		return user;
	}
	
	@Override
	public String getRoleByLoginAndPass(String login, String password) {
		String role="";
		
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			
			
			User u=(User) l.get(i);
			//System.err.println(u);
			if((u.getLogin().equals(login))&&(u.getPassword().equals(password)))
			{
				role=u.getRole();
			}
			
		}
		return role;

	}

	@Override
	public int getIdUserByLoginAndPass(String login, String password) {
		int id=-1;
		List l = new ArrayList<>();
		l=this.findAll();
		
		for(int i=0;i<l.size();i++)
		{
			
			
			User u=(User) l.get(i);
			System.err.println(u);
			if((u.getLogin().equals(login))&&(u.getPassword().equals(password)))
			{
				id=u.getId();
			}
			
		}
		
		return id;
	}

	@Override
	public List<RP_Projet> getAllRPUsers() {
		List l = new ArrayList<RP_Projet>();
		List l1 = new ArrayList<RP_Projet>();
        l=this.findAll();
		
		for(int i=0;i<l.size();i++)
		{
			User u=(User) l.get(i);
			
			if(u.getRole().equals("rp"))
			{
				l1.add(u);
				
			}
		}
		return l1;
	}

	@Override
	public User getUserById(int id) {
		User u=new User();
		List l = new ArrayList<>();
		l=this.findAll();	
		for(int i=0;i<l.size();i++)
		{
			
			
			User u1=(User) l.get(i);
			if(u1.getId()==id)
				return u1;
		}
		return u;
	}
	@Override
	public byte[] loadImage(int id) {
		User u=new User();
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			
			
			User u1=(User) l.get(i);
			if(u1.getId()==id)
				return u1.getPicture();
		}
		 return u.getPicture();
	}
	@Override
	public int getIdUserByFirstLastName(String first, String last) {
		List l = new ArrayList<>();
		l=this.findAll();
		for(int i=0;i<l.size();i++)
		{
			
			
			User u=(User) l.get(i);
			if((u.getFirstname().equals(first))&&(u.getLastname().equals(last)))
			{
				return u.getId();
			}
		}
		return 0;
	}

	@Override
	public List getAllENGUsers() {
		List l = new ArrayList<RP_Projet>();
		List l1 = new ArrayList<RP_Projet>();
        l=this.findAll();
		
		for(int i=0;i<l.size();i++)
		{
			User u=(User) l.get(i);
			
			if(u.getRole().equals("eng"))
			{
				l1.add(u);
				
			}
		}
		return l1;
	}

	


	@Override
	public List<Congé> findCongébyIdUser(int user_id) {
		List<Congé> list = new ArrayList<Congé>();
		String requete="select c from Congé c where c.user.id=:s  ";
		
		try{	
	list=(List<Congé>) entityManager.createQuery(requete,Congé.class)
	.setParameter("s", user_id)
	
	.getResultList();
		}
		catch(NoResultException ex)
		{
			System.out.println("pas de résultat");
		}

	return list;
	
	}

	@Override
	public void removeUser(int User_id) {
		User user=entityManager.find(User.class, User_id);
		entityManager.remove(user);		
	}
	public  long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	@Override
	public List<User> DisplayAllUser() {
		TypedQuery<User> q=entityManager.createQuery("SELECT p FROM User p", User.class);
		List<User> res=q.getResultList();
		return res;
	}

	@Override
	public List getAllUsers() {
		return null;
	}
        @Override
	public List getAllPMFullName() {
		List l = new ArrayList<User>();
		List l1 = new ArrayList<User>();
		l1.add("");
        l=this.findAll();
		
		for(int i=0;i<l.size();i++)
		{
			User u=(User) l.get(i);
			
			if(u.getRole().equals("rp"))
			{
				//System.err.println(u);
				l1.add(u.getFirstname()+ " "+u.getLastname());
				
			}
		}
		return l1;
	}



	}

	






