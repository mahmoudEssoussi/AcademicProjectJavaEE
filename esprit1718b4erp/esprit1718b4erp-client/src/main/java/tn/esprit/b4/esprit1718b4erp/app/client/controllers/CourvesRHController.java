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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.esprit.b4.esprit1718b4erp.entities.Cellule_excel;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author firas saadaoui
 */
public class CourvesRHController implements Initializable {

    @FXML
    private ScatterChart<?, ?> StatCongé;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    protected List<User> ListUser = new ArrayList<User>();
    @FXML
    private BarChart<?, ?> barchar;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";

            Context context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);

            List l = TraitementExcel();
            Cellule_excel c = new Cellule_excel();
            ListUser = proxy.findAll();
           

               
                for (int k = 0; k < l.size(); k++) {
                for (User user : ListUser) {
                int val = user.getId();
                    c = ((Cellule_excel) l.get(k));
                    if (c.getId_emp().equals(Integer.toString(val))) {
                        User u = proxy.getUserById((user.getId()));
                        //System.out.println(u.getNumber_h());
                        System.out.println("fama");
                        System.out.println(c.getNbr_h());
                        String s = c.getNbr_h();
                        System.out.println(c.toString());
                        System.out.println("cost h " + u.getCost_h());
                        float f = u.getCost_h();
                        System.out.println("salaire est" + (f * Float.parseFloat(s)));
                        float salaire = (f * Float.parseFloat(s));
                        XYChart.Series set1 = new XYChart.Series<>();

                        set1.getData().add(new XYChart.Data(u.getFirstname(), salaire));
//                    set1.getData().add(new XYChart.Data("Alice", 10000));
//                    set1.getData().add(new XYChart.Data("Alex", 2000));

                        StatCongé.getData().addAll(set1);
                        //barchar.getData().addAll(set1);
                    }
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(CourvesRHController.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/UserService!tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote";

            Context context = new InitialContext();
            UserServiceRemote proxy = (UserServiceRemote) context.lookup(jndiName);

            List l = TraitementExcel();
            Cellule_excel c = new Cellule_excel();
            ListUser = proxy.findAll();
           

               
                for (int k = 0; k < l.size(); k++) {
                for (User user : ListUser) {
                int val = user.getId();
                    c = ((Cellule_excel) l.get(k));
                    if (c.getId_emp().equals(Integer.toString(val))) {
                        User u = proxy.getUserById((user.getId()));
                        //System.out.println(u.getNumber_h());
                        System.out.println("fama");
                        System.out.println(c.getNbr_h());
                        String s = c.getNbr_h();
                        System.out.println(c.toString());
                        System.out.println("cost h " + u.getCost_h());
                        float f = u.getCost_h();
                        System.out.println("salaire est" + (f * Float.parseFloat(s)));
                        float salaire = (f * Float.parseFloat(s));
                        XYChart.Series set1 = new XYChart.Series<>();

                        set1.getData().add(new XYChart.Data(u.getFirstname(), salaire));

                        barchar.getData().addAll(set1);
                    }
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(CourvesRHController.class.getName()).log(Level.SEVERE, null, ex);
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
                        tabFichier1[ligne][colonne] = (valeur.toString().substring(0, valeur.toString().indexOf(".")));
                        //  System.out.println(tabFichier1[ligne][colonne]); 
                        c.setId_emp(tabFichier1[ligne][colonne]);

                    }
                    if (colonne == 1) {
                        XSSFCell cellule = row.getCell(colonne);
                        Object valeur = ContenuCellule(cellule);
                        tabFichier1[ligne][colonne] = (valeur.toString().substring(0, valeur.toString().indexOf(".")));
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
