/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.esprit.b4.esprit1718b4erp.app.client.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.OrderDetails;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;
import tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.OrdersDetailsServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote;

/**
 * FXML Controller class
 *
 * @author Mahmoud essoussi
 */
public class OrdersController implements Initializable {
	@FXML
	private TextField Costumer;
	@FXML
	private TextField adress;
	private TextField orderDate;
	@FXML
	private TextField ShippedDate;
	@FXML
	private TextField shippingCost;
	@FXML
	private Button Save;
	@FXML
	private Button Add;
	@FXML
	private Button Delete;
	@FXML
	private TableView<Order> OrderTable;
	@FXML
	private TableColumn<Order, String> TCostumer;
	@FXML
	private TableColumn<Order, String> Tadresse;
	@FXML
	private TableColumn<Order, String> TorderDate;
	@FXML
	private TableColumn<Order, String> TshippedDate;
	@FXML
	private TableColumn<Order, Integer> TsCost;
	@FXML
	private TableColumn<Order, Integer> TuCost;
	@FXML
	private TableColumn<Order, Integer> Tquantity;
	@FXML
	private Button LoasPtable;
	@FXML
	private Button know;
	@FXML
	private Button update;
	@FXML
	private TableView<Product> ProductTable;
	@FXML
	private TableColumn<Product, String> Pname;
	@FXML
	private TableColumn<Product, String> Pref;
	@FXML
	private TableColumn<Product, String> AvailbaleStock;
        @FXML
        private TableColumn<Product, Integer> unitPricee;

	@FXML
	private TextField quantity;
	@FXML
	private Button LoadTableProduct;

	ObservableList<Order> champs;

	ObservableList<Product> selected;
	@FXML
	private TextField ProductQuantity;
	@FXML
	private Button Back;
    @FXML
    private Button export;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField newEntry;
    @FXML
    private DatePicker shippedDate;
    

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
			Context context = new InitialContext();
			OrderServicesRemote u = (OrderServicesRemote) context.lookup(ProductJindi);
			champs = FXCollections.observableArrayList(u.DisplayAllOrder());
		} catch (NamingException e) {

		}
		TCostumer.setCellValueFactory(new PropertyValueFactory<>("costumer"));
		Tadresse.setCellValueFactory(new PropertyValueFactory<>("adress"));
		TorderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		TshippedDate.setCellValueFactory(new PropertyValueFactory<>("shippedDate"));
		TsCost.setCellValueFactory(new PropertyValueFactory<>("shippingCost"));
		TuCost.setCellValueFactory(new PropertyValueFactory<>("unitSellingPrice"));
		Tquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		OrderTable.setItems(champs);
		
		
		combobox.getItems().addAll("costumer", "orderDate", "shippedDate", "adress","shippingCost"

				);

		// TODO
	}

	@FXML
	private void SaveProduct(ActionEvent event) throws NamingException {
		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context = new InitialContext();
		OrderServicesRemote u = (OrderServicesRemote) context.lookup(ProductJindi);
		if (controleSaisie()) {
			Order O = new Order();
			O.setCostumer(Costumer.getText());
			//O.setOrderDate(orderDate.getText());
			//O.setShippedDate(shippedDate.getValue()+"");
			O.setAdress(adress.getText());
			O.setShippingCost(Integer.parseInt(shippingCost.getText()));
			O.setQuantity(Integer.parseInt(ProductQuantity.getText()));
			u.addOrder(O);
		}

	}

	@FXML
	private void DeleteProduct(ActionEvent event) throws NamingException {
		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context = new InitialContext();
		OrderServicesRemote u = (OrderServicesRemote) context.lookup(ProductJindi);
		Order O = new Order();
		O = OrderTable.getSelectionModel().getSelectedItem();
		u.delete(O);

	}

	@FXML
	private void LoasPtable(ActionEvent event) throws NamingException {
		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context = new InitialContext();
		OrderServicesRemote u = (OrderServicesRemote) context.lookup(ProductJindi);
		champs = FXCollections.observableArrayList(u.DisplayAllOrder());
		OrderTable.setItems(champs);
	}

	@FXML
	private void know(ActionEvent event)throws NamingException {
		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context = new InitialContext();
		OrderServicesRemote u = (OrderServicesRemote) context.lookup(ProductJindi);
		List  <OrderDetails> list=new ArrayList<OrderDetails>();
		Order order = new Order();
		order = OrderTable.getSelectionModel().getSelectedItem();
		list=order.getListOrderProduct();
		if(list.isEmpty()){alert("this product don't have a product, make sure to add a product ");}
		else{	
		alert("quantity="+list.get(0).getQuantity()+" unit price ="+list.get(0).getUnitprice()+"la date="+list.get(0).getDate());
		
		
		}
	
		
		
		

		
		
		
		
	}

	@FXML
	private void LoadTableProduct(ActionEvent event) throws NamingException {
		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
		Context context = new InitialContext();
		ProductServicesRemote u = (ProductServicesRemote) context.lookup(ProductJindi);
		selected = FXCollections.observableArrayList(u.DisplayAllPstock());
		Pref.setCellValueFactory(new PropertyValueFactory<>("refProduct"));
		Pname.setCellValueFactory(new PropertyValueFactory<>("name"));
		AvailbaleStock.setCellValueFactory(new PropertyValueFactory<>("unitInStock"));
		unitPricee.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
		
		ProductTable.setItems(selected);
	}

	@FXML
	private void AddPM(ActionEvent event) throws NamingException {

		String ProductJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/ProductServices!tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote";
		Context context = new InitialContext();
		ProductServicesRemote P = (ProductServicesRemote) context.lookup(ProductJindi);
		Product product = new Product();

		String OrderJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context1 = new InitialContext();
		OrderServicesRemote O = (OrderServicesRemote) context1.lookup(OrderJindi);
		Order order = new Order();

		String OrderDetailsJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrdersDetailsServices!tn.esprit.b4.esprit1718b4erp.services.OrdersDetailsServicesRemote";
		Context context2 = new InitialContext();
		OrdersDetailsServicesRemote D = (OrdersDetailsServicesRemote) context2.lookup(OrderDetailsJindi);
		OrderDetails details = new OrderDetails();

		product = ProductTable.getSelectionModel().getSelectedItem();
		order = OrderTable.getSelectionModel().getSelectedItem();

		int Final = product.getUnitInStock();
		int Final2 = product.getUnitInOrder();

		if (Final < Integer.parseInt(quantity.getText())) {
			alert("insufficient stock");


		} else {

			if (order.getListOrderProduct().isEmpty())

			{// mis a jour du produit
				product.setUnitInStock(Final - Integer.parseInt(quantity.getText()));
				product.setUnitInOrder(Final2 + Integer.parseInt(quantity.getText()));
				P.update(product);
				// mis a jour du order

				int shippingCost = order.getShippingCost();
				int totalQuantity = order.getQuantity() + Integer.parseInt(quantity.getText());
				int unitprice = (shippingCost + order.getQuantity() * order.getUnitSellingPrice()
						+ Integer.parseInt(quantity.getText()) * product.getUnitPrice()) / totalQuantity;
				order.setUnitSellingPrice(unitprice);
				order.setQuantity(totalQuantity);
				O.update(order);

				// mis a jour du tableau en commun
				details.setOrder(order);
				details.setProductOrder(product);
				details.setQuantity(Integer.parseInt(quantity.getText()));
				details.setUnitprice(product.getUnitPrice());
				LocalDate date = null;
				details.setDate(date);

				D.add(details);
			}

			else {
				alert("you can't add one more product to this order ");

			}

		}

	}
	
	


    @FXML
    private void update(ActionEvent event) throws NamingException {
    	String OrderJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context1 = new InitialContext();
		OrderServicesRemote O = (OrderServicesRemote) context1.lookup(OrderJindi);
		Order order = new Order();
		order = OrderTable.getSelectionModel().getSelectedItem();
		if(combobox.getValue().isEmpty()&&newEntry.getText().isEmpty()&&order==null){
			alert("check fields");}
		else{
			
			
			String choice = combobox.getValue();
			String newValue = newEntry.getText();
			
			if (choice.equalsIgnoreCase("costumer")) {
				order.setCostumer(newValue);
			} else if (choice.equalsIgnoreCase("adress")) {
				order.setAdress(newValue);
			} 
			 else if (choice.equalsIgnoreCase("orderDate")) {
				// order.setOrderDate(newValue);
				}
			 else if (choice.equalsIgnoreCase("shippedDate")) {
				// order.setShippedDate(newValue);
				}else if (choice.equalsIgnoreCase("shippingCost")) {
					order.setShippingCost(Integer.parseInt(newValue));
			}

			O.update(order);
		}
    	
    	
    	
    	
    }
    
    @FXML
    private void export(ActionEvent event) throws NamingException, IOException  {
    	String OrderJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/OrderServices!tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote";
		Context context1 = new InitialContext();
		OrderServicesRemote O = (OrderServicesRemote) context1.lookup(OrderJindi);
		Order order = new Order();
		
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Orders Details");
		XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("id");
		header.createCell(1).setCellValue("costumer	");
		header.createCell(2).setCellValue("order date ");
		header.createCell(3).setCellValue("shipped date");
		header.createCell(4).setCellValue("unit selling price ");
		header.createCell(5).setCellValue("adress");
		header.createCell(6).setCellValue("shipping cost");
		header.createCell(7).setCellValue("quantity");
		
		int index = 1;
		List arraylist = new ArrayList<>();
		arraylist = O.DisplayAllOrder();
		sheet.autoSizeColumn(1);
		sheet.setColumnWidth(3, 200 * 25);
		sheet.setZoom(150);
		
		
		for (Order c : O.DisplayAllOrder()) {
			XSSFRow row = sheet.createRow(index);
			row.createCell(0).setCellValue(c.getId());
			row.createCell(1).setCellValue(c.getCostumer());
			row.createCell(2).setCellValue(c.getOrderDate());
			row.createCell(3).setCellValue(c.getShippedDate());
			row.createCell(4).setCellValue(c.getUnitSellingPrice());
			row.createCell(5).setCellValue(c.getAdress());
			row.createCell(6).setCellValue(c.getShippingCost());
			row.createCell(7).setCellValue(c.getQuantity());
			
			index++;

		}
		FileOutputStream fileOut = new FileOutputStream("order Details.xlsx");
		wb.write(fileOut);
		fileOut.close();
    	
    	
    	
    	
    }


	@FXML
	private void Back(ActionEvent event) throws IOException {
		Parent espaceResProjects = FXMLLoader.load(getClass().getResource("/fxml/espaceRF.fxml"));
		Scene espaceResProjectsScene = new Scene(espaceResProjects);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(espaceResProjectsScene);
		window.show();
	}

	public boolean controleSaisie() {
		if (Costumer.getText().isEmpty()) {
			alert(" Costumer ");
			return false;
		}
		if (adress.getText().isEmpty()) {
			alert(" adress  ");
			return false;
		}

		if (Integer.valueOf(shippingCost.getText()) < 0) {
			alert(" shippingCost  ");
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
