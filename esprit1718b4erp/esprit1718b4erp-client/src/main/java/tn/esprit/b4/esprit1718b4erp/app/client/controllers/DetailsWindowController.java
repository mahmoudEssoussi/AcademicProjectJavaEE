/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote;

/**
 * FXML Controller class
 *
 * @author chihi
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private Label detailsprojectName;
    @FXML
    private Label detailsprojectIdentity;
    @FXML
    private TextField detailsType;
    @FXML
    private TextField Detailsstatus;
    @FXML
    private TextField DetailsPriority;
    @FXML
    private TextField DetailsPrice;
    @FXML
    private TextArea DetailsDescription;
    @FXML
    private DatePicker DetailsStartDate;
    @FXML
    private DatePicker DetailsDeadLine;
    @FXML
    private Button DetailsClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context1;
		try {
			context1 = new InitialContext();
			RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context1.lookup(jndiName1);
			RP_Projet p =new RP_Projet();
			p=rpProjetServiceRemote.getProjectById(RPConsultProjectsController.getIdSelctedproject());
		    
			detailsprojectName.setText(p.getNomProjet());
			detailsprojectIdentity.setText(p.getIdentifiantClient());
			detailsType.setText(p.getType());
			Detailsstatus.setText(p.getStatus());
			DetailsPriority.setText(p.getPriority());
			DetailsPrice.setText(Float.toString(p.getPrice()));
			DetailsDescription.setText(p.getDescription());
			DetailsStartDate.setPromptText(p.getPlannedStartDate());
			DetailsDeadLine.setPromptText(p.getPlannedEndDate());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		detailsprojectName.setDisable(true);
		detailsprojectIdentity.setDisable(true);
		detailsType.setDisable(true);
		Detailsstatus.setDisable(true);
		DetailsPriority.setDisable(true);
		DetailsPrice.setDisable(true);
		DetailsDescription.setDisable(true);
		DetailsStartDate.setDisable(true);
		DetailsDeadLine.setDisable(true);
		
    	
    	
    	DetailsPrice.setText(Integer.toString(RPConsultProjectsController.getIdSelctedproject()));
    	
    	
    }    

    @FXML
    private void DetailsClose_Click(ActionEvent event) {
    }
    
}
