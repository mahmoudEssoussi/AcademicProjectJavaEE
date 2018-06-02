package tn.esprit.b4.esprit1718b4erp.app.client.gui;


import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;




public class Main  {

	
	public static void main(String[] args) throws NamingException {
		String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		//String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
		//String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);
		System.out.println("test"); 
        User u1=new User("firas", "saadaouiameeel", "firas", "firas", "rh");
        System.out.println(userServiceRemote.findCongébyIdUser(3));
          //CongéServiceRemote congéServiceRemote =(CongéServiceRemote) context.lookup(jndiName);
		//System.out.println(userServiceRemote.getRoleByLoginAndPass("firas", "firas"));
        //congéServiceRemote.assignCongéToUser(3,0);
          
	//RHServiceRemote rhServiceRemote = (RHServiceRemote) context.lookup(jndiName);
	//System.out.println(rhServiceRemote.findrecrutementById(12));
	//userServiceRemote.delete(u1);
	}
	

}
