/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.time.LocalDate;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.StackPane;

//import antlr.collections.List;
import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.EtatCongé;
import tn.esprit.b4.esprit1718b4erp.entities.TypeCongé;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class CongéController implements Initializable {
    public static JFXDialog dialogSkill;
    @FXML
    private DatePicker DateBEgin;
    @FXML
    private DatePicker DateEnd;
    @FXML
    private ComboBox<TypeCongé> txtType;
    @FXML
    private TextArea txtComment;

    @FXML
    private TableView<Congé> tabCongeAnswer;
    @FXML
    private TableColumn<Congé, String> colDateBegin;
    @FXML
    private TableColumn<Congé, String> colDateEnd;
    @FXML
    private TableColumn<Congé, String> colType;
    @FXML
    private TableColumn<Congé, String> colAnswer;
    @FXML
    private Button btnDemandeCongé;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnPopUp;
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            txtType.getItems().setAll(TypeCongé.annuel, TypeCongé.maladie , TypeCongé.maternité);
            String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
        	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
          		
            Context context=new InitialContext();
            Context context1=new InitialContext();

            UserServiceRemote userServiceRemote = (UserServiceRemote) context1.lookup(jndiName1);
            CongéServiceRemote  proxy =(CongéServiceRemote) context.lookup(jndiName);
                List<Congé> c = userServiceRemote.findCongébyIdUser(LoginGUIController.getIdUserConnecte());
            colDateBegin.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            colDateEnd.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colAnswer.setCellValueFactory(new PropertyValueFactory<>("etat"));
            
             
          
            
            ObservableList<Congé> items = FXCollections.observableArrayList(c);
            tabCongeAnswer.setItems(items);
            
        } catch (NamingException ex) {
            System.out.println("Error naming exception");
        }

    }    

    @FXML
    private void onClickDemandCongé(ActionEvent event) throws NamingException, InstantiationException, IllegalAccessException {
        String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
      		
        Context context=new InitialContext();
        Context context1=new InitialContext();

        UserServiceRemote userServiceRemote = (UserServiceRemote) context1.lookup(jndiName1);
        CongéServiceRemote  proxy =(CongéServiceRemote) context.lookup(jndiName);
		        Congé c = new Congé();
              java.sql.Date dateBegin = java.sql.Date.valueOf(DateBEgin.getValue());
              java.sql.Date dateEnd = java.sql.Date.valueOf(DateEnd.getValue());

		        c.setDateDebut(dateBegin);
	            c.setDateFin(dateEnd);
	            c.setTypeCongé(txtType.getValue());
	            c.setUser(userServiceRemote.getUserById(LoginGUIController.getIdUserConnecte()));
	            c.setComment(txtComment.getText());
	            proxy.save(c);
	         //   c.setBinaryStream();
               	//Alert alert = new Alert(AlertType.INFORMATION);
	    		//alert.setTitle("Leave Demand");
	    		//alert.setHeaderText("Succesful :) ");
	    		//alert.showAndWait();
			
                    dateBegin.setTime(0);
                    dateEnd.setTime(0);
                    txtType.setValue(null);
                    txtComment.setText("");
	           
    }

    @FXML
    private void OnCLickBack(ActionEvent event) throws IOException {
           Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/eng_interface.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show(); 
    }

    @FXML
    private void OnClickPopUp(ActionEvent event) throws IOException, NamingException {
        
//        Pane child = null;
//        FXMLLoader.load(getClass().getResource("/fxml/PopUpRepCon.fxml"));
//        JFXDialogLayout dialogLayout = new JFXDialogLayout();   
//        dialogLayout.getChildren(child);
//        dialogSkill = new JFXDialogLayout(congePane,dialogLayout,JFXDialog.DialogTransition.CENTER);
//        dialogSkill.show();

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
            
        EtatCongé etat = null ;
        System.out.println(congé.getEtat());
if((congé.getEtat().toString()).equals("accepted")){
        Pane child = null;
	
			child = FXMLLoader.load(getClass().getResource("/fxml/PopUpRepConOK.fxml"));
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
    }else{
   Pane child = null;
	
			child = FXMLLoader.load(getClass().getResource("/fxml/PopUpRepConNope.fxml"));
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.getChildren().add(child);
		dialogSkill = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		dialogSkill.show();
    }
    }
    }
}
