/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class RH_responsable_hireController implements Initializable {

    @FXML
    private TableView<Recrutement_attente> tableview;
    private ArrayList<Recrutement_attente> tab;

    private ObservableList<Recrutement_attente> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Recrutement_attente, String> col_id;
    @FXML
    private TableColumn<Recrutement_attente, String> col_firstname;
    @FXML
    private TableColumn<Recrutement_attente, String> col_lastname;
    @FXML
    private TableColumn<Recrutement_attente, String> col_email;
    @FXML
    private TableColumn<Recrutement_attente, String> col_phonenumber;
    @FXML
    private TableColumn<Recrutement_attente, Integer> col_workhour;
    @FXML
    private TableColumn<Recrutement_attente, Integer> col_hourcost;
    @FXML
    private Button btn_find;
    @FXML
    private TextField recrutementTF;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField twtWorkingHour;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtHourCst;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btn_update;
    @FXML
    private TableColumn<?, ?> col_MaritalStatus;
    @FXML
    private TableColumn<?, ?> col_Gender;
    @FXML
    private TextField txt_gender;
    @FXML
    private TextField txt_login;
    @FXML
    private TextField text_passeword;
    @FXML
    private Button btnAddUSer;
    @FXML
    private ComboBox<String> combo_dispo;
    @FXML
    private TextField txt_role;
    @FXML
    private Button btn_refresh;
    @FXML
    private TextField txtMaritalStatus;
    @FXML
    private TextField txt_DateBirth;
    @FXML
    private TableColumn<Recrutement_attente, String> col_Nationality;
    @FXML
    private TextField txt_Nationality;
    @FXML
    private TableColumn<Recrutement_attente, String> col_Nationality1;
    @FXML
    private Button btnBack;
    @FXML
    private Button btn_refuse;
    @FXML
    private ComboBox<String> comboRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        combo_dispo.getItems().addAll("available", "occupied");
        comboRole.getItems().addAll("humain ressource","responsable project","engineer","finance ressource","crm","rh_responsable" , "admin");
        Context context;
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
            context = new InitialContext();
            RHServiceRemote proxy = (RHServiceRemote) context.lookup(jndiName);
            col_id.setCellValueFactory(new PropertyValueFactory<>("cin"));
            col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            col_workhour.setCellValueFactory(new PropertyValueFactory<>("number_h"));
            col_hourcost.setCellValueFactory(new PropertyValueFactory<>("cost_h"));
            col_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            col_MaritalStatus.setCellValueFactory(new PropertyValueFactory<>("marital_status"));
             col_Nationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));



            List<Recrutement_attente> ra;
            ra = proxy.findAll();
            ObservableList<Recrutement_attente> items = FXCollections.observableArrayList(ra);
            tableview.setItems(items);

        } catch (NamingException e) {
            e.printStackTrace();
        }

        System.out.println("tableview.getSelectionModel().getSelectedItem()");

    }

    
    private void onclicked_test(ActionEvent event) throws NamingException {
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(" Portfolio Adding Error ");
		alert.setHeaderText("you have an empty field");
		alert.showAndWait();
		}
    @FXML
    private void onclicked_find(ActionEvent event) throws NamingException {

        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
        Context context = new InitialContext();
        RHServiceRemote proxy = (RHServiceRemote) context.lookup(jndiName);
        if (recrutementTF.getText().equals("")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("User searching");
            alert.setHeaderText("you have to enter a user Id");
            alert.showAndWait();

        } else {
            List<Recrutement_attente> ra = proxy.findrecrutementById(Integer.parseInt(recrutementTF.getText()));
            System.out.println(ra);
            ObservableList<Recrutement_attente> items = FXCollections.observableArrayList(ra);
            tableview.setItems(items);
            recrutementTF.setText("");
        }

    }

    @FXML
    private byte[] onClicedUpdate(ActionEvent event) throws NamingException {
      String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
        Context context = new InitialContext();
        RHServiceRemote proxy = (RHServiceRemote) context.lookup(jndiName);
        System.out.println(tableview.getSelectionModel().getSelectedItem());
        txtId.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getCin()));
        txtFirstName.setText(tableview.getSelectionModel().getSelectedItem().getFirstname());
        txtLastName.setText(tableview.getSelectionModel().getSelectedItem().getLastname());
        txtEmail.setText(tableview.getSelectionModel().getSelectedItem().getEmail());
        txt_DateBirth.setText(tableview.getSelectionModel().getSelectedItem().getDate_birth());
        txt_gender.setText(tableview.getSelectionModel().getSelectedItem().getGender());
        txtMaritalStatus.setText(tableview.getSelectionModel().getSelectedItem().getMarital_status());
        txtPhoneNumber.setText(tableview.getSelectionModel().getSelectedItem().getPhone_number());
        txt_Nationality.setText(tableview.getSelectionModel().getSelectedItem().getNationality());

        twtWorkingHour.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getNumber_h()));
        txtHourCst.setText(Float.toString(tableview.getSelectionModel().getSelectedItem().getCost_h()));
        //byte[]  pic = proxy.loadImage(tableview.getSelectionModel().getSelectedItem().getId());
                byte[]  pic = proxy.loadImage(tableview.getSelectionModel().getSelectedItem().getId());
          

        System.out.println(pic);
        return pic;
        
    }

 

    @FXML
    private void OnClickAddToTabUser(ActionEvent event) throws NamingException {

        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
        Context context = new InitialContext();
        UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
        User ra = new User();

        try {
            ra.setFirstname(txtFirstName.getText());
            ra.setLastname(txtLastName.getText());
            ra.setEmail(txtEmail.getText());
            ra.setPhone_number(Integer.parseInt(txtPhoneNumber.getText()));
            ra.setDate_birth(txt_DateBirth.getText());
            ra.setCin((txtId.getText()));
            ra.setNumber_h(Integer.parseInt(twtWorkingHour.getText()));
            ra.setCost_h(Float.parseFloat(txtHourCst.getText()));
            ra.setMarital_status(txtMaritalStatus.getText());
            ra.setNationality(txt_Nationality.getText());
            ra.setGender(txt_gender.getText());
            ra.setLogin(txt_login.getText());
            ra.setPassword(text_passeword.getText());
            ra.setRole(comboRole.getValue().toString());            
            ra.setPicture(this.onClicedUpdate(event));
                    ObservableList<Recrutement_attente> items = tableview.getItems();
                    items.remove(tableview.getSelectionModel().getSelectedItem());
            
        } catch (Exception e) {
            System.out.println("field is missing!");
        } finally {

            System.out.println(ra);
            proxy.save(ra);
           Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Recruitment waiting for the validation ");
            alert.setHeaderText("Succesful :) ");
            alert.showAndWait();
            System.out.println("rec ajouté");
        }
      
      txtFirstName.setText("");
      txtLastName.setText("");
      txtEmail.setText("");
      txtId.setText("");
      //txt_role.setText("");
      txt_login.setText("");
      txtMaritalStatus.setText("");
      txt_gender.setText("");
      txtPhoneNumber.setText("");
      txt_DateBirth.setText("");
      
    }

    @FXML
    private void onclicked_Refresh(ActionEvent event) {
        Context context;
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
            context = new InitialContext();
            RHServiceRemote proxy = (RHServiceRemote) context.lookup(jndiName);
            col_id.setCellValueFactory(new PropertyValueFactory<>("cin"));
            col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            col_workhour.setCellValueFactory(new PropertyValueFactory<>("number_h"));
            col_hourcost.setCellValueFactory(new PropertyValueFactory<>("cost_h"));
            col_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

            List<Recrutement_attente> ra;
            ra = proxy.findAll();
            ObservableList<Recrutement_attente> items = FXCollections.observableArrayList(ra);
            tableview.setItems(items);

        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onClickedBack(ActionEvent event) throws IOException {
         Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/responsableRHinterface.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

   
    @FXML
    private void onClicedRefuse(ActionEvent event) throws NamingException {
        Context context;

    	String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
         context = new InitialContext();
         RHServiceRemote proxy = (RHServiceRemote) context.lookup(jndiName);
        if (tableview.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Account Removing Error ");
            alert.setHeaderText("Account does not exist ");
            alert.showAndWait();
        } else {
            int id_user = tableview.getSelectionModel().getSelectedItem().getId();
            proxy.removeUser(id_user);
            ObservableList<Recrutement_attente> items = tableview.getItems();
            items.remove(tableview.getSelectionModel().getSelectedItem());

            //tabViewUser.refresh();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Account Removing");
            alert.setHeaderText("Succesful :) ");
            alert.showAndWait();
        }
        
    }

}
