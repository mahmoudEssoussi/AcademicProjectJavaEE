/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.SendMailTLS;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author chihi
 */
public class ADConsultProjetController implements Initializable  {
	
	
	
	
	
	
	
	
	
	
	
	
	

    @FXML
    private TableView<RP_Projet> ADtable_projet;
    @FXML
    private TableColumn<RP_Projet, String> identifiantProjet;
    @FXML
    private TableColumn<RP_Projet, String> nomProjet;
    @FXML
    private TableColumn<RP_Projet, String> status;
    @FXML
    private TableColumn<RP_Projet, String> type;
    @FXML
    private TableColumn<RP_Projet, String> plannedStartDate;
    @FXML
    private TableColumn<RP_Projet, String> plannedEndDate;
    @FXML
    private TableColumn<RP_Projet, Float> price;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private ComboBox<String> ProjectManagerComboBox;
    @FXML
    private ComboBox<String> priorityComboBox;
    @FXML
    private Label creationDate;
    @FXML
    private Label PMName;
    @FXML
    private Button updateNewProject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        identifiantProjet.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("identifiantProjet") );
         nomProjet.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("nomProjet") );
          status.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("status")); 
         type.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("type") );
         plannedStartDate.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("plannedStartDate") );
         plannedEndDate.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("plannedEndDate") );

         price.setCellValueFactory(new PropertyValueFactory<RP_Projet, Float>("price") );
         
                 
        try {
            ADtable_projet.setItems(getAllProjets());
        } catch (NamingException ex) {
            Logger.getLogger(ADConsultProjetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        /*  priority.getItems().addAll("High","Normal","Low");
        priority.setValue("Normal");
        actualEndDate.setDisable(true);*/
        
        priorityComboBox.getItems().addAll("High","Normal","Low");
        priorityComboBox.setValue("Normal");
        
        statusComboBox.getItems().addAll("Wait","accepted","rejected");
        statusComboBox.setValue("Wait");
        
        try {
			ProjectManagerComboBox.setItems(this.getRPUsersName());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ProjectManagerComboBox.setValue("");
        
        priorityComboBox.setDisable(true);
        ProjectManagerComboBox.setDisable(true);
        
        
        
       
    }  
    
	public ObservableList<RP_Projet> getAllProjets() throws NamingException
	{
		String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context.lookup(jndiName);
		
		
            
            ObservableList<RP_Projet> projets=FXCollections.observableArrayList();
           // projets=(ObservableList<RP_Projet>) rpProjetServiceRemote.findAll();
            
            List l = new ArrayList<>();
    		l=rpProjetServiceRemote.findAll();
    		
    		for(int i=0;i<l.size();i++)
    		{
    			RP_Projet p=(RP_Projet) l.get(i);
    			projets.add(p);
    		}
    		
    			
    			
    			
            
            return projets;
		
	}
	
	public ObservableList<String> getRPUsersName() throws NamingException
	{
		String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);
		
            
            ObservableList<String> rpusersName=FXCollections.observableArrayList();
           // projets=(ObservableList<RP_Projet>) rpProjetServiceRemote.findAll();
            
            List l = new ArrayList<>();
    		l=userServiceRemote.getAllRPUsers();
    		
    		for(int i=0;i<l.size();i++)
    		{
    			User u=(User) l.get(i);
    			rpusersName.add(u.getFirstname()+" "+u.getLastname());
    			System.out.println(u.getFirstname());
    		}
    		
    			
    			
    			
            
            return rpusersName;
		
	}

    @FXML
    private void selectedRowAction(MouseEvent event) throws NamingException {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context.lookup(jndiName);
		
		String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context1=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context1.lookup(jndiName1);
		
		
        RP_Projet p =new RP_Projet();
        
        User u= new User();
        
       
        p=ADtable_projet.getSelectionModel().getSelectedItem();
        u=userServiceRemote.getUserById(p.getCreatedBy());
        PMName.setText(u.getFirstname()+" "+u.getLastname());
        creationDate.setText(p.getCreationDate());

        
        
    }

    @FXML
    private void updateNewProject_Click(ActionEvent event) throws NamingException {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context.lookup(jndiName);
		
		String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context1=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context1.lookup(jndiName1);
		

		
		
		RP_Projet p =new RP_Projet();
		p=ADtable_projet.getSelectionModel().getSelectedItem();
		p.setStatus("Not Started");
		p.setPriority( priorityComboBox.getValue());
		p.setProjectManager( ProjectManagerComboBox.getValue());
		rpProjetServiceRemote.update(p);
		
		List l = new ArrayList<>();
		l=userServiceRemote.findAll();
		
		for(int i=0;i<l.size();i++)
		{
			User u=(User) l.get(i);
			if(p.getProjectManager().equals(u.getFirstname()+" "+u.getLastname()))
					{
				System.out.println(u);
				String mail="majdichihir@gmail.com";
			   	  String pass="koloma123";
			   	  String[] to={u.getEmail()};
			   	  String subject="new assignment";
			   	  String body="you have been assigned to a new project"
			   	  +" project Name= "+p.getNomProjet()+" project identity ="+p.getIdentifiantProjet()+" planned start date ="+p.getPlannedStartDate()+" deadLine= "+p.getPlannedEndDate();
			   	  SendMailTLS sendMail = new SendMailTLS();
			   	  sendMail.sendFromGMail(mail,pass,to,subject,body);
				
					}
			
		}	
		
		

   		
		
		
    }

    @FXML
    private void StatuscomboBox_Click(ActionEvent event) {
       if( statusComboBox.getValue().equals("accepted"))
       {
         priorityComboBox.setDisable(false);
        ProjectManagerComboBox.setDisable(false);  
       }
       else
       {
    	   priorityComboBox.setDisable(true);
           ProjectManagerComboBox.setDisable(true);
           statusComboBox.setValue("Wait");
           ProjectManagerComboBox.setValue("");
       }
    }

    @FXML
    private void BackClick(ActionEvent event) throws IOException {
            Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/EspaceAdmin.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    
    }

    
}
