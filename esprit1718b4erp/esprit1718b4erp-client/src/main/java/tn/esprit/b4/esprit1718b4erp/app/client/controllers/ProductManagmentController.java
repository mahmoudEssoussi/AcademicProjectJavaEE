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

import javax.mail.internet.NewsAddress;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.plaf.basic.ComboPopup;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;
import tn.esprit.b4.esprit1718b4erp.services.ElementProductServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote;

/**
 * FXML Controller class
 *
 * @author Mahmoud essoussi
 */
public class ProductManagmentController implements Initializable {

    public static Product product = new Product();
    @FXML
    private TextField ref;
    @FXML
    private TextField name;
    @FXML
    private TextField unitStock;
    @FXML
    private TextField ManCost;
    @FXML
    private TextField HRCost;
    @FXML
    private Button Save;
    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> TRef;
    @FXML
    private TableColumn<Product, String> Tname;
    @FXML
    private TableColumn<Product, Integer> TuPrice;
    @FXML
    private TableColumn<Product, Integer> TuStock;
    @FXML
    private TableColumn<Product, Integer> TpmCost;
    @FXML
    private TableColumn<Product, Integer> TmCost;
    @FXML
    private TableColumn<Product, Integer> ThrCost;
    @FXML
    private TableColumn<Product, Integer> TuOrder;
    @FXML
    private TableColumn<Product, Integer> Tproject;

    ObservableList<Product> champs;

    ObservableList<PrimaryMaterialsStock> selected;
    @FXML
    private Button LoasPtable;
    @FXML
    private TableView<PrimaryMaterialsStock> Itable;
    @FXML
    private TableColumn<PrimaryMaterialsStock, String> Iname;
    @FXML
    private TableColumn<PrimaryMaterialsStock, String> Iref;
    @FXML
    private TableColumn<PrimaryMaterialsStock, Integer> AvailbaleStock;
    @FXML
    private TableColumn<PrimaryMaterialsStock, Integer> unitPricee;
    @FXML
    private TextField quantity;
    @FXML
    private Button LoadTableProduct;
    @FXML
    private Button know;
    @FXML
    private Button Back;
    @FXML
    private Button update;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField newEntry;
    @FXML
    private Button export;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
            Context context = new InitialContext();
            ProductServicesRemote u = (ProductServicesRemote) context.lookup(ProductJindi);
            champs = FXCollections.observableArrayList(u.DisplayAllPstock());

        } catch (NamingException e) {

        }

        TRef.setCellValueFactory(new PropertyValueFactory<>("refProduct"));
        Tname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TuPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        TuStock.setCellValueFactory(new PropertyValueFactory<>("unitInStock"));
        TpmCost.setCellValueFactory(new PropertyValueFactory<>("costPraimaryMaterials"));
        TmCost.setCellValueFactory(new PropertyValueFactory<>("manifacturCost"));
        ThrCost.setCellValueFactory(new PropertyValueFactory<>("hrCost"));
        TuOrder.setCellValueFactory(new PropertyValueFactory<>("unitInOrder"));
        Tproject.setCellValueFactory(new PropertyValueFactory<>("idProject"));

        productTable.setItems(champs);

        combobox.getItems().addAll("Name", "Reference", "ManCost", "HRCost"
        );

    }

    @FXML
    private void SaveProduct(ActionEvent event) throws NamingException {
        String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
        Context context = new InitialContext();
        ProductServicesRemote u = (ProductServicesRemote) context.lookup(ProductJindi);
        if (controleSaisie()) {
            Product P = new Product();
            P.setRefProduct(ref.getText());
            P.setName(name.getText());
            P.setUnitInStock(Integer.parseInt(unitStock.getText()));
            P.setManifacturCost(Integer.parseInt(ManCost.getText()));
            P.setHrCost(Integer.parseInt(HRCost.getText()));

            u.addPRoduct(P);
        }

    }

    @FXML
    private void DeleteProduct(ActionEvent event) throws NamingException {

        String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
        Context context = new InitialContext();
        ProductServicesRemote u = (ProductServicesRemote) context.lookup(ProductJindi);

        Product P = new Product();

        P = productTable.getSelectionModel().getSelectedItem();
        u.delete(P);
    }

    @FXML
    private void LoadTableProduct(ActionEvent event) {

        try {
            String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
            Context context = new InitialContext();
            PrimaryMaterialsStockServicesRemote c = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
            selected = FXCollections.observableArrayList(c.DisplayAllPstock());
        } catch (NamingException e) {
        }

        Iname.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        Iref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        AvailbaleStock.setCellValueFactory(new PropertyValueFactory<>("finalStock"));
        unitPricee.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        Itable.setItems(selected);

    }

    @FXML
    private void LoasPtable(ActionEvent event) {
        try {
            String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
            Context context = new InitialContext();
            ProductServicesRemote u = (ProductServicesRemote) context.lookup(ProductJindi);
            champs = FXCollections.observableArrayList(u.DisplayAllPstock());
            productTable.setItems(champs);
        } catch (NamingException e) {

        }
    }

    @FXML
    private void AddPM(ActionEvent event) throws NamingException {
        String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
        Context context = new InitialContext();
        ProductServicesRemote P = (ProductServicesRemote) context.lookup(ProductJindi);

        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
        Context context1 = new InitialContext();
        PrimaryMaterialsStockServicesRemote I = (PrimaryMaterialsStockServicesRemote) context1.lookup(jndiName);

        String ElementJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ElementProductServices!tn.esprit.b4.esprit1718b4erp.services.ElementProductServicesRemote";
        Context context2 = new InitialContext();
        ElementProductServicesRemote m = (ElementProductServicesRemote) context2.lookup(ElementJindi);

        Product pr = new Product();
        pr = productTable.getSelectionModel().getSelectedItem();

        PrimaryMaterialsStock pm = new PrimaryMaterialsStock();
        pm = Itable.getSelectionModel().getSelectedItem();

        ElementProduct el = new ElementProduct();

        int Final = pm.getFinalStock();
        int Out = pm.getStockOut();

        if (Final < Integer.parseInt(quantity.getText())) {
            System.out.println("stock insufisant");

        } else {
            // mis a jour du matiere premiere
            pm.setStockOut(Out + Integer.parseInt(quantity.getText()));
            pm.setFinalStock(Final - Integer.parseInt(quantity.getText()));
            I.update(pm);
            // mis a jour du Produit
            int a = pr.getCostPraimaryMaterials();
            int CostPraimaryMaterials = (pm.getUnitPrice() * Integer.parseInt(quantity.getText())) + a;
            int unitprice = (pr.getHrCost() + pr.getManifacturCost() + CostPraimaryMaterials) / pr.getUnitInStock();
            pr.setCostPraimaryMaterials(CostPraimaryMaterials);
            pr.setUnitPrice(unitprice);
            P.update(pr);
            // ajout de matiere premiere a un produit
            el.setMaterials(pm);
            el.setProduct(pr);
            el.setQuantity(Integer.parseInt(quantity.getText()));
            m.add(el);

        }

    }

    @FXML
    private void know(ActionEvent event) throws IOException {
        ProductManagmentController.product = productTable.getSelectionModel().getSelectedItem();
        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/ElementProduct.fxml"));

        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {

        Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/espaceRF.fxml"));
        Scene espaceResProjectsScene = new Scene(espaceResProjects);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show();
    }

    @FXML
    private void update(ActionEvent event) throws NamingException {
        String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
        Context context = new InitialContext();
        ProductServicesRemote P = (ProductServicesRemote) context.lookup(ProductJindi);
        Product pr = new Product();
        pr = productTable.getSelectionModel().getSelectedItem();

        if (combobox.getValue().isEmpty() && newEntry.getText().isEmpty() && pr == null) {
            alert("check fields");
        } else {

            String choice = combobox.getValue();
            String newValue = newEntry.getText();

            if (choice.equalsIgnoreCase("Name")) {
                pr.setName(newValue);
            } else if (choice.equalsIgnoreCase("Reference")) {
                pr.setRefProduct(newValue);
            } else if (choice.equalsIgnoreCase("ManCost")) {
                pr.setManifacturCost(Integer.parseInt(newValue));
            } else if (choice.equalsIgnoreCase("HRCost")) {
                pr.setHrCost(Integer.parseInt(newValue));
            }

            P.update(pr);
        }

    }

    @FXML
    private void export(ActionEvent event) throws NamingException, IOException {
        String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
        Context context = new InitialContext();
        ProductServicesRemote P = (ProductServicesRemote) context.lookup(ProductJindi);
        Product pr = new Product();
        pr = productTable.getSelectionModel().getSelectedItem();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Product Details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("idProduct");
        header.createCell(1).setCellValue("costPraimaryMaterials");
        header.createCell(2).setCellValue("hrCost");
        header.createCell(3).setCellValue("idProject");
        header.createCell(4).setCellValue("manifacturCost");
        header.createCell(5).setCellValue("name");
        header.createCell(6).setCellValue("refProduct");
        header.createCell(7).setCellValue("unitInOrder");
        header.createCell(8).setCellValue("unitPrice");
        int index = 1;
        List arraylist = new ArrayList<>();
        arraylist = P.DisplayAllPstock();
        sheet.autoSizeColumn(1);
        sheet.setColumnWidth(3, 200 * 25);
        sheet.setZoom(150);

        for (Product c : P.DisplayAllPstock()) {
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(c.getIdProduct());
            row.createCell(1).setCellValue(c.getCostPraimaryMaterials());
            row.createCell(2).setCellValue(c.getHrCost());
            row.createCell(3).setCellValue(c.getIdProduct());
            row.createCell(4).setCellValue(c.getManifacturCost());
            row.createCell(5).setCellValue(c.getName());
            row.createCell(6).setCellValue(c.getRefProduct());
            row.createCell(7).setCellValue(c.getUnitInOrder());
            row.createCell(8).setCellValue(c.getUnitPrice());
            index++;

        }
        FileOutputStream fileOut = new FileOutputStream("Product Details.xlsx");
        wb.write(fileOut);
        fileOut.close();

    }

    public boolean controleSaisie() {
        if (ref.getText().isEmpty()) {
            alert(" ref ");
            return false;
        }
        if (name.getText().isEmpty()) {
            alert(" name  ");
            return false;
        }
        if (Integer.valueOf(unitStock.getText()) < 0) {
            alert(" unitStock  ");
            return false;

        }
        if (Integer.valueOf(HRCost.getText()) < 0) {
            alert(" HRCost  ");
            return false;

        }
        if (Integer.valueOf(ManCost.getText()) < 0) {
            alert(" ManCost  ");
            return false;

        }

        return true;
    }

    public void alert(String what) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(" error ");
        alert.setHeaderText("check" + what);
        alert.showAndWait();
    }
}
