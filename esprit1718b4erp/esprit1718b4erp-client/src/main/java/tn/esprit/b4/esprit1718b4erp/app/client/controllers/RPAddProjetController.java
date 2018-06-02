/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.awt.peer.SystemTrayPeer;
import java.io.IOException;
import java.net.URL;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author chihi
 */
public class RPAddProjetController implements Initializable {

    @FXML
    private TextField identifiantProjet;
    @FXML
    private TextField identifiantClient;
    @FXML
    private TextField nomProjet;
    
    @FXML
    private TextArea description;
    @FXML
    private DatePicker plannedStartDate;
    @FXML
    private DatePicker plannedEndDate;
    
    @FXML
    private Button RP_Add_Projet;
    @FXML
    private TextField type;
    @FXML
    private TextField price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      /*  priority.getItems().addAll("High","Normal","Low");
        priority.setValue("Normal");
        actualEndDate.setDisable(true);*/
    	//plannedStartDate
    	
    }    

    @FXML
    private void RP_Add_ProjetClick(ActionEvent event) throws NamingException {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
    	
		Context context=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context.lookup(jndiName);
		
		RP_Projet projet=new RP_Projet();
		try
		{
			LocalDate psd=plannedStartDate.getValue();
			LocalDate ped=plannedEndDate.getValue();
			if(psd.isBefore(LocalDate.now()))
			{
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setHeaderText("invalid date!  ");
		        alert.showAndWait();
			}
			else if((plannedEndDate.getValue().isEqual(psd))||(plannedEndDate.getValue().isBefore(psd)))
			{
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setHeaderText("invalid date! ");
		        alert.showAndWait();
			}
			else if(identifiantProjet.getText().isEmpty())
			{
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setHeaderText("identity is empty! ");
		        alert.showAndWait();
			}
			else if(nomProjet.getText().isEmpty())
			{
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setHeaderText("name is empty!");
		        alert.showAndWait();
			}
			else if(description.getText().isEmpty())
			{
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setHeaderText("description is empty!");
		        alert.showAndWait();
			}
			else if(price.getText().isEmpty())
			{
				
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setHeaderText("price is empty! ");
		        alert.showAndWait();
		        
			}
			
			
			
			else
			{
				try
				{
					Float f=Float.parseFloat(price.getText());
				}
				catch(Exception e)
				{
					Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setHeaderText("price has to be a number! ");
			        alert.showAndWait();
				}
			
		projet.setIdentifiantProjet(identifiantProjet.getText());
		projet.setIdentifiantClient(identifiantClient.getText());
		projet.setNomProjet(nomProjet.getText());
		projet.setCreatedBy(LoginGUIController.getIdUserConnecte());
		//projet.setProjectManager(projetManager.getText());
		projet.setPlannedStartDate(plannedStartDate.getValue().toString());
		projet.setPlannedEndDate(plannedEndDate.getValue().toString());
		projet.setStatus("wait");
		//projet.setPriority(priority.getValue());
		projet.setPriority("Normal");
		projet.setDescription(description.getText());
		//projet.setActualEndDate(actualEndDate.getValue().toString());
		projet.setType(type.getText());
		projet.setPrice(Float.parseFloat(price.getText()));
		projet.setCreationDate(LocalDateTime.now().toString());
		projet.setProjectManager("");
		projet.setDaysBetween(DAYS.between(LocalDate.now(), ped));
		//System.out.println(DAYS.between(psd, ped));
		
		System.out.println(LoginGUIController.getIdUserConnecte());
		System.out.println(projet);
		rpProjetServiceRemote.save(projet);
		System.out.println("projet ajouté");
		
		 Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succesful :) ");
        alert.showAndWait();
		
		
			}
		
		}
		catch(Exception e)
		{
			System.out.println("field is missing!");
		}
		
    }

    @FXML
    private void backClick(ActionEvent event) throws IOException {
        Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceResProjects.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }
    
}
