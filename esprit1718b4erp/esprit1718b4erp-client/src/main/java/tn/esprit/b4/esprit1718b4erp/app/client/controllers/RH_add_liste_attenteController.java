/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserService;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class RH_add_liste_attenteController implements Initializable {
    final FileChooser fileChooser = new FileChooser();

    public static JFXDialog dialogSkill;

    @FXML
    private TextField txt_first_name;
    @FXML
    private TextField txt_last_name;
    @FXML
    private Button btn_add;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_phone;
    @FXML
    private TextField txt_email;
    @FXML
    private DatePicker txt_dob;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<String> maritalStatus;
    @FXML
    private TextField txt_nationnality;
    @FXML
    private TextArea comment;
    @FXML
    private ComboBox<?> disponibility;
    @FXML
    private Button btn_back;
    @FXML
    private TableView<User> tabViewUser;
    private ArrayList<User> tab;

    @FXML
    private TableColumn<User, Integer> colID;
    @FXML
    private TableColumn<User, String> colFirstName;
    @FXML
    private TableColumn<User, String> coLastName;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colDOB;
    @FXML
    private TableColumn<User, String> colPhoneNumber;
    @FXML
    private TableColumn<User, String> colNationality;
    @FXML
    private TableColumn<User, String> colGender;
    @FXML
    private TableColumn<User, String> colMaritalStatus;
    @FXML
    private TableColumn<User, Integer> colWorkingH;
    @FXML
    private TableColumn<User, Float> colCostH;
    @FXML
    private Button btn_add1;
    @FXML
    private Button btn_Update;
    private AnchorPane anchropane;
    @FXML
    private StackPane stackPane;
    URL url1;
    ResourceBundle rb1;
    

    /**
     * Initializes the controller class.
     */
    private static User user = new User();

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        RH_add_liste_attenteController.user = user;
    }
    @FXML
    private Button browseButton;
    @FXML
    private Button btn_Refraiche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.getItems().addAll("male", "female");
        maritalStatus.getItems().addAll("maried", "single");
        disponibility.setDisable(true);

        Context context;
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
            context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
            colID.setCellValueFactory(new PropertyValueFactory<>("cin"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            coLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colDOB.setCellValueFactory(new PropertyValueFactory<>("date_birth"));
            colNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colMaritalStatus.setCellValueFactory(new PropertyValueFactory<>("marital_status"));
            colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            colWorkingH.setCellValueFactory(new PropertyValueFactory<>("number_h"));
            colCostH.setCellValueFactory(new PropertyValueFactory<>("cost_h"));
            List<User> ra;
            ra = proxy.findAll();
            ObservableList<User> items = FXCollections.observableArrayList(ra);
            tabViewUser.setItems(items);

        } catch (NamingException e) {
            e.printStackTrace();
        }

        System.out.println("tableview.getSelectionModel().getSelectedItem()");
url1=url;
rb1=rb;
    }

    @FXML
    private void on_btn_add_clicked(ActionEvent event) throws NamingException {
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/RHService!tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote";
        Context context = new InitialContext();
        RHServiceRemote rhserviceremote = (RHServiceRemote) context.lookup(jndiName);
        Recrutement_attente ra = new Recrutement_attente();

        try {
            ra.setFirstname(txt_first_name.getText());
            ra.setLastname(txt_last_name.getText());
            ra.setEmail(txt_email.getText());
            ra.setPhone_number(txt_phone.getText());
            ra.setDate_birth(txt_dob.getValue().toString());
            ra.setCin(Integer.parseInt(txt_cin.getText()));
            ra.setMarital_status(maritalStatus.getValue());
            ra.setComment(comment.getText());
            ra.setNationality(txt_nationnality.getText());
            ra.setGender(gender.getValue().toString());
//            byte[] data = Files.readAllBytes(this.OnClickBrows(event).toPath());
//            System.out.println("tttttttttteeeesssstttt"+data);
            ra.setPicture(Files.readAllBytes(this.OnClickBrows(event).toPath()));
        } catch (Exception e) {
            System.out.println("field is missing!");
        } finally {

            System.out.println(ra);
            rhserviceremote.save(ra);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Recruitment waiting for the validation ");
            alert.setHeaderText("Succesful :) ");
            alert.showAndWait();
            System.out.println("rec ajouté");
        }
        txt_first_name.setText("");
        txt_last_name.setText("");
        txt_dob.setValue(null);
        txt_email.setText("");
        txt_phone.setText("");
        txt_cin.setText("");
        gender.getItems();
        comment.setText("");
        txt_nationnality.setText("");

    }

    @FXML
    private void on_btn_back_clicked(ActionEvent event) throws IOException {
        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/espaceRH.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void RemoveUseronClicked(ActionEvent event) throws NamingException {
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
        Context context = new InitialContext();
        UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
        if (tabViewUser.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Account Removing Error ");
            alert.setHeaderText("Account does not exist ");
            alert.showAndWait();
        } else {
            int id_user = tabViewUser.getSelectionModel().getSelectedItem().getId();
            proxy.removeUser(id_user);
            ObservableList<User> items = tabViewUser.getItems();
            items.remove(tabViewUser.getSelectionModel().getSelectedItem());

            //tabViewUser.refresh();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Account Removing");
            alert.setHeaderText("Succesful :) ");
            alert.showAndWait();
        }
    }

    @FXML
    private void Update_Button(ActionEvent event) throws IOException {
           setUser(tabViewUser.getSelectionModel().getSelectedItem());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("details.fxml"));
        Pane child = null;

        child = FXMLLoader.load(getClass().getResource("/fxml/PopUpUpdateUser.fxml"));

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.getChildren().add(child);
        dialogSkill = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialogSkill.show();
   

    }

    @FXML
    private File OnClickBrows(ActionEvent event) throws NamingException {
          String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
        Context context = new InitialContext();
        UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
        Stage primaryStage = null;
        setExtFilters(fileChooser);
        File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println(file);
        return file;
    }

     private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
     public void init(){
         initialize(url1, rb1);
     }

    @FXML
    private void OncLick_refraiche(ActionEvent event) {
    
        Context context;
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
            context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
            colID.setCellValueFactory(new PropertyValueFactory<>("cin"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            coLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colDOB.setCellValueFactory(new PropertyValueFactory<>("date_birth"));
            colNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colMaritalStatus.setCellValueFactory(new PropertyValueFactory<>("marital_status"));
            colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            colWorkingH.setCellValueFactory(new PropertyValueFactory<>("number_h"));
            colCostH.setCellValueFactory(new PropertyValueFactory<>("cost_h"));
            List<User> ra;
            ra = proxy.findAll();
            ObservableList<User> items = FXCollections.observableArrayList(ra);
            tabViewUser.setItems(items);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
