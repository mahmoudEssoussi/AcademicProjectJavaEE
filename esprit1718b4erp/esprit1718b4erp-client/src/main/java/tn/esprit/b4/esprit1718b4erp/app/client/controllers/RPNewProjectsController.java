package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import static java.time.temporal.ChronoUnit.DAYS;

import java.awt.Color;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.scene.control.TableRow;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.hibernate.criterion.RowCountProjection;

import tn.esprit.b4.esprit1718b4erp.entities.RP_Affectation;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Tache;
import tn.esprit.b4.esprit1718b4erp.entities.SendMailTLS;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_NoteServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chihi
 */
public class RPNewProjectsController implements Initializable {

    @FXML
    private TableView<RP_Projet> RP_TabProject;
    @FXML
    private TableColumn<RP_Projet, String> identifiantProjet;
    @FXML
    private TableColumn<RP_Projet, String> nomProjet;
    @FXML
    private TableColumn<RP_Projet, String> type;
    @FXML
    private TableColumn<RP_Projet, String> plannedStartDate;
    @FXML
    private TableColumn<RP_Projet, String> plannedEndDate;
    @FXML
    private TableColumn<RP_Projet, String> description;
    @FXML
    private TextField NoteTitle;
    @FXML
    private TextArea PreviewNote;
    @FXML
    private TextField projectName;
    @FXML
    private Label status;
    @FXML
    private Button AddNoteSave;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> talents;
    @FXML
    private TableColumn<User, Integer> phoneNumber;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private Button addTeamMembers;
    @FXML
    private TableColumn<RP_Affectation, String> teamLeaderName;
    @FXML
    private Button finishTeamMembers;
    @FXML
    private TableView<User> RPTabTeamMembers;
    @FXML
    private TableView<RP_Affectation> RPTabTeamLeader;
    @FXML
    private TextField taskName;
    @FXML
    private DatePicker StartDate;
    @FXML
    private DatePicker DeadLine;
    @FXML
    private Button AddTask;
    @FXML
    private TextField Tasktype;
    @FXML
    private TableView<RP_Affectation> TabAffectedENG;
    @FXML
    private TableColumn<RP_Affectation, String> fullNameTask;
    @FXML
    private TableColumn<RP_Affectation, String> AssignedToTask;
    @FXML
    private TableColumn<RP_Affectation, String> statusTask;
    @FXML
    private Button deleteTeamMembers;
    @FXML
    private TableColumn<RP_Projet, Float> DaysLeft;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        identifiantProjet.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("identifiantProjet") );
         nomProjet.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("nomProjet") );
          description.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("status")); 
         type.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("type") );
         plannedStartDate.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("plannedStartDate") );
         plannedEndDate.setCellValueFactory(new PropertyValueFactory<RP_Projet, String>("plannedEndDate") );
         DaysLeft.setCellValueFactory(new PropertyValueFactory<RP_Projet, Float>("daysBetween") );
         
         
         name.setCellValueFactory(new PropertyValueFactory<User, String>("firstname") );
         talents.setCellValueFactory(new PropertyValueFactory<User, String>("talents")); 
         phoneNumber.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone_number") );
         email.setCellValueFactory(new PropertyValueFactory<User, String>("email") );
         
         teamLeaderName.setCellValueFactory(new PropertyValueFactory<RP_Affectation, String>("fullName") );
         
         fullNameTask.setCellValueFactory(new PropertyValueFactory<RP_Affectation, String>("fullName") );
         AssignedToTask.setCellValueFactory(new PropertyValueFactory<RP_Affectation, String>("taskName") );
         statusTask.setCellValueFactory(new PropertyValueFactory<RP_Affectation, String>("taskStatus") );
         

         

         try {
			RP_TabProject.setItems(getAll_AssignedProjets());
			RPTabTeamMembers.setItems(getAllENGUsers());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         RP_TabProject.setRowFactory(tv -> new TableRow<RP_Projet>() {
	            @Override
	            public void updateItem(RP_Projet item, boolean empty) {
	                super.updateItem(item, empty) ;
	                if (item == null) {
	                    setStyle("");
	                } else if (item.getStatus().equals("Complete")) {
	                    setStyle("-fx-background-color: LimeGreen  ;");
	                    RP_TabProject.setDisable(false);
	                }  else if (item.getDaysBetween()<1) {
	                    setStyle("-fx-background-color: MediumVioletRed    ;");
	                    RP_TabProject.setDisable(false);
	                } else if (item.getDaysBetween()==1) {
	                    setStyle("-fx-background-color: Tomato     ;");
	                    RP_TabProject.setDisable(false);
	                } else if ((item.getDaysBetween()==3)||(item.getDaysBetween()==2)) {
	                	
	                	setStyle("-fx-background-color: Yellow   ;");
	                    RP_TabProject.setDisable(false);
	                }/*else if (item.getStatus().equals("Started")) {
	                    setStyle("-fx-background-color: Gray ;");
	                    RP_TabProject.setDisable(false);
	                } else if (item.getStatus().equals("Not Started")) {
	                    setStyle("-fx-background-color: LightGray ;");
	                    RP_TabProject.setDisable(false);
	                } */ 
	                
	                
	                else {
	                    setStyle("");
	                }
	            }
	        });
         
         
         
         
         
         AddNoteSave.setDisable(true);
         addTeamMembers.setDisable(true);
         finishTeamMembers.setDisable(true);
         deleteTeamMembers.setDisable(true);
    }  
    
    public static ObservableList<RP_Projet> getAll_AssignedProjets() throws NamingException
	{
            
            
            String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);
            
            
            
		String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context1=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context1.lookup(jndiName1);
		
		
            
            ObservableList<RP_Projet> assigned_projets=FXCollections.observableArrayList();
           // projets=(ObservableList<RP_Projet>) rpProjetServiceRemote.findAll();
            User u= new User();
            u=userServiceRemote.getUserById(LoginGUIController.getIdUserConnecte());
            
            
            
            List l = new ArrayList<>();
    		l=rpProjetServiceRemote.getProjectsByPM(u.getFirstname()+" "+u.getLastname());
    		
    		for(int i=0;i<l.size();i++)
    		{
    			
    			RP_Projet p=(RP_Projet) l.get(i);
    			if(p.getStatus().equals("In progress"))
    			if(isfinished(p.getIdProjet()))
    			{
    				p.setStatus("Complete");
    				rpProjetServiceRemote.update(p);
    			}
    			if(p.getStatus().equals("Complete")==false)
    			{
    				LocalDate now=LocalDate.now();
    				p.setDaysBetween(DAYS.between(now,LocalDate.parse(p.getPlannedEndDate()) ));
    				if(p.getDaysBetween()==0)
    					p.setStatus("Overdue");
    				rpProjetServiceRemote.update(p);
    				System.out.println(p.getDaysBetween());
    			}
    			if(p.getStatus().equals("Not Started"))
    				if(isStarted(p.getIdProjet()))
    				{
        				p.setStatus("In progress");
        				rpProjetServiceRemote.update(p);
        			}
    				
    			assigned_projets.add(p);
    		}
    		
    			
    			
    			
            
            return assigned_projets;
		
	}
    
    public ObservableList<User> getAllENGUsers() throws NamingException
	{
		String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);
		

		String jndiName1="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
		Context context1=new InitialContext();
		RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context1.lookup(jndiName1);
		
            
            ObservableList<User> rpusersName=FXCollections.observableArrayList();
           // projets=(ObservableList<RP_Projet>) rpProjetServiceRemote.findAll();
            
            List l = new ArrayList<>();
    		l=userServiceRemote.getAllENGUsers();
    		
    		for(int i=0;i<l.size();i++)
    		{
    			User u=(User) l.get(i);
    			if(rpAffectationServiceRemote.isAffected(u.getId())==false)
    			rpusersName.add(u);
    			System.out.println(u.getFirstname());
    		}
    		
    			
    			
    			
            
            return rpusersName;
		
	}
    
    public ObservableList<RP_Affectation> getAffectedENG(int id) throws NamingException
	{
		/*String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);*/
		
		String jndiName1="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
		Context context1=new InitialContext();
		RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context1.lookup(jndiName1);
		
            
            ObservableList<RP_Affectation> rpusersName=FXCollections.observableArrayList();
           // projets=(ObservableList<RP_Projet>) rpProjetServiceRemote.findAll();
            
            List l = new ArrayList<>();
    		l=rpAffectationServiceRemote.findAll();
    		
    		for(int i=0;i<l.size();i++)
    		{
    			RP_Affectation a=new RP_Affectation();
    			//User u=new User();
    			a=(RP_Affectation) l.get(i);
    			//u=userServiceRemote.getUserById(a.getIdUser());
    			
    			//u.setFirstname(u.getFirstname()+" "+u.getLastname());
    			//if((u.getAffected()!=0)&&(u.getAffectedTo()==id))
    			if(a.getIdProjet()==id)
    			rpusersName.add(a);
    			//System.out.println(u.getFirstname());
    		}
    		
    			
    			
    			
            
            return rpusersName;
		
	}
    public static boolean isfinished(int id) throws NamingException
    {
    	String jndiName1="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_TacheService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote";
    	Context context1=new InitialContext();
    	RP_TacheServiceRemote rpTacheServiceRemote=(RP_TacheServiceRemote) context1.lookup(jndiName1);
          
    	  List l = new ArrayList<>();
  		l=rpTacheServiceRemote.findAll();
  		int j=0;
  		for(int i=0;i<l.size();i++)
  		{
  			
  			RP_Tache t=(RP_Tache) l.get(i);
  			if(t.getIdProjet()==id)
  			if(t.getStatus().equals("Complete")==false)
  			return false;
  		}
  		return true;
    	
    }
    public static boolean isStarted(int id) throws NamingException
    {
    	String jndiName1="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_TacheService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote";
    	Context context1=new InitialContext();
    	RP_TacheServiceRemote rpTacheServiceRemote=(RP_TacheServiceRemote) context1.lookup(jndiName1);
          
    	  List l = new ArrayList<>();
  		l=rpTacheServiceRemote.findAll();
  		int j=0;
  		for(int i=0;i<l.size();i++)
  		{
  			
  			RP_Tache t=(RP_Tache) l.get(i);
  			if(t.getIdProjet()==id)
  			if(DAYS.between(LocalDate.parse(t.getStartDate()),LocalDate.now())==0)
  			return true;
  		}
  		return false;
    	
    }

    @FXML
    private void AddNoteSave_Click(ActionEvent event) throws NamingException {
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_NoteService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_NoteServiceRemote";
		Context context1=new InitialContext();
		RP_NoteServiceRemote rpNoteServiceRemote=(RP_NoteServiceRemote) context1.lookup(jndiName1);
		
		String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context.lookup(jndiName);
		
		
    	RP_Projet p =new RP_Projet();
    	RP_Note n =new RP_Note();
    	User u=new User();
    	u=userServiceRemote.getUserById(LoginGUIController.getIdUserConnecte());

        p=RP_TabProject.getSelectionModel().getSelectedItem();
        n.setCreatedBy(u.getFirstname()+" "+u.getLastname());
        n.setIdProjet(p.getIdProjet());
        n.setCreationDate(LocalDateTime.now().toString());
        n.setNoteTitle(NoteTitle.getText());
        n.setPreviewNote(PreviewNote.getText());
        rpNoteServiceRemote.save(n);
        
    }

    @FXML
    private void RP_SelectProjet(MouseEvent event) throws NamingException  {
    	
    /*	String jndiName="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
		Context context=new InitialContext();
		RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context.lookup(jndiName);
		
    	*/
        RP_Projet p =new RP_Projet();
        p=RP_TabProject.getSelectionModel().getSelectedItem();
        projectName.setText(p.getNomProjet());
        //if(p.getStatus().equals("accepted"))
            status.setText(p.getStatus());
        AddNoteSave.setDisable(false);
        addTeamMembers.setDisable(true);
        
        
        
       ObservableList<RP_Affectation> newaffectedENGUsers=getAffectedENG(p.getIdProjet());
        
        RPTabTeamLeader.setItems(getAffectedENG(p.getIdProjet()));
        
        for(int i=0;i< newaffectedENGUsers.size();i++)
		{
        	RP_Affectation a=new RP_Affectation();
        	a=(RP_Affectation) newaffectedENGUsers.get(i);
        	
        	if(a.getJeton()==2)
        	{
        		System.out.println(a);
        		
        		//RPTabTeamLeader.getSelectionModel().select(i);
         	
        	}
		}
        
                                RPTabTeamLeader.setRowFactory(tv -> new TableRow<RP_Affectation>() {
				            @Override
				            public void updateItem(RP_Affectation item, boolean empty) {
				                super.updateItem(item, empty) ;
				                if (item == null) {
				                    setStyle("");
				                } else if (item.getJeton()==2) {
				                    setStyle("-fx-background-color: tomato;");
				                    finishTeamMembers.setDisable(false);
				                } else {
				                    setStyle("");
				                }
				            }
				        });
      
        	
        	
        

		
        		//TableRow row =(TableRow) RPTabTeamLeader.getSelectionModel().getSelectedItem();
        		//teamLeaderName.setStyle("-fx-background-color: red;");
       /* try
        {
        if(RPTabTeamLeader.getItems()!=null)
        {
        	int i=0;
        	for (Node n: RPTabTeamLeader.lookupAll("TableRow")) {
        	
        		      if (n instanceof TableRow) {
        		    	  
        		    	 TableRow  row = (TableRow) n;
        		    	  if (RPTabTeamLeader.getItems().get(i).getJeton()==2) {
        		    		  	 row.getStyleClass().add("TeamLeader");
            		    	//  row.setDisable(false);
        		            } 
        		      else {
        		              row.getStyleClass().add("NotTeamLeader");
        		             // row.setDisable(true);
        		    	  }
        		    	  i++;
        		    	  
        		    	  if (i == RPTabTeamLeader.getItems().size())
        		              break;

        		    	 
        		
        		    	  
        		    	  
        		    	  
        		    	  
        		    	  
        		    	  
        		    	 
        		      }
        		}
        }
        }
        catch(Exception e)
        {
        	System.out.println("table vide!");
        }
		
        		
        	
        		
        		
        finally	
        	
        {*/
		
        RPTabTeamMembers.setItems(getAllENGUsers());
        
       
        
        if(RPTabTeamMembers.getSelectionModel().getSelectedItem()!=null)
        	addTeamMembers.setDisable(false);
        
        if(RPTabTeamLeader.getSelectionModel().getSelectedItem()==null)
        {
        	finishTeamMembers.setDisable(true);
        	deleteTeamMembers.setDisable(true);
        }
       
        
        TabAffectedENG.setItems(this.getAffectedENG(p.getIdProjet()));
        //}

    }

    @FXML
    private void addTeamMembers_Click(ActionEvent event) throws NamingException {
   
    	RP_Projet p =new RP_Projet();
        p=RP_TabProject.getSelectionModel().getSelectedItem();
        User u =new User();
        u=RPTabTeamMembers.getSelectionModel().getSelectedItem();
        RP_Affectation a=new RP_Affectation();
        a.setIdProjet(p.getIdProjet());
        a.setIdUser(u.getId());
        a.setJeton(1);
        a.setFullName(u.getFirstname()+" "+u.getLastname());
        
       // rpAffectationServiceRemote.save(a);
        
       /* u.setAffected(1);
        u.setAffectedTo(p.getIdProjet());
        userServiceRemote.update(u);*/
        
        
        ObservableList<User>allENGUsers =RPTabTeamMembers.getItems();

        allENGUsers.remove(u);
        
     
        RPTabTeamLeader.getItems().add(a);
        
        
        
        
        
        
        
        
    }

    @FXML
    private void finishTeamMembers_Click(ActionEvent event) throws NamingException {
    /*	String jndiName="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
		Context context=new InitialContext();
		RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context.lookup(jndiName);
		
		  RP_Affectation a=new RP_Affectation();
	        a=RPTabTeamLeader.getSelectionModel().getSelectedItem();
	        a.setJeton(2);
	        rpAffectationServiceRemote.update(a);*/


    	 	String jndiName="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
		+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
Context context=new InitialContext();
RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context.lookup(jndiName);
       

        RP_Projet p =new RP_Projet();
        p=RP_TabProject.getSelectionModel().getSelectedItem();
        RP_Affectation a=rpAffectationServiceRemote.getAffectationTeamLeader(p.getIdProjet());
        //System.err.println(a);



     ObservableList<RP_Affectation>newaffectedENGUsers=RPTabTeamLeader.getItems();
     int j=0;
      for(int i=0;i< newaffectedENGUsers.size();i++)
		{
    	  
     	if(rpAffectationServiceRemote.isAffected(newaffectedENGUsers.get(i).getIdUser())==false)
     	{
     		if((RPTabTeamLeader.getSelectionModel().getSelectedItem()!=null)&&(RPTabTeamLeader.getSelectionModel().getSelectedItem().equals(newaffectedENGUsers.get(i))))
     		{
     			newaffectedENGUsers.get(i).setJeton(2);
     			a.setJeton(1);
     			rpAffectationServiceRemote.update(a);
     			
     		}
     		rpAffectationServiceRemote.update(newaffectedENGUsers.get(i));
     	}
     	else if(j==0)
     	{
     		if((RPTabTeamLeader.getSelectionModel().getSelectedItem()!=null)&&(RPTabTeamLeader.getSelectionModel().getSelectedItem().equals(a)==false))
     		{
     			Alert alert = new Alert(AlertType.CONFIRMATION);
     			alert.setTitle("Confirmation Dialog");
		        alert.setHeaderText("team has already a team leader!"
		        		+ "change team leader?");
		       Optional<ButtonType> action= alert.showAndWait();
		       if(action.get()==ButtonType.OK)
		       {
		    	   RPTabTeamLeader.getSelectionModel().getSelectedItem().setJeton(2);
	     			a.setJeton(1);
	     			rpAffectationServiceRemote.update(a);
	     			rpAffectationServiceRemote.update(RPTabTeamLeader.getSelectionModel().getSelectedItem());
	     			j=1;
		       }
		       
     			
     			
     		}
     	}
		}
      
      RP_TabProject.setItems(getAll_AssignedProjets());
      TabAffectedENG.setItems(getAffectedENG(p.getIdProjet()));
      RPTabTeamLeader.setItems(getAffectedENG(p.getIdProjet()));
      
      
      RPTabTeamLeader.setRowFactory(tv -> new TableRow<RP_Affectation>() {
          @Override
          public void updateItem(RP_Affectation item, boolean empty) {
              super.updateItem(item, empty) ;
              if (item == null) {
                  setStyle("");
              } else if (item.getJeton()==2) {
                  setStyle("-fx-background-color: tomato;");
              }
              else {
                  setStyle("");
              }
          }
      });
		
    }

    @FXML
    private void RPTabTeamMembers_SelectedRow(MouseEvent event) {
    	if(RP_TabProject.getSelectionModel().getSelectedItem()!=null)
         addTeamMembers.setDisable(false);
    	
    	
    	//if(RPTabTeamLeader.getSelectionModel().getSelectedItem()==null)
        	finishTeamMembers.setDisable(true);
    	
        

    }

    @FXML
    private void RPTabTeamLeader_SelectedRow(MouseEvent event) {
    	 addTeamMembers.setDisable(true);
    	finishTeamMembers.setDisable(false);
    	deleteTeamMembers.setDisable(false);
    	
    }

    @FXML
    private void AddTask_Click(ActionEvent event) throws NamingException {

	 	String jndiName="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
	 			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
Context context=new InitialContext();
RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context.lookup(jndiName);

String jndiName1="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_TacheService!"
		+ "tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote";
Context context1=new InitialContext();
RP_TacheServiceRemote rpTacheServiceRemote=(RP_TacheServiceRemote) context1.lookup(jndiName1);

String jndiName2="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
		+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
Context context2=new InitialContext();
RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context2.lookup(jndiName2);

String jndiName3="esprit1718b4erp-ear/esprit1718b4erp-service/"
		+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
Context context3=new InitialContext();
UserServiceRemote userServiceRemote=(UserServiceRemote) context3.lookup(jndiName3);



         RP_Affectation a=new RP_Affectation();
         LocalDate psd=StartDate.getValue();
         if(psd.isBefore(LocalDate.now()))
			{
				System.out.println("invalid date!");
			}
			else if((DeadLine.getValue().isEqual(psd))||(DeadLine.getValue().isBefore(psd)))
			{
				System.out.println("invalid date!");
			}
			else if(Tasktype.getText().isEmpty())
			{
				System.out.println("Tasktype is empty!");
			}
			else if(taskName.getText().isEmpty())
			{
				System.out.println("taskName is empty!");
			}
			else
			{
         a=TabAffectedENG.getSelectionModel().getSelectedItem();
         RP_Projet p =new RP_Projet();
         p=RP_TabProject.getSelectionModel().getSelectedItem();
         if((a.getTaskStatus()==null)||(a.getTaskStatus().equals("Complete")))
         {
         RP_Tache t=new RP_Tache();
         t.setNomTache(taskName.getText());
         t.setAssegnedTo(a.getIdUser());
         t.setIdProjet(a.getIdProjet());
         t.setFullName(a.getFullName());
         t.setType(Tasktype.getText());
         t.setStartDate(StartDate.getValue().toString());
         t.setDeadLine(DeadLine.getValue().toString());
         t.setStatus("Not Started");
         t.setAssignedDate(LocalDateTime.now().toString());
        a.setTaskName(taskName.getText());
        a.setTaskStatus("Not Started");
        LocalDate now=LocalDate.now();
		t.setDaysBetween(DAYS.between(now,LocalDate.parse(t.getDeadLine()) ));
		if(DAYS.between(now,LocalDate.parse(t.getStartDate()) )==0)
		{
			t.setStatus("In progress");
			a.setTaskStatus("In progress");
			
		}
        rpAffectationServiceRemote.update(a);
        rpTacheServiceRemote.save(t);
        
      /*  if(LocalDate.parse(p.getPlannedStartDate()).isEqual(StartDate.getValue()))
        {
        	System.out.println("project Started!");
        	p.setStatus("In progress");
        	rpProjetServiceRemote.update(p);
        }*/
        
        
        
        TabAffectedENG.setItems(getAffectedENG(p.getIdProjet()));
        RPTabTeamLeader.setItems(getAffectedENG(p.getIdProjet()));
        taskName.clear();
        Tasktype.clear();
        StartDate.setValue(null);
        DeadLine.setValue(null);
        
        String mail="majdichihir@gmail.com";
	   	  String pass="koloma123";
	   	  String[] to={userServiceRemote.find(a.getIdUser()).getEmail()};
	   	  String subject="new affectation";
	   	  String body="you have been affected to a new task"
	   	  +" project Name= "+p.getNomProjet()+" project identity ="+p.getIdentifiantProjet()+" planned start date ="+p.getPlannedStartDate()+" deadLine= "+p.getPlannedEndDate()
	   	+" Task Name= "+t.getNomTache()+" planned start date  ="+t.getStartDate()+" deadLine= "+t.getDeadLine();
	   	  SendMailTLS sendMail = new SendMailTLS();
	   	  sendMail.sendFromGMail(mail,pass,to,subject,body);
		
         
        
         }
         else
         {
        	 Alert alert = new Alert(AlertType.WARNING);
	        alert.setHeaderText("engineer is already affected!  ");
	        alert.showAndWait();
        	 
         }
			}

    }

    @FXML
    private void deleteTeamMembers_Click(ActionEvent event) throws NamingException {

	 	String jndiName="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
	+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
Context context=new InitialContext();
RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context.lookup(jndiName);
   
     if(rpAffectationServiceRemote.isAffected(RPTabTeamLeader.getSelectionModel().getSelectedItem().getIdUser())==false)
		{
    	 ObservableList<RP_Affectation>allNewENG =RPTabTeamLeader.getItems();

    	 allNewENG.remove(RPTabTeamLeader.getSelectionModel().getSelectedItem());
    	 RPTabTeamMembers.setItems(getAllENGUsers());
		}
     else if (RPTabTeamLeader.getSelectionModel().getSelectedItem().getJeton()==2)
     {
    	 Alert alert = new Alert(AlertType.WARNING);
	        alert.setHeaderText("you can't delete team leader! ");
	        alert.showAndWait();
 	
     }
     else if (RPTabTeamLeader.getSelectionModel().getSelectedItem().getTaskName()!=null)
     {
    	 Alert alert = new Alert(AlertType.WARNING);
	        alert.setHeaderText("enginner is affected to task!  ");
	        alert.showAndWait();
     }
     else
     {
    	 rpAffectationServiceRemote.delete(RPTabTeamLeader.getSelectionModel().getSelectedItem());
    	 
    	 ObservableList<RP_Affectation>allNewENG =RPTabTeamLeader.getItems();
         allNewENG.remove(RPTabTeamLeader.getSelectionModel().getSelectedItem());
    	 
    	 RPTabTeamMembers.setItems(getAllENGUsers());
     }
     TabAffectedENG.setItems(getAffectedENG(RPTabTeamLeader.getSelectionModel().getSelectedItem().getIdProjet()));

    }

    @FXML
    private void BackClick(ActionEvent event) throws IOException {
            Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceResProjects.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    
    }
    
}
