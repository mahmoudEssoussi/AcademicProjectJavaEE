/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.SendMailTLS;
import tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class ProduitprojController implements Initializable {
	RP_Projet sujetSelected;
    @FXML
    private Button back;
    ObservableList<RP_Projet > datasujet;
    @FXML
    private TableView<RP_Projet> projets;
    @FXML
    private Button addp;
    @FXML
    private Button Deletp;
    @FXML
    private Button Updatep;
    @FXML
    private TableColumn<?, ?> Projectname;
    @FXML
    private TableColumn<?, ?> decription;
    @FXML
    private TableColumn<?, ?> Debutdate;
    @FXML
    private TableColumn<?, ?> EndingDate;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private TextField pn;
    @FXML
    private TextField dd;
    @FXML
    private TextField ed;
    @FXML
    private Button sendmail;
    @FXML
    private TextField desc;
    @FXML
    private TextField pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<RP_Projet> data = FXCollections.observableArrayList();
        datasujet = FXCollections.observableArrayList();

 
        Projectname.setCellValueFactory(new PropertyValueFactory<>("nomProjet"));
       
        decription.setCellValueFactory(new PropertyValueFactory<>("description"));
       
        Debutdate.setCellValueFactory(new PropertyValueFactory<>("plannedStartDate"));
        EndingDate.setCellValueFactory(new PropertyValueFactory<>("plannedEndDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
		Context context1 = null;
		try {
			context1 = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ClientServiceRemote c = null;
		try {
			c = (ClientServiceRemote)  context1.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ch=Integer.toString(EspaceCRMController.y);
        List< RP_Projet> lstSujet = c.clientprojet(ch);
        lstSujet.stream().forEach((user) -> {
            datasujet.add(( RP_Projet) user);
        });

        projets.getItems().addAll(datasujet);

        projets.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println(newSelection);
            if (newSelection != null) {
                sujetSelected = (RP_Projet) newSelection;

            }

        });}

    @FXML
    private void back(ActionEvent event) throws IOException {
         Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceCRM.fxml"));
          Scene espaceResProjectsScene=new Scene(espaceResProjects);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(espaceResProjectsScene);
          window.show(); 
    }

    @FXML
    private void addp(ActionEvent event) throws NamingException {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
     			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
 		Context context1=new InitialContext();
     	ClientServiceRemote c=(ClientServiceRemote)  context1.lookup(jndiName);
     	RP_Projet p= new RP_Projet();
     	p.setNomProjet(pn.getText());
     	Date d=new Date();
     	p.setCreationDate(d.toString());
     	p.setPlannedStartDate(dd.getText());
     	p.setPlannedEndDate(ed.getText());
     	p.setIdentifiantClient(Integer.toString(EspaceCRMController.y));
     	p.setDescription(desc.getText());
     	p.setPrice(Float.parseFloat(pr.getText()));
     	c.ajoutprojetclient(p);
     	refresh();
     	
    }

    @FXML
    private void Deletep(ActionEvent event) throws NamingException {

    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
    	
		Context context1=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context1.lookup(jndiName1);
		Context context=new InitialContext();
    	ClientServiceRemote c=(ClientServiceRemote) context.lookup(jndiName);
    	RP_Projet p =new RP_Projet();
        ObservableList<RP_Projet > allsujet=projets.getItems();
       p=projets.getSelectionModel().getSelectedItem();
       
       rpProjetServiceRemote.delete(p);
       
      
        allsujet.remove(p);
        refresh();
    }
    public void refresh() {
        projets.getItems().clear();
        datasujet.clear();
        String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
		Context context1 = null;
		try {
			context1 = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ClientServiceRemote c = null;
		try {
			c = (ClientServiceRemote)  context1.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ch=Integer.toString(EspaceCRMController.y);
        List< RP_Projet> lstSujet = c.clientprojet(ch);;
        lstSujet.stream().forEach((user) -> {
            datasujet.add((RP_Projet) user);
        });
        

        projets.getItems().addAll(datasujet);
    }


    @FXML
    private void Updatep(ActionEvent event) throws NamingException {
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
    	
		Context context1=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context1.lookup(jndiName1);
		RP_Projet p=new RP_Projet();
		
	       p=projets.getSelectionModel().getSelectedItem();
	       p.setNomProjet(pn.getText());
	     	Date d=new Date();
	     	p.setCreationDate(d.toString());
	     	p.setPlannedStartDate(dd.getText());
	     	p.setPlannedEndDate(ed.getText());
	     	p.setIdentifiantClient(Integer.toString(EspaceCRMController.y));
	     	p.setDescription(desc.getText());
	     	p.setPrice(Float.parseFloat(pr.getText()));
	     	rpProjetServiceRemote.update(p);
	     	refresh();
	        
	                    refresh();
    }

    @FXML
    private void sendm(ActionEvent event) throws NamingException {
    	int i=0;
    	Date d=new Date();
    	Date date=new Date();
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
     			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
 		Context context1=new InitialContext();
     	ClientServiceRemote c=(ClientServiceRemote)  context1.lookup(jndiName);
    	String ch=Integer.toString(EspaceCRMController.y);
        List< RP_Projet>proj = c.clientprojet(ch);
        for(i=0;i<proj.size();i++)
        {
        	String ch2;
        	String ch3 = null;
        	String ch4 = null;
        	int j=0;
        	String ch1=proj.get(i).getPlannedEndDate();
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
          

            try {

                date = formatter.parse(ch1);
                

            } catch (ParseException e) {
                e.printStackTrace();
            }

        
        	if(d.getMonth()==date.getMonth())
        	{
        		if(d.getYear()==date.getYear())
        		{
        			if((d.getDay()-date.getDay())==3)
        			{
        				String mail="mohamedmalek.barkati@esprit.tn";
        		     	  String pass="Mm09615363";
        		     	  String[] to={"mohamedmalek.barkati@esprit.tn"};
        		     	  String subject="Projet terminé";
        		     	  String body="We're sending  you this mail to inform you that your projet will end in "+proj.get(i).getPlannedEndDate();
        		     	  SendMailTLS sendMail = new SendMailTLS();
        		     	  sendMail.sendFromGMail(mail,pass,to,subject,body);
        			}
        		}
        	}
        	}
    }
    
}
