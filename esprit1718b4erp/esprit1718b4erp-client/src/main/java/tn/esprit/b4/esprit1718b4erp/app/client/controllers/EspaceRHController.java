/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class EspaceRHController implements Initializable {

    @FXML
    private Button btn_emp_management;
    @FXML
    private Button btn_logout;
    @FXML
    private Button PayrollButton;
    @FXML
    private ImageView imageViewStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handle_button_action(ActionEvent event) throws IOException {
    
    }

    @FXML
    private void onClickLogout(ActionEvent event) throws IOException {
        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/LoginGUI.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void OnClickPayrollButton(ActionEvent event) throws NamingException, IOException {
       

    }

    @FXML
    private void OnMouseClickedStat(MouseEvent event) throws IOException {
           Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/courvesRH.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void OnMouseClickedPayroll(MouseEvent event) throws FileNotFoundException, IOException, NamingException {
         String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
        Context context = new InitialContext();

        UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("UserTable");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Id user");
        header.createCell(1).setCellValue("number of hour");
        header.createCell(2).setCellValue("cost of hour");

        int index = 1;
        List arraylist = new ArrayList<>();
        arraylist = proxy.DisplayAllUser();
        sheet.autoSizeColumn(1);
        sheet.setColumnWidth(3, 200 * 25);
        sheet.setZoom(150);
        for (User P : proxy.DisplayAllUser()) {
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(P.getId());
            row.createCell(1).setCellValue(P.getNumber_h());
            row.createCell(2).setCellValue(P.getCost_h());

            index++;

        }

        FileOutputStream fileOut = new FileOutputStream("ERP_pointage.xls");
        wb.write(fileOut);
        fileOut.close();

        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/Payroll_RH.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void OnMouseClickedEMployeManagement(MouseEvent event) throws IOException {
            Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/RH_add_liste_attente.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

}
