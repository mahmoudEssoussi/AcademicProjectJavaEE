/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4erp.app.client.controllers;


import java.awt.event.MouseEvent;
import javafx.scene.control.MenuItem;
import java.io.IOException;
import javafx.scene.control.ContextMenu;
import javafx.event.ActionEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.criterion.ExistsSubqueryExpression;

import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings;

import antlr.debug.Event;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.entities.Client;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Recrutement_attente;
import tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.ClientServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.RHServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceRemote;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Firas Guizeni
 */
public class BillController implements Initializable {
	@FXML
	private TableView<Bill> listbill;
    private ArrayList<Bill> tab;

     ObservableList<Bill> oblist ;
	
	
    @FXML
    private AnchorPane bill;
    @FXML
    private Tab BillRecieved;
    @FXML
    private Button addbill;
    @FXML
    private TextField custname;
    @FXML
    private TextField billingadresss;
    @FXML
    private TextField shippingadress;
    @FXML
    private TextField billamount;
    @FXML
    
    private TextField paymentdue;
    @FXML
    private DatePicker billdate;
    @FXML
    private DatePicker topaybefore;
    @FXML
    private ComboBox<String> typeofpay;
    @FXML
    private TableColumn<?, ?> customername;
    @FXML
    private TableColumn<?, ?> billingadress;
    @FXML
    private TableColumn<?, ?> shipingadress;
    @FXML
    private TableColumn<?, ?> amount;
    @FXML
    private TableColumn<?, ?> Due;
    @FXML
    private TableColumn<?, ?> BillDate;
    @FXML
    private TableColumn<?, ?> typeofpayment;
    @FXML
    private Button deletebill;
    @FXML
    private Tab BillToSend;
    @FXML
    private TextField billsearch;
    @FXML
    private Button mmb;
    @FXML
    private TableColumn<?, ?> topaybefore1;
   Bill sujetSelected;
    //private TableView<?> listbill;
    @FXML
    private Button billsearsh;
    @FXML
    private ComboBox<String> billtype;
    @FXML
    private TextField caisse;
    
    protected List <Bill> fiars=new ArrayList<Bill>();
    protected List <Caisse> caissestat=new ArrayList<Caisse>();
    @FXML
    private TextField datetod;
    @FXML
    private Button close;
    @FXML
    private Button billback;
    @FXML
    private ScatterChart<?, ?> billstat;
    @FXML
    private NumberAxis billy;
    @FXML
    private CategoryAxis billx;
    @FXML
    private TableColumn<?, ?> inout;
    @FXML
    private Tab Archive;
    @FXML
    private TableColumn<?, ?> archname;
    @FXML
    private TableColumn<?, ?> archfrom;
    @FXML
    private TableColumn<?, ?> archto;
    @FXML
    private TableColumn<?, ?> archamount;
    @FXML
    private TableColumn<?, ?> archdue;
    @FXML
    private TableColumn<?, ?> archbilldate;
    @FXML
    private TableColumn<?, ?> archtopaybefore;
    @FXML
    private TableColumn<?, ?> archtype;
    @FXML
    private TableColumn<?, ?> archinout;
    @FXML
    private TableView<Bill> archivelist;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
String  now1=LocalDate.now()+"";
    	
    	listbill.setRowFactory(tv -> new TableRow<Bill>() {
            @Override
            public void updateItem(Bill item, boolean empty) {
                super.updateItem(item, empty) ;
               if (1>0) {
            	   System.out.println("dkhal");
                    setStyle("-fx-background-color: tomato;");
                } else {
                    setStyle("");
                }
            }
        });
    	
    	
    	typeofpay.getItems().addAll("check", "online");
    	billtype.getItems().addAll("In", "Out");
//afficherlistbill();
    	
    	
    	
    	listbill.setRowFactory(new Callback<TableView<Bill>, TableRow<Bill>>() {
			
			@Override
			public TableRow<Bill> call(TableView<Bill> param) {
				
				final TableRow<Bill> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem MenuItem = new MenuItem("Set Cells");
				
				
				MenuItem.setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent e) {
				    	String p = listbill.getSelectionModel().getSelectedItem().getCustomer_name();
				    	custname.setText(p);

						String p1 = listbill.getSelectionModel().getSelectedItem().getBilling_adress();
						billingadresss.setText(p1);

						String p2 = listbill.getSelectionModel().getSelectedItem().getShipping_adress();
						shippingadress.setText(p2);

						int p3 = listbill.getSelectionModel().getSelectedItem().getAmount();
						billamount.setText(String.valueOf(p3));

						int p4 = listbill.getSelectionModel().getSelectedItem().getPayment_due();
						paymentdue.setText(String.valueOf(p4));

						Date p6 = listbill.getSelectionModel().getSelectedItem().getBill_date();
						Date Date = p6;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");				
						 Date date;
						date = p6;
						 LocalDate ld=new java.sql.Date(date.getTime()).toLocalDate();						
						 System.out.println(ld);
							billdate.setValue(ld);
						

						Date p5 = listbill.getSelectionModel().getSelectedItem().getTo_pay_before();
						Date string1 = p5;
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");				
						 Date date1;
						date1 = p5;
						 LocalDate ld1=new java.sql.Date(date1.getTime()).toLocalDate();						
						 System.out.println(ld1);
						 topaybefore.setValue(ld1);
						

						String p7 = listbill.getSelectionModel().getSelectedItem().getDeputy_document();
						billtype.setValue(p7);

						String p8 = listbill.getSelectionModel().getSelectedItem().getType_of_payment();
						System.out.println(p8);
						typeofpay.setValue(p8);
						
				    }
				});
						
						
						
						
				contextMenu.getItems().add(MenuItem);

				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				
				
				
				
				return row;
			}
		});
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	String jndiName2 = "esprit1718b4erp-ear/esprit1718b4erp-service/CaisseService!tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote";
        Context context2=null;
		try {
			context2 = new InitialContext();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        

     
			CaisseServiceRemote caisseRemote = null;
		
				try {
					caisseRemote = (CaisseServiceRemote) context2.lookup(jndiName2);
			
			
		
		
		
        Caisse c2 = new Caisse();
        long a= c2.getSomme_caisse();
        
       // caisse.setText(Long.toString(a));
        
    	
    	billsearch.textProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				StockFilter((String) oldValue, (String) newValue);
			}
		});
    	
    	
    
    	
    
      // categorie.getSelectionModel().select(0);
        ObservableList<Bill> oblistt = FXCollections.observableArrayList();
        oblist = FXCollections.observableArrayList();

 
        customername.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        billingadress.setCellValueFactory(new PropertyValueFactory<>("billing_adress"));
        shipingadress.setCellValueFactory(new PropertyValueFactory<>("shipping_adress"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Due.setCellValueFactory(new PropertyValueFactory<>("payment_due"));
        BillDate.setCellValueFactory(new PropertyValueFactory<>("bill_date"));
        topaybefore1.setCellValueFactory(new PropertyValueFactory<>("to_pay_before"));
        typeofpayment.setCellValueFactory(new PropertyValueFactory<>("type_of_payment"));
        inout.setCellValueFactory(new PropertyValueFactory<>("deputy_document"));

        
        
        
       
        
        
        String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";


        Context ctx = null;
		BillServiceRemote proxy = null;
	
			ctx = new InitialContext();
	
		
			proxy = (BillServiceRemote) ctx.lookup(jndiName);
	
	List<Bill> fousi=proxy.afficherbill();
		fousi.stream().forEach((user) -> {
            oblist.add((Bill) user);
        });

        listbill.getItems().addAll(oblist);
        listbill.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println(newSelection);
            if (newSelection != null) {
                sujetSelected = newSelection;

            }

        });
    	
    	
    	
        
        
        ObservableList<Bill> oblisttt = FXCollections.observableArrayList();
        oblist = FXCollections.observableArrayList();

 
        archname.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        archfrom.setCellValueFactory(new PropertyValueFactory<>("billing_adress"));
        archto.setCellValueFactory(new PropertyValueFactory<>("shipping_adress"));
        archamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        archdue.setCellValueFactory(new PropertyValueFactory<>("payment_due"));
        archbilldate.setCellValueFactory(new PropertyValueFactory<>("bill_date"));
        archtopaybefore.setCellValueFactory(new PropertyValueFactory<>("to_pay_before"));
        archtype.setCellValueFactory(new PropertyValueFactory<>("type_of_payment"));
        archinout.setCellValueFactory(new PropertyValueFactory<>("deputy_document"));

        
        
        
       
        
        
        String jndiName3 = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";


        Context ctx3 = null;
		BillServiceRemote proxy3 = null;
	
			ctx3 = new InitialContext();
	
		
			proxy3 = (BillServiceRemote) ctx3.lookup(jndiName3);
	
	List<Bill> arch=proxy3.displayArchive();
		arch.stream().forEach((user) -> {
            oblist.add((Bill) user);
        });

		archivelist.getItems().addAll(oblist);
		archivelist.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println(newSelection);
            if (newSelection != null) {
                sujetSelected = newSelection;

            }

        });
        
        
        
    	
    	
        int w=0;
    	int c=0;
    	fiars=proxy.findAll();
    	System.out.println(fiars);
    	for (Bill B:fiars){
    		Date datebill5  = B.getBill_date();
    		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    		Date date = datebill5;
			if ((B.getDeputy_document().equalsIgnoreCase("in"))&&(new Date().after(date))){
				w+=B.getPayment_due();
				System.out.println(w);
				
			}
			else if ((B.getDeputy_document().equalsIgnoreCase("out"))&&(new Date().after(date))){
				c+=B.getPayment_due();
				System.out.println(c);
				
				
			}
    		
    	}
    	
    	Long caiss=(long) (w-c);
    	System.out.println(caiss);
    	caisse.setText(Long.toString(caiss));
    	
    	
    	
    	
    	String  now=LocalDate.now()+"";
    	System.out.println(now);
    	
    	datetod.setText(now);
    	
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			finally {
				
			
		
    	caissestat=caisseRemote.findAll();
    	System.out.println(caissestat);
    	for (Caisse C:caissestat){
    		
    	Caisse caisse2 = new Caisse();
    	Date datestat=C.getDate();
    	Long sommestat=C.getSomme_caisse();
    	System.out.println(datestat);	
    	System.out.println(sommestat);
    	XYChart.Series series =new XYChart.Series();
    	series.getData().add(new XYChart.Data(datestat,sommestat));
    	//series.getData().add(new XYChart.Data("1",30));
    	
    	
    	
    	billstat.getData().addAll(series);
    	}
    	billstat.setTitle("Cash-Desk evolution");
    	
    	
    	
    	
    	
			}   	
    }    

    @FXML
    private void onclickbutton(ActionEvent event) throws NamingException {

		InitialContext ctx = null;
		//InitialContext ctx1 = null;
		//Context context1=new InitialContext();
		BillServiceRemote proxy = null;
		//CaisseServiceRemote proxy1 = null;
		ctx = new InitialContext();
		//ctx1 = new InitialContext();
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";
	//String jndiName1 = "esprit1718b4erp-ear/esprit1718b4erp-service/CaisseService!tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote";
       // CaisseServiceRemote CaisseServiceRemote = (CaisseServiceRemote) ctx1.lookup(jndiName1);

		proxy = (BillServiceRemote) ctx.lookup(jndiName);
		
	//Bill b =new Bill(1, "Foussy");
		Bill b=new Bill();
		b.setCustomer_name(custname.getText());
		b.setBilling_adress(billingadresss.getText());
		b.setShipping_adress(shippingadress.getText());
		int y=Integer.parseInt((billamount.getText()));
		if (y>0){
			b.setAmount(y);
		}else if (y<=0){
			Alert alert =new Alert(AlertType.INFORMATION);
		    alert.setTitle("check");
		    alert.setHeaderText("check value");
		    alert.showAndWait();
		}
		b.setAmount(Integer.parseInt((billamount.getText())));
		
		java.sql.Date billdate1 = java.sql.Date.valueOf(billdate.getValue());
		b.setBill_date(billdate1);
		java.sql.Date billdate2 = java.sql.Date.valueOf(topaybefore.getValue());
		b.setTo_pay_before(billdate2);
		
		int x=Integer.parseInt(paymentdue.getText());
		if (x<=Integer.parseInt((billamount.getText()))){
			b.setPayment_due(x);
		}
		else if (x>Integer.parseInt((billamount.getText()))) {
			 Alert alert =new Alert(AlertType.INFORMATION);
			    alert.setTitle("check");
			    alert.setHeaderText("check value");
			    alert.showAndWait();
		}
		
	    b.setType_of_payment(typeofpay.getValue());
	    b.setDeputy_document(billtype.getValue());
	    
	    proxy.save(b);
	    
	    Alert alert =new Alert(AlertType.INFORMATION);
	    alert.setTitle("Bill add statue");
	    alert.setHeaderText("The bill has been added successfully");
	    alert.showAndWait();
	
	    

	int a=0;
	int c=0;
	fiars=proxy.findAll();
	System.out.println(fiars);
	
	
	
	for (Bill B:fiars){
		Date datebill5  = B.getBill_date();
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = datebill5;
		if ((B.getDeputy_document().equalsIgnoreCase("in"))&&(new Date().after(date))){
			a+=B.getPayment_due();
			System.out.println(a);
			
		}
		else if ((B.getDeputy_document().equalsIgnoreCase("out"))&&(new Date().after(date))){
			c+=B.getPayment_due();
			System.out.println(c);
			
			
		}
		
		
		
	}
	
	Long caiss=(long) (a-c);
	System.out.println(caiss);
	caisse.setText(Long.toString(caiss));

	
	String  now=LocalDateTime.now()+"";
	System.out.println(now);
    }

    @FXML
    private void mmb(ActionEvent event) {
    	
    	
    	

    	oblist.remove(oblist);
    		try {
    			String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";
    			Context context = new InitialContext();
    			BillServiceRemote u = (BillServiceRemote) context.lookup(jndiName);
    			oblist = FXCollections.observableArrayList(u.afficherbill());
    			listbill.setItems(oblist);
    		} catch (NamingException e) {
    		}

    	}
    

    @FXML
    private void supprimer(ActionEvent event) throws NamingException {
    	
    	String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";
		Context context = new InitialContext();
		BillServiceRemote b = (BillServiceRemote) context.lookup(jndiName);
		Bill bill = new Bill();
		bill = listbill.getSelectionModel().getSelectedItem();
		System.out.println(bill);
		b.delete(bill);
		
		Alert alert =new Alert(AlertType.INFORMATION);
	    alert.setTitle("Bill delete statue");
	    alert.setHeaderText("The bill has been deleted successfully");
	    alert.showAndWait();
		
		int a=0;
		int c=0;
		fiars=b.findAll();
		System.out.println(fiars);
		for (Bill B:fiars){
			if (B.getDeputy_document().equalsIgnoreCase("in")){
				a+=B.getPayment_due();
				System.out.println(a);
				
			}
			else if (B.getDeputy_document().equalsIgnoreCase("out")){
				c+=B.getPayment_due();
				System.out.println(c);
				
				
			}
			
		}
		
		Long caiss=(long) (a-c);
		System.out.println(caiss);
		caisse.setText(Long.toString(caiss));
		
    	
    }
    
    
    public void afficherlistbill() throws NamingException {
		Context context;
		context = new InitialContext();
		String jndiName = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!"
       			+ "tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";
		BillServiceRemote proxyB = (BillServiceRemote) context.lookup(jndiName);

		customername.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        billingadress.setCellValueFactory(new PropertyValueFactory<>("billing_adress"));
        shipingadress.setCellValueFactory(new PropertyValueFactory<>("shipping_adress"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Due.setCellValueFactory(new PropertyValueFactory<>("payment_due"));
        BillDate.setCellValueFactory(new PropertyValueFactory<>("bill_date"));
        topaybefore1.setCellValueFactory(new PropertyValueFactory<>("to_pay_before"));
        typeofpayment.setCellValueFactory(new PropertyValueFactory<>("type_of_payment"));
		List<Bill> list = proxyB.findAll();
		ObservableList<Bill> items = FXCollections.observableArrayList(list);
		listbill.setItems(items);

	}
    
    
    
    

    @FXML
    private void searchbill(ActionEvent event) throws NamingException {
    	
    	
    	
    	
    	
    	   String jndiName="esprit1718b4erp-ear/esprit1718b4erp-service/BillService!"
       			+ "tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";
   		Context context=new InitialContext();
       	BillServiceRemote b=(BillServiceRemote) context.lookup(jndiName);
           Bill bill =new Bill();
           ObservableList<Bill> allsujet=listbill.getItems();
          bill=listbill.getSelectionModel().getSelectedItem();
          

      
          
          bill.setCustomer_name(custname.getText());
  		bill.setBilling_adress(billingadresss.getText());
  		bill.setShipping_adress(shippingadress.getText());
  		bill.setAmount(Integer.parseInt((billamount.getText())));
  		java.sql.Date billdate1 = java.sql.Date.valueOf(billdate.getValue());
  		bill.setBill_date(billdate1);
  		java.sql.Date billdate2 = java.sql.Date.valueOf(topaybefore.getValue());
  		bill.setTo_pay_before(billdate2);
  		bill.setPayment_due(Integer.parseInt(paymentdue.getText()));
  	    bill.setType_of_payment(typeofpay.getValue());
  	    bill.setDeputy_document(billtype.getValue());
           //System.out.println(c1);
  	  oblist.clear();
           b.update(bill);
           
           
           
           int a=0;
   		int c=0;
   		fiars=b.findAll();
   		System.out.println(fiars);
   		for (Bill B:fiars){
   			if (B.getDeputy_document().equalsIgnoreCase("in")){
   				a+=B.getPayment_due();
   				System.out.println(a);
   				
   			}
   			else if (B.getDeputy_document().equalsIgnoreCase("out")){
   				c+=B.getPayment_due();
   				System.out.println(c);
   				
   				
   			}
   			
   		}
   		
   		Long caiss=(long) (a-c);
   		System.out.println(caiss);
   		caisse.setText(Long.toString(caiss));  
   		
    	
		

   		
   	 ObservableList<Bill> oblistt = FXCollections.observableArrayList();
     oblist = FXCollections.observableArrayList();


     customername.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
     billingadress.setCellValueFactory(new PropertyValueFactory<>("billing_adress"));
     shipingadress.setCellValueFactory(new PropertyValueFactory<>("shipping_adress"));
     amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
     Due.setCellValueFactory(new PropertyValueFactory<>("payment_due"));
     BillDate.setCellValueFactory(new PropertyValueFactory<>("bill_date"));
     topaybefore1.setCellValueFactory(new PropertyValueFactory<>("to_pay_before"));
     typeofpayment.setCellValueFactory(new PropertyValueFactory<>("type_of_payment"));
     inout.setCellValueFactory(new PropertyValueFactory<>("deputy_document"));

     
     
     
    
     
     
     String jndiName1 = "esprit1718b4erp-ear/esprit1718b4erp-service/BillService!tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote";


     Context ctx = null;
		BillServiceRemote proxy = null;
	
			ctx = new InitialContext();
	
		
			proxy = (BillServiceRemote) ctx.lookup(jndiName1);
	
	List<Bill> fousi=proxy.afficherbill();
	/*	fousi.stream().forEach((user) -> {
         oblist.add((Bill) user);
     });*/

     listbill.getItems().addAll(oblist);
     listbill.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
         System.out.println(newSelection);
         if (newSelection != null) {
             sujetSelected = newSelection;

         }

     });
 	
 	
 	
     
     
     
                       
    	
    }
    
    

    
    
    private void StockFilter(String oldValue, String newValue) {
		ObservableList<Bill> filterList = FXCollections.observableArrayList();
		if (billsearch == null || (newValue.length() < oldValue.length()) || newValue == null) {

			listbill.setItems(oblist);

		} else {

			newValue = newValue.toUpperCase();

			for (Bill B : listbill.getItems()) {

				String filterFirstName = B.getCustomer_name();

				if (filterFirstName.toUpperCase().contains(newValue)) {

					filterList.add(B);

				}

			}
			listbill.setItems(filterList);

		}

	}

    @FXML
    private void close(ActionEvent event) throws NamingException {
    	
//    	InitialContext ctx1 = null;
//		CaisseServiceRemote proxy1 = null;
//		
//			ctx1 = new InitialContext();
		
    	String CaisseJindi = "esprit1718b4erp-ear/esprit1718b4erp-service/CaisseService!tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote";
		Context context2 = new InitialContext();
		CaisseServiceRemote C = (CaisseServiceRemote) context2.lookup(CaisseJindi);
		
		//String jndiName1 = "esprit1718b4erp-ear/esprit1718b4erp-service/CaisseService!tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote";
	    
//			CaisseServiceRemote CaisseServiceRemote = (CaisseServiceRemote) ctx1.lookup(jndiName1);
//		
//	
//			proxy1 = (CaisseServiceRemote) ctx1.lookup(jndiName1);
		
	    Caisse c=new Caisse();
	    
	    String  now=LocalDate.now()+"";
	    System.out.println(now);
	    java.sql.Date billdate3 = java.sql.Date.valueOf(now);
	    c.setDate(billdate3);
	    String a= caisse.getText().toString();
	    System.out.println(a);
	   
	    System.out.println(LocalTime.now()); 
	    LocalTime before1= LocalTime.parse("17:00:00.000");
        caissestat=C.findAll();
        System.out.println(caissestat);
	    for(Caisse i:caissestat){
	    	if((i.getDate().equals(now) )|| (LocalTime.now().isBefore(before1))){
	    		System.out.println(c.getDate());
	    		Alert alert =new Alert(AlertType.INFORMATION);
	    	    alert.setTitle("Cash-desk statut");
	    	    alert.setHeaderText("The cash-desk has already been closed");
	    	    alert.showAndWait();
	    	    return;
	    	   
	    	}
	    
	    }
	    c.setSomme_caisse(Long.parseLong(a));
	    C.save(c);
		
		Alert alert =new Alert(AlertType.INFORMATION);
	    alert.setTitle("Cash-desk statut");
	    alert.setHeaderText("The cash-desk has been closed successfully");
	    alert.showAndWait();
    	
    }

    @FXML
    private void Billback(ActionEvent event)throws IOException {
    	
    	Parent espaceResProjects=FXMLLoader.load(getClass().getResource("/fxml/espaceRF.fxml"));
        Scene espaceResProjectsScene=new Scene(espaceResProjects);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(espaceResProjectsScene);
        window.show(); 
    }
    
    public class Datedecrementer {
		  public String addOneDay(String date) {
		    return LocalDate.parse(date).minusDays(1).toString();
		  }
		}
    
}
