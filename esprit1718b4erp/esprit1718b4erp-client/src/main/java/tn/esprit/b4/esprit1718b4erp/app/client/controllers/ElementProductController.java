/*
 * To change this license header, choose License Headers in Project Properties.
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

import org.apache.poi.xssf.usermodel.XSSFRow;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;
import tn.esprit.b4.esprit1718b4erp.services.ElementProductServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;

/**
 * FXML Controller class
 *
 * @author Mahmoud essoussi
 */
public class ElementProductController implements Initializable {

	@FXML
	private TableView<PrimaryMaterialsStock> StockTable;
	@FXML
	private TableColumn<PrimaryMaterialsStock, String> Tname;
	@FXML
	private TableColumn<PrimaryMaterialsStock, String> Tref;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> unitPrice;
    @FXML
    private TableView<ElementProduct> element;
	@FXML
    private TableColumn<ElementProduct, Integer> quantity;
    @FXML
    private TableColumn<ElementProduct, String> date;
    @FXML
	private Button back;

	ObservableList<PrimaryMaterialsStock> champs;
	ObservableList<ElementProduct> cham;
	
	protected List<PrimaryMaterialsStock> modelData = new ArrayList<PrimaryMaterialsStock>();
	protected List<ElementProduct> modelData2 = new ArrayList<ElementProduct>();
	
	List<ElementProduct> champ;
	
	
	private Product product;

	
	


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
			Context context;
			context = new InitialContext();
			PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
			PrimaryMaterialsStock pr = new PrimaryMaterialsStock();
			
			String ElementJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ElementProductServices!tn.esprit.b4.esprit1718b4erp.services.ElementProductServicesRemote";
			Context context2 = new InitialContext();
			ElementProductServicesRemote m = (ElementProductServicesRemote) context2.lookup(ElementJindi);

			this.product = ProductManagmentController.product;
			champ = product.getListElementProduct();

			for (ElementProduct E : champ) {
				PrimaryMaterialsStock gh = u.FindById((long) E.getMaterials().getIdStock());
		
				modelData.add(gh);
				modelData2.add(E);
				
			}
			champs = FXCollections.observableArrayList(modelData);
			
			Tname.setCellValueFactory(new PropertyValueFactory<>("itemName"));
			Tref.setCellValueFactory(new PropertyValueFactory<>("ref"));
			unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
			
			
			cham = FXCollections.observableArrayList(modelData2);
			quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			//date.setCellValueFactory(new PropertyValueFactory<>("date"));
			
			
			element.setItems(cham);

			StockTable.setItems(champs);
			

		} catch (NamingException e) {

		}

	}

	@FXML
	private void back(ActionEvent event) throws IOException {
		Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/ProductManagment.fxml"));

		Scene espaceResProjectsScene = new Scene(espaceResProjects);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(espaceResProjectsScene);
		window.show();
	}

}
