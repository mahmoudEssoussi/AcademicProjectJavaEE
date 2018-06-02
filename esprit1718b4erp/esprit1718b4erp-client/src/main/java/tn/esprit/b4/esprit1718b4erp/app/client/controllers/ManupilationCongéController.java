/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.EtatCongé;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class ManupilationCongéController implements Initializable {

    @FXML
    private TableView<Congé> tableViewCongé;
    private ArrayList<Congé> tab;

    @FXML
    private TableColumn<Congé, String> colDateBegin;
    @FXML
    private TableColumn<Congé, String> colDateEnd;
    @FXML
    private TableColumn<Congé, String> colType;
    @FXML
    private CheckBox checkBoxAccept;
    @FXML
    private CheckBox checkBoxRefuse;
    @FXML
    private TextArea txtComent;
    @FXML
    private TextField txtCin;
    @FXML
    private TextField txtFirstame;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtMaritalStatus;
    @FXML
    private TextField txtNationality;
    @FXML
    private TextField txtNbreDay;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Context context;
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
            context = new InitialContext();
            CongéServiceRemote proxy = (CongéServiceRemote) context.lookup(jndiName);
            
            
           // colUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            colDateBegin.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            colDateEnd.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));

            List<Congé> ra;
            ra = proxy.findAll();
            
            ObservableList<Congé> items = FXCollections.observableArrayList(ra);
            tableViewCongé.setItems(items);
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onItemClicked(MouseEvent event) throws NamingException {
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
        String jndiName1 = "esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";

        Context context = new InitialContext();
        UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);
        CongéServiceRemote proxy1 = (CongéServiceRemote) context.lookup(jndiName1);
        User u = tableViewCongé.getSelectionModel().getSelectedItem().getUser();
        Congé c = new Congé();
        txtComent.setText(tableViewCongé.getSelectionModel().getSelectedItem().getComment());
        System.out.println("cin");
        u.setCin(u.getCin());
        txtCin.setText(u.getCin());
        txtFirstame.setText(u.getFirstname());
        txtLastName.setText(u.getLastname());
        txtNationality.setText(u.getNationality());
        txtEmail.setText(u.getEmail());
        txtMaritalStatus.setText(u.getMarital_status());
        txtGender.setText(u.getGender());
        System.out.println(tableViewCongé.getSelectionModel().getSelectedItem().getDateDebut());
        System.out.println(tableViewCongé.getSelectionModel().getSelectedItem().getDateFin());
        txtNbreDay.setText(String.valueOf(proxy.getDateDiff(tableViewCongé.getSelectionModel().getSelectedItem().getDateDebut(), tableViewCongé.getSelectionModel().getSelectedItem().getDateFin(), TimeUnit.DAYS)));
        if (proxy.getDateDiff(tableViewCongé.getSelectionModel().getSelectedItem().getDateDebut(), tableViewCongé.getSelectionModel().getSelectedItem().getDateFin(), TimeUnit.DAYS) >= 30)
        {
            checkBoxAccept.setDisable(true);
            checkBoxRefuse.setDisable(true);
            c = tableViewCongé.getSelectionModel().getSelectedItem();
            c.setEtat(EtatCongé.refused);
            System.out.println("superieur a 30");
            proxy1.update(c);
          //  Alert alert = new Alert(Alert.AlertType.INFORMATION);
           // alert.setTitle("Refused ");
           // alert.setHeaderText("you have exceeded the valid number of days :( ");
            //alert.showAndWait();
            txtCin.setText("");
            txtFirstame.setText("");
            txtLastName.setText("");
            txtNationality.setText("");
            txtEmail.setText("");
            txtMaritalStatus.setText("");
            txtGender.setText("");
            txtComent.setText("");
            txtNbreDay.setText("");
           // tableViewCongé.refresh();
            checkBoxAccept.setSelected(false);
            checkBoxRefuse.setSelected(false);
          

        }
        checkBoxAccept.setDisable(false);
        checkBoxRefuse.setDisable(false);
        checkBoxAccept.setSelected(false);
        checkBoxRefuse.setSelected(false);

    }

    @FXML
    private void onchikedAccept(ActionEvent event) throws NamingException {
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
        Context context = new InitialContext();
        CongéServiceRemote proxy = (CongéServiceRemote) context.lookup(jndiName);
        Congé c = new Congé();
        c = tableViewCongé.getSelectionModel().getSelectedItem();
        c.setEtat(EtatCongé.accepted);
        proxy.update(c);
                    ObservableList<Congé> items = tableViewCongé.getItems();
                    items.remove(tableViewCongé.getSelectionModel().getSelectedItems());
                    System.out.println(tableViewCongé.getSelectionModel().getSelectedItems());
                   // proxy.delete(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Accepted ");
        alert.setHeaderText("demand accepted :) ");
        alert.showAndWait();
        checkBoxAccept.setSelected(false);
        txtCin.setText("");
        txtFirstame.setText("");
        txtLastName.setText("");
        txtNationality.setText("");
        txtEmail.setText("");
        txtMaritalStatus.setText("");
        txtGender.setText("");
        txtComent.setText("");
        txtNbreDay.setText("");

    }

    @FXML
    private void onchikedRefused(ActionEvent event) throws NamingException {
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/CongéService!tn.esprit.b4.esprit1718b4erp.services.CongéServiceRemote";
        Context context = new InitialContext();
        CongéServiceRemote proxy = (CongéServiceRemote) context.lookup(jndiName);
        Congé c = new Congé();
        c = tableViewCongé.getSelectionModel().getSelectedItem();
        c.setEtat(EtatCongé.refused);
        c.setComment("firas saadaoui");
        System.out.println("ssss");
        proxy.update(c);
        
                    ObservableList<Congé> items = tableViewCongé.getItems();
                    items.remove(tableViewCongé.getSelectionModel().getSelectedItems());
                   // proxy.delete(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refused ");
        alert.setHeaderText("demand refused :( ");
        alert.showAndWait();
        checkBoxRefuse.setSelected(false);
        txtCin.setText("");
        txtFirstame.setText("");
        txtLastName.setText("");
        txtNationality.setText("");
        txtEmail.setText("");
        txtMaritalStatus.setText("");
        txtGender.setText("");
        txtComent.setText("");
        txtNbreDay.setText("");
    }

    @FXML
    private void OnCLickBack(ActionEvent event) throws IOException {
                  Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/responsableRHinterface.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
        
    }

}
