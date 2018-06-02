/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.EtatCongé;
import tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class PopUpRepConController implements Initializable {

    @FXML
    private TextField test_txt;
    @FXML
    private Label label_reponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
            String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
            
            Context context=new InitialContext();
            Context context1=new InitialContext();
            Congé congé = new Congé();
            UserServiceRemote userServiceRemote = (UserServiceRemote) context1.lookup(jndiName1);
            CongéServiceRemote  proxy =(CongéServiceRemote) context.lookup(jndiName);
            List<Congé> c = userServiceRemote.findCongébyIdUser(LoginGUIController.getIdUserConnecte());
            System.out.println(c);
            for(int i = 0 ; i<c.size();i++){
               congé  = (Congé)c.get(i);
                 label_reponse.setText(congé.setEtatString(EtatCongé.accepted));
            }
          
        } catch (NamingException ex) {
            Logger.getLogger(PopUpRepConController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
