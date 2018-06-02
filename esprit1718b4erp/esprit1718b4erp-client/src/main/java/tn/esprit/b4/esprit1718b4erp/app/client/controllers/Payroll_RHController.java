/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4erp.entities.Cellule_excel;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class Payroll_RHController implements Initializable {

       @FXML
    private TableView<User> tabViewUser1;
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
    private Label labelId;
    @FXML
    private Label LabelFirstName;
    @FXML
    private Label LabelLastName;
    @FXML
    private Label LabelSalary;
    @FXML
    private Button backButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Context context;
            
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
            tabViewUser1.setItems(items);
        } catch (NamingException ex) {
            Logger.getLogger(Payroll_RHController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private static Object ContenuCellule(XSSFCell cellule) {
        Object value = null;

        if (cellule == null) {
            value = "";
        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            value = cellule.getBooleanCellValue();
        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
            value = cellule.getErrorCellValue();
        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
            value = cellule.getCellFormula();
        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            value = cellule.getNumericCellValue();
        } else if (cellule.getCellType() == XSSFCell.CELL_TYPE_STRING) {
            value = cellule.getRichStringCellValue();
        }
        return value;

    }

    @FXML
    private void onItemClicked(MouseEvent event) throws NamingException {
        String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";
  		
        Context context=new InitialContext();
        UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(jndiName);

        List l = TraitementExcel();
        Cellule_excel c = new Cellule_excel();
        //String val =(txt_id_Emp.getText());
        Integer val = tabViewUser1.getSelectionModel().getSelectedItem().getId();
        System.out.println( tabViewUser1.getSelectionModel().getSelectedItem().getId());
        for (int i = 0; i < l.size(); i++) {

            c = ((Cellule_excel) l.get(i));
            if (c.getId_emp().equals(val.toString())) {
               User u = userServiceRemote.getUserById((val));
               	//System.out.println(u.getNumber_h());
                System.out.println("fama");
                System.out.println(c.getNbr_h());
                String s =c.getNbr_h();
                System.out.println(c.toString());
               	System.out.println("cost h " +u.getCost_h());
               	float f =u.getCost_h();
               	System.out.println("salaire est" +(f*Float.parseFloat(s)));
               	float salaire = (f*Float.parseFloat(s));
              labelId.setText(u.getCin());
              LabelFirstName.setText(u.getFirstname());
              LabelLastName.setText(u.getLastname());
              LabelSalary.setText(Float.toString(salaire)+"DT");

            } 

        }
    }


    public List<Cellule_excel> TraitementExcel() {
        InputStream inp = null;
        try {
            inp = new FileInputStream("ERP_pointage.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(inp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = wb.getSheetAt(0);

        int nbLigneFichier1 = sheet.getPhysicalNumberOfRows();
        System.out.println("nbre ligne");
        System.out.println(nbLigneFichier1);
        int nombreDeCelluleMax = 0;

        for (int ligne = 0; ligne < nbLigneFichier1; ligne++) {
            XSSFRow row = sheet.getRow(ligne);

            if (row != null) {
                if (row.getPhysicalNumberOfCells() > nombreDeCelluleMax) {
                    nombreDeCelluleMax = row.getPhysicalNumberOfCells();
                }
            }

        }
        int nbColFichier1 = nombreDeCelluleMax;
        System.out.println("nbre colonne");
        System.out.println(nbColFichier1);
        nombreDeCelluleMax = 0;

        String tabFichier1[][] = new String[nbLigneFichier1][nbColFichier1];
        List l = new ArrayList();
        for (int ligne = 1; ligne < nbLigneFichier1; ligne++) {
            Cellule_excel c = new Cellule_excel();

            XSSFRow row = sheet.getRow(ligne);
            if (row != null) {

                for (int colonne = 0; colonne < nbColFichier1; colonne++) {
                    if (colonne == 0) {
                        XSSFCell cellule = row.getCell(colonne);
                        Object valeur = ContenuCellule(cellule);
                        tabFichier1[ligne][colonne] =(valeur.toString().substring(0,valeur.toString().indexOf(".") ));
                        //  System.out.println(tabFichier1[ligne][colonne]); 
                        c.setId_emp(tabFichier1[ligne][colonne]);

                    }
                    if (colonne == 1) {
                        XSSFCell cellule = row.getCell(colonne);
                        Object valeur = ContenuCellule(cellule);
                        tabFichier1[ligne][colonne] = (valeur.toString().substring(0,valeur.toString().indexOf(".")));
                        // System.out.println(tabFichier1[ligne][colonne]); 
                        c.setNbr_h(tabFichier1[ligne][colonne]);

                    }

                }
                l.add(c);
                for (int i = 0; i < l.size(); i++) {
                    Cellule_excel c1 = (Cellule_excel) l.get(i);
                     //System.out.println( c1.toString());
                    // System.out.println(c1);
                }

            }

        }

        try {
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
        
    }

    @FXML
    private void OnCLickBack(ActionEvent event) throws IOException {
        
            Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/espaceRH.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

}
