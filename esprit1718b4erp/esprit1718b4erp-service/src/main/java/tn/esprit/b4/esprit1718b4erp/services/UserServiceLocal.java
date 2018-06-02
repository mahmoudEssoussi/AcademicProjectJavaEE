package tn.esprit.b4.esprit1718b4erp.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Local
public interface UserServiceLocal extends IGenericDAO<User> {

	User login(String login, String password);
	String getRoleByLoginAndPass(String login, String password);
	List<Congé> findCongébyIdUser(int user_id );
	public void removeUser(int User_id);
	public List<User> DisplayAllUser();
    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit);

	int getIdUserByLoginAndPass(String login, String password);
	List getAllRPUsers();
	List getAllUsers();
	List getAllENGUsers();
	User getUserById(int id);
	int getIdUserByFirstLastName(String first, String last);
	 byte[] loadImage(int id);
         List getAllPMFullName();

	 
}
