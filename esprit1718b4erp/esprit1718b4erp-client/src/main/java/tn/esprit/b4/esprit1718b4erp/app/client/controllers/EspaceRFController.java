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
public class EspaceRFController implements Initializable {
    @FXML
    private Button PSM;
    @FXML
    private Button ProductManagement;
    @FXML
    private Button OrderManagment;
    @FXML
    private Button bill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickPSM(ActionEvent event) throws IOException {
             Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/StockManagement.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
    }

    @FXML
    private void ProductManagement(ActionEvent event) throws IOException {
               Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/ProductManagment.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
    }

    @FXML
    private void OrderManagment(ActionEvent event) throws IOException {
                      Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/Orders.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
    }

    @FXML
    private void bill(ActionEvent event)  throws IOException {
                               Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/bill.fxml"));
      Scene espaceResProjectsScene=new Scene(espaceResProjects);
      Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(espaceResProjectsScene);
      window.show();
            
            
    }
    
}
