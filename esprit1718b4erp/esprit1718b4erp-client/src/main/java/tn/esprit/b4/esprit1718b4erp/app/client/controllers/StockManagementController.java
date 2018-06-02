/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Mahmoud essoussi
 */
public class StockManagementController implements Initializable {

	@FXML
	private TextField ref;
	@FXML
	private TextField name;

	@FXML
	private TextField InitialStock;
	@FXML
	private DatePicker DateIn;
	@FXML
	private TextField priceUnit;
	@FXML
	private Button AddStock;
	@FXML
	private TableView<PrimaryMaterialsStock> StockTable;
	@FXML
	private TableColumn<PrimaryMaterialsStock, String> Tname;
	@FXML
	private TableColumn<PrimaryMaterialsStock, String> Tref;
	@FXML
	private TableColumn<PrimaryMaterialsStock, String> Tdate;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> Tinitial;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> unitPrice;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> stockPrice;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> Tin;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> Tout;
	@FXML
	private TableColumn<PrimaryMaterialsStock, Integer> Tfinal;

	ObservableList<PrimaryMaterialsStock> champs;
	@FXML
	private Button Refresh;
	@FXML
	private Button Update;
	@FXML
	private Button Delete;
	@FXML
	private ComboBox<String> ChoiceUpdate;
	@FXML
	private TextField Nupdate;
	@FXML
	private TextField NewIn;
	@FXML
	private TextField NewOut;
	@FXML
	private Button NewInOut;
	@FXML
	private TextField Search;
	@FXML
	private Button back;
	@FXML
	private Button excel;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		Search.textProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				StockFilter((String) oldValue, (String) newValue);
			}
		});
		try {
			String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
			Context context = new InitialContext();
			PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
			champs = FXCollections.observableArrayList(u.DisplayAllPstock());
		} catch (NamingException e) {
		}

		Tname.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		Tref.setCellValueFactory(new PropertyValueFactory<>("ref"));

		Tinitial.setCellValueFactory(new PropertyValueFactory<>("InitialStock"));

		stockPrice.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
		unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
		Tin.setCellValueFactory(new PropertyValueFactory<>("stockIn"));
		Tin.setCellValueFactory(new PropertyValueFactory<>("stockIn"));
		Tout.setCellValueFactory(new PropertyValueFactory<>("stockOut"));
		Tfinal.setCellValueFactory(new PropertyValueFactory<>("finalStock"));
		Tdate.setCellValueFactory(new PropertyValueFactory<>("dateIn"));

		StockTable.setItems(champs);

		ChoiceUpdate.getItems().addAll("Name", "Reference", "Date In", "Initial Stock"

		);

	}

	@FXML
	private void onClickAdd(ActionEvent event) throws NamingException {
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
		Context context = new InitialContext();
		PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);

		PrimaryMaterialsStock P = new PrimaryMaterialsStock();
		if (controleSaisie()) {
			P.setRef(ref.getText());
			P.setItemName(name.getText());
			P.setUnitPrice(Integer.parseInt(priceUnit.getText()));
			P.setInitialStock(Integer.parseInt((InitialStock.getText())));
			P.setStockPrice(Integer.parseInt((InitialStock.getText())) * Integer.parseInt((priceUnit.getText())));

			String date = DateIn.getValue() + "";
			P.setDateIn(date);

			P.setStockIn(0);
			P.setStockOut(0);
			P.setFinalStock(Integer.parseInt(InitialStock.getText()));
			u.addPstock(P);
			System.out.println(P);
		}

	}

	@FXML
	void Refresh(ActionEvent event) {

		champs.remove(champs);
		try {
			String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
			Context context = new InitialContext();
			PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
			champs = FXCollections.observableArrayList(u.DisplayAllPstock());
			StockTable.setItems(champs);
		} catch (NamingException e) {
		}

	}

	@FXML
	private void Delete(ActionEvent event) throws NamingException {
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
		Context context = new InitialContext();
		PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
		PrimaryMaterialsStock pr = new PrimaryMaterialsStock();
		pr = StockTable.getSelectionModel().getSelectedItem();
		u.delete(pr);

	}

	@FXML
	private void Update(ActionEvent event) throws NamingException {
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
		Context context = new InitialContext();
		PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
		PrimaryMaterialsStock pr = new PrimaryMaterialsStock();

		String choice = ChoiceUpdate.getValue();
		String newValue = Nupdate.getText();
		pr = StockTable.getSelectionModel().getSelectedItem();
		System.out.println(pr);

		if (choice.equalsIgnoreCase("Name")) {
			pr.setItemName(newValue);
		} else if (choice.equalsIgnoreCase("Reference")) {
			pr.setRef(newValue);
		} else if (choice.equalsIgnoreCase("Initial Stock")) {
			pr.setInitialStock(Integer.parseInt(newValue));
		} else if (choice.equalsIgnoreCase("Date In")) {
			pr.setDateIn(newValue);
		}

		u.update(pr);

	}

	@FXML
	private void NewInOut(ActionEvent event) throws NamingException {
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
		Context context = new InitialContext();
		PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
		PrimaryMaterialsStock pr = new PrimaryMaterialsStock();
		String newIn = NewIn.getText();
		String newOut = NewOut.getText();
		pr = StockTable.getSelectionModel().getSelectedItem();

		if (controleSaisie2()) {
				int Final = pr.getFinalStock();
				int Initial = pr.getInitialStock();
				int In = pr.getStockIn();

				int UnitPrice = pr.getUnitPrice();
				int StockPrice = pr.getStockPrice();

				int newUnitPrice = Integer.parseInt(newOut);

				float UP = ((Integer.parseInt(newIn) * newUnitPrice) + (UnitPrice * Final))
						/ (Final + Integer.parseInt(newIn));

				In = In + Integer.parseInt(newIn);
				Final = Initial + In;

				pr.setUnitPrice((int) UP);
				pr.setStockPrice((int) (UP * Final));

				pr.setStockIn(In);
				pr.setFinalStock(Final);

				u.update(pr);
			
		}
	

	}

	private void StockFilter(String oldValue, String newValue) {
		ObservableList<PrimaryMaterialsStock> filterList = FXCollections.observableArrayList();
		if (Search == null || (newValue.length() < oldValue.length()) || newValue == null) {

			StockTable.setItems(champs);

		} else {

			newValue = newValue.toUpperCase();

			for (PrimaryMaterialsStock E : StockTable.getItems()) {

				String filterFirstName = E.getItemName();

				if (filterFirstName.toUpperCase().contains(newValue)) {

					filterList.add(E);

				}

			}
			StockTable.setItems(filterList);

		}

	}

	@FXML
	private void onClickExcel(ActionEvent event) throws NamingException, IOException {
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/PrimaryMaterialsStockServices!tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote";
		Context context = new InitialContext();
		PrimaryMaterialsStockServicesRemote u = (PrimaryMaterialsStockServicesRemote) context.lookup(jndiName);
		PrimaryMaterialsStock pr = new PrimaryMaterialsStock();

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Stock Details");
		XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("IdStock");
		header.createCell(1).setCellValue("ref");
		header.createCell(2).setCellValue("itemName");
		header.createCell(3).setCellValue("unit price");
		header.createCell(4).setCellValue("dateIn");
		header.createCell(5).setCellValue("InitialStock");
		header.createCell(6).setCellValue("stockIn");
		header.createCell(7).setCellValue("stockOut");
		header.createCell(8).setCellValue("finalStock");
		int index = 1;
		List arraylist = new ArrayList<>();
		arraylist = u.DisplayAllPstock();
		sheet.autoSizeColumn(1);
		sheet.setColumnWidth(3, 200 * 25);
		sheet.setZoom(150);

		for (PrimaryMaterialsStock P : u.DisplayAllPstock()) {
			XSSFRow row = sheet.createRow(index);
			row.createCell(0).setCellValue(P.getIdStock());
			row.createCell(1).setCellValue(P.getRef());
			row.createCell(2).setCellValue(P.getItemName());
			row.createCell(3).setCellValue(P.getUnitPrice());
			row.createCell(4).setCellValue(P.getDateIn());
			row.createCell(5).setCellValue(P.getInitialStock());
			row.createCell(6).setCellValue(P.getStockIn());
			row.createCell(7).setCellValue(P.getStockOut());
			row.createCell(8).setCellValue(P.getFinalStock());
			index++;

		}
		FileOutputStream fileOut = new FileOutputStream("StockDetails.xlsx");
		wb.write(fileOut);
		fileOut.close();

	}

	@FXML
	private void back(ActionEvent event) throws IOException {
		Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/espaceRF.fxml"));
		Scene espaceResProjectsScene = new Scene(espaceResProjects);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(espaceResProjectsScene);
		window.show();

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
		if (Integer.valueOf(priceUnit.getText()) < 0) {
			alert(" priceUnit  ");
			return false;

		}
		if (Integer.valueOf(InitialStock.getText()) < 0) {
			alert(" InitialStock  ");
			return false;

		}
		if ((DateIn.getValue() + "").isEmpty()) {
			alert(" InitialStock  ");
			return false;

		}

		return true;
	}

	public boolean controleSaisie2() {
		if (Integer.valueOf(NewIn.getText()) < 0) {
			alert(" ref ");
			return false;
		}
		if ((Integer.valueOf(NewOut.getText()) < 0)) {
			alert(" name  ");
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
