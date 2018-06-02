package tn.esprit.b4.esprit1718b4erp.app.client.gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import tn.esprit.b4.esprit1718b4erp.services.NotificationServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote;

public class TestMahmoud {

	public static void main(String[] args) throws NamingException{
		 String ProductJindi = "/esprit1718b4erp-ear/esprit1718b4erp-service/NotificationServices!tn.esprit.b4.esprit1718b4erp.services.NotificationServicesRemote";
         Context context = new InitialContext();
         NotificationServicesRemote u = (NotificationServicesRemote) context.lookup(ProductJindi);
         //u.notif(5, "hoho");
         System.out.println(u.DisplayAllnotif(5));
		
	}

}
