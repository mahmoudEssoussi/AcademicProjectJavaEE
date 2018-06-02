/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.SendMailTLS;
import tn.esprit.b4.esprit1718b4erp.services.ClientService;
import tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class EspaceCRMController implements Initializable {
static int y;
    @FXML
    private TextField Adresse;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Email;
    @FXML
    private TextField Numt;
    @FXML
    private TextField Numc;
    @FXML
    private Button addClient;
    @FXML
    private TableView<Client> clients;
    ObservableList<Client> datasujet;
    @FXML
    private TableColumn<?, ?> Nomc;
    @FXML
    private TableColumn<?, ?> EmailC;
    Client sujetSelected;
    @FXML
    private TableColumn<?, ?> Adressec;
    @FXML
    private TableColumn<?, ?> Numtelc;
    @FXML
    private TableColumn<?, ?> Numcomptec;
    @FXML
    private Button supprimer;
    @FXML
    private Button logoutc;
    @FXML
    private Button modifier;
    @FXML
    private Button searche;
    @FXML
    private Button searcha;
    @FXML
    private TextField sa;
    @FXML
    private TextField se;
    @FXML
    private Button show;
    @FXML
    private TableColumn<?, ?> nbprojects;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      int i=0;
        ObservableList<Client> data = FXCollections.observableArrayList();
        datasujet = FXCollections.observableArrayList();

 
        Nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
        EmailC.setCellValueFactory(new PropertyValueFactory<>("email"));
       
        Adressec.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Numtelc.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        Numcomptec.setCellValueFactory(new PropertyValueFactory<>("numcompte"));
        nbprojects.setCellValueFactory(new PropertyValueFactory<>("nbprojet"));

        
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
        List<Client> lstSujet = c.afficherclient();
        for (i=0;i<lstSujet .size();i++)
        {
        	lstSujet.get(i).setNbprojet(c.calculnbprojet(Integer.toString(lstSujet.get(i).getId_client())));
        }
        lstSujet.stream().forEach((user) -> {
            datasujet.add((Client) user);
        });

        clients.getItems().addAll(datasujet);
        clients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println(newSelection);
            if (newSelection != null) {
                sujetSelected = newSelection;

            }

        });
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws NamingException {
        
    	 int i;
     	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
     			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
 		Context context1=new InitialContext();
     	ClientServiceRemote c=(ClientServiceRemote)  context1.lookup(jndiName);
     	Client c1=new Client();
     	c1.setNom(Nom.getText());
     	c1.setAdresse(Adresse.getText());
     	c1.setEmail(Email.getText());  
     	c1.setNumtel(Numt.getText()); 
     	c1.setNumcompte(Numc.getText());
     	List<Client> t ;
     	t= c.afficherclient();
     	for(i=0;i<t.size();i++)
     	{
     		if((t.get(i).getEmail()).equals(c1.getEmail()))
     		{
     			break;
     		}
     		if((t.get(i).getAdresse()).equals(c1.getAdresse()))
     		{
     			break;
     		}
     		if((t.get(i).getNom()).equals(c1.getNom()))
     		{
     			break;
     		}
     		if((t.get(i).getNom()).equals(c1.getNom()))
     		{
     			break;
     		}
     		if((t.get(i).getNumtel()).equals(c1.getNumtel()))
     		{
     			break;
     		}
     		if((t.get(i).getNumcompte()).equals(c1.getNumcompte()))
     		{
     			break;
     		}
     		
     		
     	}
     	if(i==t.size())
     	{System.out.println("ya bagra");
     	String mail="mohamedmalek.barkati@esprit.tn";
     	  String pass="Mm09615363";
     	  String[] to={"mohamedmalek.barkati@esprit.tn"};
     	  String subject="Client ajouté";
     	  String body="We're sending  you this mail to inform you that the client has been added to our contacts and here are the client information'"+" Name= "+c1.getNom()+" Adress ="+c1.getAdresse()+" Email ="+c1.getEmail()+" Telephone number= "+c1.getNumtel()+" Account ="+c1.getNumcompte();
     	  SendMailTLS sendMail = new SendMailTLS();
     	  sendMail.sendFromGMail(mail,pass,to,subject,body);
     		c.save(c1);
     		}
     	else {System.out.println("ya bhim");}
                     refresh();
    }

    @SuppressWarnings("restriction")
	@FXML
    private void supprimer(ActionEvent event) throws NamingException {

    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
		Context context=new InitialContext();
    	ClientServiceRemote c=(ClientServiceRemote) context.lookup(jndiName);
        Client c1 =new Client();
        ObservableList<Client> allsujet=clients.getItems();
       c1=clients.getSelectionModel().getSelectedItem();
       c.delete(c1);
       
      
        allsujet.remove(c1);
        
    }

    @FXML
    private void logoutc(ActionEvent event) throws IOException {
    	  Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/LoginGUI.fxml"));
          Scene espaceResProjectsScene=new Scene(espaceResProjects);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(espaceResProjectsScene);
          window.show(); 
    }

    @FXML
    private void modifier(ActionEvent event) throws NamingException {
        String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
		Context context=new InitialContext();
    	ClientServiceRemote c=(ClientServiceRemote) context.lookup(jndiName);
        Client c1 =new Client();
        ObservableList<Client> allsujet=clients.getItems();
       c1=clients.getSelectionModel().getSelectedItem();
           	c1.setNom(Nom.getText());
    	c1.setAdresse(Adresse.getText());
    	c1.setEmail(Email.getText());  
    	c1.setNumtel(Numt.getText()); 
    	c1.setNumcompte(Numc.getText());
        System.out.println(c1);
        c.update(c1);
                    refresh();
       
    }
        
    public void refresh() {
        clients.getItems().clear();
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
        List<Client> lstSujet = c.findAll();
        lstSujet.stream().forEach((user) -> {
            datasujet.add((Client) user);
        });
        

        clients.getItems().addAll(datasujet);
    }

    @FXML
    private void searche(ActionEvent event) throws NamingException {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
		Context context1=new InitialContext();
    	ClientServiceRemote c=(ClientServiceRemote)  context1.lookup(jndiName);
    	String ch =se.getText();
    	  clients.getItems().clear();
    	 ObservableList<Client> data = FXCollections.observableArrayList();
         datasujet = FXCollections.observableArrayList();

  
         Nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
         EmailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        
         Adressec.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         Numtelc.setCellValueFactory(new PropertyValueFactory<>("numtel"));
         Numcomptec.setCellValueFactory(new PropertyValueFactory<>("numcompte"));
         List<Client> lstSujet = c.searchemail(ch);
         lstSujet.stream().forEach((user) -> {
             datasujet.add((Client) user);
         });

         clients.getItems().addAll(datasujet);
         clients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
             System.out.println(newSelection);
             if (newSelection != null) {
                 sujetSelected = newSelection;

             }

         });
    }

    @FXML
    private void searcha(ActionEvent event) throws NamingException {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/ClientService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote";
		Context context1=new InitialContext();
    	ClientServiceRemote c=(ClientServiceRemote)  context1.lookup(jndiName);
    	String ch =sa.getText();
    	  clients.getItems().clear();
    	 ObservableList<Client> data = FXCollections.observableArrayList();
         datasujet = FXCollections.observableArrayList();

  
         Nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
         EmailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        
         Adressec.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         Numtelc.setCellValueFactory(new PropertyValueFactory<>("numtel"));
         Numcomptec.setCellValueFactory(new PropertyValueFactory<>("numcompte"));
         List<Client> lstSujet = c.searchname(ch);
         lstSujet.stream().forEach((user) -> {
             datasujet.add((Client) user);
         });

         clients.getItems().addAll(datasujet);
         clients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
             System.out.println(newSelection);
             if (newSelection != null) {
                 sujetSelected = newSelection;

             }

         });
    }

    @FXML
    private void show(ActionEvent event) throws IOException {
    	y=clients.getSelectionModel().getSelectedItem().getId_client();
         Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/Produitproj.fxml"));
          Scene espaceResProjectsScene=new Scene(espaceResProjects);
          Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(espaceResProjectsScene);
          window.show(); 
    }
    
}
