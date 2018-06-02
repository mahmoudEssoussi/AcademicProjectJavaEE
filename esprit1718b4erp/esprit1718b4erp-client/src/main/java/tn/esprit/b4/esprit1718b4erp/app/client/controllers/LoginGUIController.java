package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author 
 */
public class LoginGUIController implements Initializable {
	   private static int idUserConnecte;
	    
	     public static int getIdUserConnecte() {
	        return idUserConnecte;
	    }
    @FXML
    private TextField txt_login;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_password1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // txt_password = TextFields.createClearablePasswordField();
    }    

    @FXML
    private void login_btn(ActionEvent event) throws IOException, NamingException {
          
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);
    	

        System.out.println(txt_login.getText()); 
	System.out.println(txt_password.getText());
           
       String role= userServiceRemote.getRoleByLoginAndPass(txt_login.getText(), txt_password.getText());
       idUserConnecte= userServiceRemote.getIdUserByLoginAndPass(txt_login.getText(), txt_password.getText());

       
       System.out.println(role);
         if(role.equals("responsable project"))
       {
       	  
          Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceResProjects.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show(); 
       }     if(role.equals("rh_responsable"))
       {
        	  
           Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/responsableRHinterface.fxml"));
       Scene espaceResProjectsScene=new Scene(espaceResProjects);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(espaceResProjectsScene);
       window.show(); 
        }
         if(role.equals("humain ressource"))
         {
         	  
            Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/EspaceRH.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show(); 
         }
         if(role.equals("crm"))
         {
        	 System.out.println("7bas");
         	  
            Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceCRM.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show(); 
         }
               if(role.equals("engineer"))
         {
         	  
            Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/eng_interface.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show(); 
         }
         if(role.equals("finance ressource"))
         {
         	  
            Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceRF.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show(); 
         }

          txt_login.setText("");
            txt_password.setText("");

            if(role.equals("admin"))
       {
       	  
          Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/EspaceAdmin.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show(); 
       }
       
       
   
   
}
    


}