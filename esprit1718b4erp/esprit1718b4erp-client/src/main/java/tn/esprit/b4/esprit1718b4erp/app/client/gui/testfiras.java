package tn.esprit.b4.esprit1718b4erp.app.client.gui;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;

public class testfiras extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		   try {
	            Parent root = FXMLLoader.load(getClass().getResource("/fxml/bill.fxml"));
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	       } catch (IOException ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }
	   
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
