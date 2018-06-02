package tn.esprit.b4.esprit1718b4erp.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.User;

import tn.esprit.b4.esprit1718b4erp.utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {

    String getRoleByLoginAndPass(String login, String password);

    int getIdUserByLoginAndPass(String login, String password);

    List getAllRPUsers();

    List getAllENGUsers();

    User getUserById(int id);

    public List<User> DisplayAllUser();

    int getIdUserByFirstLastName(String first, String last);

    public void removeUser(int User_id);

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit);
    //long calculateDays(Date dateEarly, Date dateLater) ;
    // public  String daysBetween(Date createdDate, Date expiryDate);
    //public long getDaysCountBetweenDates(LocalDate date, LocalDate date2) ;

    byte[] loadImage(int id);

    List<Congé> findCongébyIdUser(int user_id);

}
