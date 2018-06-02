package tn.esprit.b4.esprit1718b4erp.mBeans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import org.primefaces.event.SelectEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote;
@ManagedBean
@SessionScoped
public class BillBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private BillServiceRemote BillServices;
	@EJB
	private OrderServicesRemote Orderservice;
	@EJB
	private CaisseServiceRemote CaisseServices;

	private Bill bill;
	private Date date1;
	private List<Bill> ListBill = new ArrayList<Bill>();
	private List<Bill> ArchiveBill = new ArrayList<Bill>();
	private List<Bill> nonPayedBill = new ArrayList<Bill>();
	private List<Order> ListOrder = new ArrayList<Order>();
	 private String[] selectedInOut;
	 private List<String> inout;
	 private Order order;
	 private Caisse caisse;
	 
	 private List <Order> all =new ArrayList<Order>();
	 private List <Order> NonAffected =new ArrayList<Order>();
	 private Long id;
	 
	 
	public BillBean() {
		
	}
	
	
	

	@PostConstruct
	public void init() {
		ListBill = BillServices.findAll();
		ArchiveBill=BillServices.displayArchive();
		nonPayedBill=BillServices.afficherbill();
		bill =new Bill();
		order=new Order();
		caisse=new Caisse();
		
		
	NonAffected=ReturnNonAffected();
	
	
	}

	public String addBill() {
        
		BillServices.addbill(bill);
		bill = new Bill();
		
		
		 int w=0;
	    	int c=0;
	    	ListBill=BillServices.findAll();
	    	
	    	for (Bill B:ListBill){
	    		Date datebill5  = B.getBill_date();
	    		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    		if ((B.getDeputy_document().equalsIgnoreCase("in"))&&(new Date().after(datebill5))){
					w+=B.getPayment_due();
					System.out.println(w);
					
				}
				else if ((B.getDeputy_document().equalsIgnoreCase("out"))&&(new Date().after(datebill5))){
					c+=B.getPayment_due();
					System.out.println(c);
					
					
				}
	    		
	    	}
	    	
	    	Long caiss=(long) (w-c);
	    	System.out.println(caiss);
	    	
	    	caisse.setSomme_caisse(caiss);
	    	Date date = java.sql.Date.valueOf(LocalDate.now());
	    	
	    	caisse.setDate(date);
	    	CaisseServices.save(caisse);
		
		

		return "SBills?faces-redirect=true";
	}

	public String deleteBill(Bill B) {

		BillServices.delete(B);
		bill = new Bill();

		return "SBills?faces-redirect=true";
	}

	public List<Bill> displayAll() {
		ListBill = BillServices.findAll();

		return ListBill;

	}

	public List<Bill> ArchiveBill() {
		ArchiveBill = BillServices.displayArchive();

		return ArchiveBill;

	}

	public List<Bill> nonPayedBill() {
		nonPayedBill = BillServices.afficherbill();

		return nonPayedBill;

	}
	
	public String modifyBill(Bill bill) {

		BillServices.update(bill);
		bill = new Bill();

		return "Bills?faces-redirect=true";
	}
   
	
	public String[] getSelectedInOut() {
        return selectedInOut;
    }
 
    public void setSelectedInOut(String[] InOut) {
        this.selectedInOut = selectedInOut;
    }
	
	
	public BillServiceRemote getBillServices() {
		return BillServices;
	}

	public void setBillServices(BillServiceRemote billServices) {
		BillServices = billServices;
	}

	

	public Bill getBill() {
		return bill;
	}




	public void setBill(Bill bill) {
		this.bill = bill;
	}




	

	public List<Bill> getListBill() {
		return ListBill;
	}




	public void setListBill(List<Bill> listBill) {
		ListBill = listBill;
	}




	public List<Bill> getArchiveBill() {
		return ArchiveBill;
	}

	public void setArchiveBill(List<Bill> archiveBill) {
		ArchiveBill = archiveBill;
	}

	public List<Bill> getNonPayedBill() {
		return nonPayedBill;
	}

	public void setNonPayedBill(List<Bill> nonPayedBill) {
		this.nonPayedBill = nonPayedBill;
	}
	
	
	public List <Order> ReturnNonAffected(){
		List <Order> all =new ArrayList<Order>();
		List <Order> NonAffected =new ArrayList<Order>();
		all=Orderservice.DisplayAllOrder();
		for(Order P : all){
			if (P.getAffected().equals("no")){
				NonAffected.add(P);
			}
			
			
		}
		
		return NonAffected;
		
	}
	
	public String AffectOrder (){
		
		order=getOrder(id);
		
		order.setAffected("oui");
		Orderservice.update(order);
		
		bill.setAmount(order.getBill());
		bill.setBill_date(order.getOrderDate());
		bill.setCustomer_name(order.getCostumer());
		bill.setBilling_adress(order.getAdress());
		BillServices.addbill(bill);
		bill =new Bill();
		order = new Order();
		
		return "SBills?faces-redirect=true";
	}




	public Order getOrder() {
		return order;
	}




	public void setOrder(Order order) {
		this.order = order;
	}




	public OrderServicesRemote getOrderservice() {
		return Orderservice;
	}




	public void setOrderservice(OrderServicesRemote orderservice) {
		Orderservice = orderservice;
	}




	public List<Order> getListOrder() {
		return ListOrder;
	}




	public void setListOrder(List<Order> listOrder) {
		ListOrder = listOrder;
	}




	public List<Order> getAll() {
		return all;
	}




	public void setAll(List<Order> all) {
		this.all = all;
	}




	public List<Order> getNonAffected() {
		return NonAffected;
	}




	public void setNonAffected(List<Order> nonAffected) {
		NonAffected = nonAffected;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Order p : NonAffected) {

			

			if (p.getId().equals(id)) {

				return p;
			}
		}
		return null;
	}
	
	public void PDF(Bill B) 
    {
    	Document document = new Document();
    try
    {
       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/jee/Details_Bill.pdf"));
       document.open();
      
      PdfPTable table = new PdfPTable(5); // 3 columns.
      table.setWidthPercentage(100); //Width 100%
      table.setSpacingBefore(10f); //Space before table
      table.setSpacingAfter(10f); //Space after table

      //Set Column widths
      float[] columnWidths = {1f,1f,1f,1f,1f};
      table.setWidths(columnWidths);

       PdfPCell c1 = new PdfPCell(new Phrase("Name Product"));
      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(c1);
      
	      c1 = new PdfPCell(new Phrase("Customer"));
	      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(c1);
	      c1 = new PdfPCell(new Phrase("Amout"));
	      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(c1);
	      c1 = new PdfPCell(new Phrase("Adress"));
	      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(c1);
	
	      c1 = new PdfPCell(new Phrase("Date"));
	      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(c1);
	      table.setHeaderRows(1);
	      
		 Bill b = new Bill();
			List<Bill> o = BillServices.findAll();
			
			//tableProduit.setItems(data1);
	      for (int i = 0; i < o.size(); i++) {
	        
            table.addCell(o.get(i).getCustomer_name());
            table.addCell(o.get(i).getAmount()+"");
            table.addCell(o.get(i).getBilling_adress());
            table.addCell(o.get(i).getBill_date()+"");
            table.addCell(o.get(i).getTo_pay_before()+"");  } 
      		document.add(new Paragraph("*****Liste Des produits*****"));
		      document.add(table);
		      
		      document.close();
		      writer.close();
		    } catch (DocumentException e)
		    {
		       e.printStackTrace();
		    } catch (FileNotFoundException e)
		    {
		       e.printStackTrace();
}}
	
	
}

