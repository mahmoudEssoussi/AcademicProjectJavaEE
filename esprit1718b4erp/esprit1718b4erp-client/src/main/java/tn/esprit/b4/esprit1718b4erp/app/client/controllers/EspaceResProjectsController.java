/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chihi
 */
public class EspaceResProjectsController implements Initializable {

    @FXML
    private Button RP_Menu_addProject;
    @FXML
    private Button newProjects;
    @FXML
    private Button RP_ConsylProjects;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RP_Menu_addProject(ActionEvent event) throws IOException {
    	Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/RPAddProjet.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void RPnewProjects_Click(ActionEvent event) throws IOException {
    	Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/RPNewProjects.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        espaceResProjectsScene.getStylesheets().add("/styles/RPNewProjects.css");
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void RP_consultProjects_Click(ActionEvent event) throws IOException {
        Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/RPConsultProjects.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void Logout_Click(ActionEvent event) throws IOException {
        Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/LoginGUI.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }
    
}
