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
 * @author firas saadaoui
 */
public class ResponsablhRHinterfaceController implements Initializable {

    @FXML
    private Button RecAttenteButton;
    @FXML
    private Button CongéAttenteButton;
    @FXML
    private Button btn_logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToRecAttente(ActionEvent event) throws IOException {
        
               Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/RH_responsable_hire.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show(); 
    }

    @FXML
    private void GoToCongéAttente(ActionEvent event) throws IOException {
               Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/ManupilationCongé.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
    }

    @FXML
    private void OnClickLogin(ActionEvent event) throws IOException {
                    Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/LoginGUI.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
    }
    
}
