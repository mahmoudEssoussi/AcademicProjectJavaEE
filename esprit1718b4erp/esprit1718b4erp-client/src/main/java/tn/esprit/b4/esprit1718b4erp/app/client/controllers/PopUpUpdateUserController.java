/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class PopUpUpdateUserController implements Initializable {

    @FXML
    private TextField IdTxt;
    @FXML
    private TextField FrstnaemTxt;
    @FXML
    private TextField LastNameTxt;
    @FXML
    private TextField EmailTxt;
    @FXML
    private TextField phoneNumberTxt;
    @FXML
    private TextField NatinnalityTxt;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextField CostHourtxt;
    @FXML
    private ComboBox<String> COmboGender;
    @FXML
    private ComboBox<String> ComboMarital;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        COmboGender.getItems().addAll("female" , "male");
        ComboMarital.getItems().addAll("maried" , "Single");
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
            Context context;
            
            context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
            
           User u = RH_add_liste_attenteController.getUser();
            System.out.println(u);
        if(u==null){
               System.out.println("select user");
           }
            System.out.println( RH_add_liste_attenteController.getUser());
            IdTxt.setText(u.getCin());
            FrstnaemTxt.setText(u.getFirstname());
            LastNameTxt.setText(u.getLastname());
            EmailTxt.setText(u.getEmail());
            phoneNumberTxt.setText(Long.toString(u.getPhone_number()));
            NatinnalityTxt.setText(u.getNationality());
            CostHourtxt.setText(Float.toString(u.getCost_h()));
            datepicker.setValue(LocalDate.parse(u.getDate_birth()));
            
            
            
            
            
            
            
            
        } catch (NamingException ex) {
            Logger.getLogger(PopUpUpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void DoneUpade(ActionEvent event) throws NamingException {
                    String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
            Context context;
            
            context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
            
           User u = RH_add_liste_attenteController.getUser();
        u.setFirstname(FrstnaemTxt.getText());
        u.setCin(IdTxt.getText());
        u.setLastname(LastNameTxt.getText());
        u.setEmail(EmailTxt.getText());
        u.setPhone_number(Integer.parseInt(phoneNumberTxt.getText()));
        u.setCost_h(Float.parseFloat(CostHourtxt.getText()));
        u.setDate_birth(datepicker.getValue().toString());
        proxy.update(u);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refused ");
        alert.setHeaderText("demand refused :( ");
        alert.showAndWait();
        
        FrstnaemTxt.setText("");
           IdTxt.setText("");
        LastNameTxt.setText("");
        EmailTxt.setText("");
            CostHourtxt.setText("");
//            
//            RH_add_liste_attenteController o = new RH_add_liste_attenteController();
//            o.init();
//                
    }


}
