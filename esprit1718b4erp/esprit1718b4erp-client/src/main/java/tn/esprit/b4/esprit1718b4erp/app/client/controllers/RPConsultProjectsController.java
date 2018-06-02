package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import static java.time.temporal.ChronoUnit.DAYS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.controlsfx.control.textfield.TextFields;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Affectation;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Note;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Projet;
import tn.esprit.b4.esprit1718b4erp.entities.RP_Tache;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_NoteServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author chihi
 */
public class RPConsultProjectsController implements Initializable {

    @FXML
    private TextField searchTextField;
    @FXML
    private Label CreatedBy;
    @FXML
    private Label DtaeCreated;
    @FXML
    private Button Details;
    @FXML
    private TableView<RP_Note> TableListNote;
    @FXML
    private TableColumn<RP_Note, String> TaskNameColomb;
    @FXML
    private TableColumn<RP_Note, String> CreatedByColomb;
    @FXML
    private TableColumn<RP_Note, String> DateCreatedColomb;
    @FXML
    private TableColumn<RP_Note, String> PreviewNoteColomb;
    @FXML
    private TableView<RP_Tache> TableListTask;
    @FXML
    private TableColumn<RP_Tache, String> taskName;
    @FXML
    private TableColumn<RP_Tache, String> TaskType;
    @FXML
    private TableColumn<RP_Tache, String> AssignedTo;
    @FXML
    private TableColumn<RP_Tache, String> AssignedDate;
    @FXML
    private TableColumn<RP_Tache, String> DeadLine;
    @FXML
    private TableColumn<RP_Tache, String> Status;
    
   private ComboBox comboboxStatus;
    
    private static int idSelctedproject=0;
    

    
	public static int getIdSelctedproject() {
		return idSelctedproject;
	}
	public static void setIdSelctedproject(int idSelctedproject) {
		RPConsultProjectsController.idSelctedproject = idSelctedproject;
	}
	/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	TaskNameColomb.setCellValueFactory(new PropertyValueFactory<RP_Note, String>("noteTitle") );
    	CreatedByColomb.setCellValueFactory(new PropertyValueFactory<RP_Note, String>("createdBy") );
    	DateCreatedColomb.setCellValueFactory(new PropertyValueFactory<RP_Note, String>("creationDate")); 
    	PreviewNoteColomb.setCellValueFactory(new PropertyValueFactory<RP_Note, String>("previewNote") );
    	
    	taskName.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("nomTache") );
    	TaskType.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("type") );
    	AssignedDate.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("StartDate")); 
    	DeadLine.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("DeadLine") );
    	AssignedTo.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("fullName")); 
    	//Status.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("comboboxStatus") );
    	Status.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("status") );
        
    	// taskFinished.setCellValueFactory(new PropertyValueFactory<RP_Tache, String>("finished") );
        
    	// TODO
    	
    	try {
			TextFields.bindAutoCompletion(searchTextField,ProjectNameAndIdByPM());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	searchTextField.textProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				System.out.println(" Text Changed to  " + newValue + ")\n");
				String key="";
				try {
					List l =ProjectNameAndIdByPM();
					
					for(int i=0;i<l.size();i++)
		    		{
		    			key=(String) l.get(i);
		    			if(key.equals(newValue))
		    			{
		    				System.out.println(" Text Changed to  " + newValue + ")\n");
		    				System.out.println(GetProjectByNameORIdentity(key));
		    				setCreatedByAndCreationDay(GetProjectByNameORIdentity(key));
		    				TableListNote.setItems(getNotesByProjectId(GetProjectByNameORIdentity(key).getIdProjet()));
		    				TableListTask.setItems(getTachesByProjectId(GetProjectByNameORIdentity(key).getIdProjet()));
		    				setIdSelctedproject(GetProjectByNameORIdentity(key).getIdProjet());
		    				
		    				
		    				
		    				TableListTask.setRowFactory(tv -> new TableRow<RP_Tache>() {
		    			            @Override
		    			            public void updateItem(RP_Tache item, boolean empty) {
		    			                super.updateItem(item, empty) ;
		    			                if (item == null) {
		    			                    setStyle("");
		    			                } else if (item.getStatus().equals("Complete")) {
		    			                    setStyle("-fx-background-color: LimeGreen  ;");
		    			                    TableListTask.setDisable(false);
		    			                }  else if (item.getDaysBetween()<1) {
		    			                    setStyle("-fx-background-color: OrangeRed   ;");
		    			                    TableListTask.setDisable(false);
		    			                } else if (item.getDaysBetween()==1) {
		    			                    setStyle("-fx-background-color: Orange    ;");
		    			                    TableListTask.setDisable(false);
		    			                } else if ((item.getDaysBetween()==3)||(item.getDaysBetween()==2)) {
		    			                	
		    			                	setStyle("-fx-background-color: Yellow   ;");
		    			                	TableListTask.setDisable(false);
		    			                }/*else if (item.getStatus().equals("Started")) {
		    			                    setStyle("-fx-background-color: Gray ;");
		    			                    RP_TabProject.setDisable(false);
		    			                }*/ else if (item.getStatus().equals("Not Started")) {
		    			                    setStyle("-fx-background-color: LightGray ;");
		    			                    TableListTask.setDisable(false);
		    			                }  
		    			                
		    			                
		    			                else {
		    			                    setStyle("");
		    			                }
		    			            }
		    			        });
		    				
		    				
		    				
		    				
		    				
		    			}
		    			
		    		}
					
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
    	
    	
    	
    }   
    public List ProjectNameAndIdByPM() throws NamingException
    {
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context1=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context1.lookup(jndiName1);
		
    	 List l = new ArrayList<>();
    	 List l1 = new ArrayList<>();
    	 l=RPNewProjectsController.getAll_AssignedProjets();
    	 
    		for(int i=0;i<l.size();i++)
    		{
    			
    			RP_Projet p=(RP_Projet) l.get(i);
    			l1.add(p.getNomProjet());
    			l1.add(p.getIdentifiantProjet());
    		}
    	 
    	 
    	 return l1;
    }
    public RP_Projet GetProjectByNameORIdentity(String key) throws NamingException
    {
    	RP_Projet p=new RP_Projet();
    	List l = new ArrayList<>();
   	    l=RPNewProjectsController.getAll_AssignedProjets();
   	 for(int i=0;i<l.size();i++)
		{
			
			 p=(RP_Projet) l.get(i);
			 if((p.getNomProjet().equals(key))||(p.getIdentifiantProjet().equals(key)))
			 return p;
		}
    	return p;
    	
    }
    public ObservableList<RP_Note> getNotesByProjectId(int idProjet) throws NamingException
    {
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_NoteService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_NoteServiceRemote";
		Context context1=new InitialContext();
		RP_NoteServiceRemote rpNoteServiceRemote=(RP_NoteServiceRemote) context1.lookup(jndiName1);
		
        ObservableList<RP_Note> listNotes=FXCollections.observableArrayList();
        
        List l = new ArrayList<>();
   	    l=rpNoteServiceRemote.findAll();
   	 for(int i=0;i<l.size();i++)
		{
			RP_Note n=new RP_Note();
			 n=(RP_Note) l.get(i);
			 if(n.getIdProjet()==idProjet)
				 listNotes.add(n);
			 
		}
        
        return listNotes;
    }
    
    public ObservableList<RP_Tache> getTachesByProjectId(int idProjet) throws NamingException
    {
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_TacheService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote";
		Context context1=new InitialContext();
		RP_TacheServiceRemote rpTacheServiceRemote=(RP_TacheServiceRemote) context1.lookup(jndiName1);
		
        ObservableList<RP_Tache> listTaches=FXCollections.observableArrayList();
        
        //priorityComboBox.setValue("Normal");
        
        List l = new ArrayList<>();
   	    l=rpTacheServiceRemote.findAll();
   	 for(int i=0;i<l.size();i++)
		{
   		// comboboxStatus=new ComboBox();
     //   comboboxStatus.getItems().addAll("Complete");
       // CheckBox finiched=new CheckBox();
   		RP_Tache t=new RP_Tache();
			 t=(RP_Tache) l.get(i);
			 if(t.getIdProjet()==idProjet)
                         {
				            final RP_Tache ft=t;
                          //   comboboxStatus.setValue(t.getStatus());
                          //  comboboxStatus.setOnAction(e -> {
                            //	 finishedTask(ft.getIdTache());
                                // comboboxStatus.setDisable(true);
    
                         // });
                         //    if((t.getStatus().equals("Complete"))||(t.getStatus().equals("Not Started")))
                         //   	comboboxStatus.setDisable(true);
                         //    t.setComboboxStatus(comboboxStatus);
                            // t.setFinished(finiched);
				            updateStatusTask(t.getIdTache());
				      listTaches.add(t);
                         }
			 
		}
        
        return listTaches;
    }
    public void setCreatedByAndCreationDay( RP_Projet p) throws NamingException
    {
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RP_ProjetService!"
				+ "tn.esprit.b4.esprit1718b4erp.services.RP_ProjetServiceRemote";
		Context context=new InitialContext();
		RP_ProjetServiceRemote rpProjetServiceRemote=(RP_ProjetServiceRemote) context.lookup(jndiName);
		
		String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/"
				+ "UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
		Context context1=new InitialContext();
		UserServiceRemote userServiceRemote=(UserServiceRemote) context1.lookup(jndiName1);
		
		

        
        
        User u= new User();
        
       
        
        u=userServiceRemote.getUserById(p.getCreatedBy());
        CreatedBy.setText(u.getFirstname()+" "+u.getLastname());
        DtaeCreated.setText(p.getCreationDate());
        
       
        
    }
    
    public void finishedTask(int taskId) 
    {
    	
    	String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/RP_TacheService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote";
		Context context;
		
		String jndiName1="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
		Context context1;
		
		
		try {
			context = new InitialContext();
		
		RP_TacheServiceRemote rpTacheServiceRemote=(RP_TacheServiceRemote) context.lookup(jndiName);
		
		
		context1=new InitialContext();
		RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context1.lookup(jndiName1);
		RP_Tache t=rpTacheServiceRemote.getTaskById(taskId);
		RP_Affectation a=rpAffectationServiceRemote.getAffectationByIdPrjoctAndIdUser(t.getIdProjet(), t.getAssegnedTo());
         t.setStatus("Complete");
         rpTacheServiceRemote.update(t);
         if(a.getJeton()!=2)
         rpAffectationServiceRemote.delete(a);
         else
         {
        	 if(RPNewProjectsController.isfinished(a.getIdProjet()))
        		 rpAffectationServiceRemote.delete(a);
        	 else
        	 {
        		 
        	 a.setTaskStatus("Complete");
        	 rpAffectationServiceRemote.update(a);
        	 }
         }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
   
    }
    public static void updateStatusTask(int id) throws NamingException
    {
    	String jndiName="/esprit1718b4erp-ear/esprit1718b4erp-service/RP_AffectationService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_AffectationServiceRemote";
		Context context=new InitialContext();
		RP_AffectationServiceRemote rpAffectationServiceRemote=(RP_AffectationServiceRemote) context.lookup(jndiName);
		
    	
    	String jndiName1="esprit1718b4erp-ear/esprit1718b4erp-service/RP_TacheService!"
    			+ "tn.esprit.b4.esprit1718b4erp.services.RP_TacheServiceRemote";
		Context context1=new InitialContext();
		
		RP_TacheServiceRemote rpTacheServiceRemote=(RP_TacheServiceRemote) context1.lookup(jndiName1);
	RP_Tache t=	rpTacheServiceRemote.find(id);
	RP_Affectation a=rpAffectationServiceRemote.getAffectationByIdPrjoctAndIdUser(t.getIdProjet(), t.getAssegnedTo());
	System.out.println(t);
	if(t.getStatus().equals("Complete")){
		
	} else if(t.getStatus().equals("In progress"))
	{
		LocalDate now=LocalDate.now();
		t.setDaysBetween(DAYS.between(now,LocalDate.parse(t.getDeadLine()) ));
		if(t.getDaysBetween()==0)
		{
			t.setStatus("Overdue");		
		a.setTaskStatus("Overdue");
		}
	} else if(t.getStatus().equals("Not Started"))
	{
		LocalDate now=LocalDate.now();
		t.setDaysBetween(DAYS.between(now,LocalDate.parse(t.getDeadLine()) ));
		if(DAYS.between(now,LocalDate.parse(t.getStartDate()) )==0)
		{
			t.setStatus("In progress");
		    a.setTaskStatus("In progress");
		}
	}
	rpTacheServiceRemote.update(t);
	rpAffectationServiceRemote.update(a);
	
		
	
		
    	
    }

    @FXML
    private void onDetails_Click(ActionEvent event) throws IOException {
        
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DetailsWindow.fxml"));
    	Parent root1 = (Parent) fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));  
    	stage.show();
        
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
