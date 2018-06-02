package tn.esprit.b4.esprit1718b4erp.mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.Notification;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.services.NotificationServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;


@ManagedBean
@SessionScoped
public class PrimaryStock implements Serializable{
	private static final long serialVersionUID = 1L;
	String quantity;
	String unitPrice;
	int nbr =0;
	@EJB
	private PrimaryMaterialsStockServicesRemote PrimarymaterialsS;
	@EJB
	private NotificationServicesRemote notifServices;
	private Notification notif;
	private PrimaryMaterialsStock Pms =new PrimaryMaterialsStock();
	private PrimaryMaterialsStock dd ;
	private List<PrimaryMaterialsStock> ListPms=new ArrayList<PrimaryMaterialsStock>();
	private List<Notification> ListNR=new ArrayList<Notification>();
	private List<Notification> ListReaded=new ArrayList<Notification>();

	public PrimaryStock() {}

	@PostConstruct
	public void init() {

		ListPms=PrimarymaterialsS.DisplayAllPstock();
		
	
		System.out.println(ListNR);
		

	}

	public String delete(PrimaryMaterialsStock Pms ) {
		PrimarymaterialsS.delete(Pms);

		return "PrimaryMaterials?faces-redirect=true";
	}
	public String add() {

		PrimarymaterialsS.addPstock(Pms);
		Pms =new PrimaryMaterialsStock();


		return "PrimaryMaterials?faces-redirect=true";
	}

	public String SuperAdd() {

		PrimarymaterialsS.SuperUpdate(dd, quantity, unitPrice);
		return "PrimaryMaterials?faces-redirect=true";
	}

	public List<PrimaryMaterialsStock> displayallPms(){

		ListPms=PrimarymaterialsS.DisplayAllPstock();
		System.out.println(ListPms);
		return ListPms;

	}

	
	public List<Notification> notificationNonReaded(){

		  List<Notification> ListNR=new ArrayList<Notification>();
		
		  ListNR=notifServices.DisplayAllnotif(12);
			nbr = ListNR.size();
		
		return ListNR;

	}

	public List<Notification> AllReaded(){

		  List<Notification> ListReaded=new ArrayList<Notification>();
		
		  ListReaded=notifServices.DisplayAll(12);
		
		return ListReaded;

	}
	

	public PrimaryMaterialsStock getPms() {
		return Pms;
	}
	public void setPms(PrimaryMaterialsStock pms) {
		Pms = pms;
	}
	public List<PrimaryMaterialsStock> getListPms() {
		return ListPms;
	}
	public void setListPms(List<PrimaryMaterialsStock> listPms) {
		ListPms = listPms;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public PrimaryMaterialsStock getDd() {
		return dd;
	}

	public void setDd(PrimaryMaterialsStock dd) {
		this.dd = dd;
	}



	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public PrimaryMaterialsStockServicesRemote getPrimarymaterialsS() {
		return PrimarymaterialsS;
	}

	public void setPrimarymaterialsS(PrimaryMaterialsStockServicesRemote primarymaterialsS) {
		PrimarymaterialsS = primarymaterialsS;
	}

	public NotificationServicesRemote getNotifServices() {
		return notifServices;
	}

	public void setNotifServices(NotificationServicesRemote notifServices) {
		this.notifServices = notifServices;
	}

	public Notification getNotif() {
		return notif;
	}

	public void setNotif(Notification notif) {
		this.notif = notif;
	}

	public List<Notification> getListNR() {
		return ListNR;
	}

	public void setListNR(List<Notification> listNR) {
		ListNR = listNR;
	}

	public List<Notification> getListReaded() {
		return ListReaded;
	}

	public void setListReaded(List<Notification> listReaded) {
		ListReaded = listReaded;
	}

	
	public PrimaryMaterialsStock getBeer(Integer id) {
		if (id == null){
			throw new IllegalArgumentException("no id provided");
		}
		for (PrimaryMaterialsStock p : ListPms){


			Integer i = (int) (long) p.getIdStock();

			if (id.equals(i)){

				return p;
			}
		}
		return null;
	}


}
