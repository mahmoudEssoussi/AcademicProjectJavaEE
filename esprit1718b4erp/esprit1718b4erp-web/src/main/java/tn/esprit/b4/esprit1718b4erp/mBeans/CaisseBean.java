package tn.esprit.b4.esprit1718b4erp.mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.primefaces.event.SelectEvent;

import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.Bill;
import tn.esprit.b4.esprit1718b4erp.entities.Caisse;
import tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.BillServiceRemote;
import tn.esprit.b4.esprit1718b4erp.services.CaisseServiceRemote;
@ManagedBean
@SessionScoped
public class CaisseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private BillServiceRemote BillServices;
	
	@EJB
	private CaisseServiceRemote CaisseServices;
	
	private Caisse caisse;
	private Caisse caisse1;
	
	private Bill bill;
	private List<Bill> ListBill = new ArrayList<Bill>();
	private List<Caisse> ListCaisse = new ArrayList<Caisse>();
	private long a;
	public List<Caisse> getListCaisse() {
		return ListCaisse;
	}

	public void setListCaisse(List<Caisse> listCaisse) {
		ListCaisse = listCaisse;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public CaisseBean(){
		
	}
	
	@PostConstruct
	
	public void init() {
		ListBill = BillServices.findAll();
		ListCaisse = CaisseServices.findAll();
		bill =new Bill();
		caisse= new Caisse();
		caisse1= new Caisse();
		ListCaisse=firas();
		
		
		
		
		
	}
	
	
	public Caisse getCaisse1() {
		return caisse1;
	}

	public void setCaisse1(Caisse caisse1) {
		this.caisse1 = caisse1;
	}

	public List<Caisse> firas() {
		
		Caisse c = new Caisse();
		
		ListCaisse= CaisseServices.afficherCaisse();
		System.out.println(ListCaisse);
		return ListCaisse;

	}
	
	public String addCaisse() {

		CaisseServices.save(caisse);
		caisse = new Caisse();

		return "Caisse?faces-redirect=true";
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	
	
}
