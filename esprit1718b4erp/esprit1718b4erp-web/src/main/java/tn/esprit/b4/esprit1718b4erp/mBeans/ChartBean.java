package tn.esprit.b4.esprit1718b4erp.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceLocal;

@ManagedBean(name = "Charts")
@ViewScoped
public class ChartBean {
	private List<User> Listemployee ;

	public List<User> getListemployee() {
		return Listemployee;
	}

	public void setListemployee(List<User> listemployee) {
		Listemployee = listemployee;
	}

	@EJB
	UserServiceLocal userService;

	@PostConstruct
	public void init() {
		Listemployee = userService.findAll();
		for (int i = 0; i < Listemployee.size(); i++) {
			User u = (User) Listemployee.get(i);
			System.out.println("teeeeeeeeeest " + u.getCost_h());
			System.out.println("fiiiiiirst " + u.getFirstname());

		}
		
//		begin tuto
//		class PieChartData {
//			private List<User> Listemployee ;
//
//			 List<User> Listemployee1 = userService.findAll();
//			 for (int i = 0; i < Listemployee.size(); i++) {
//					User u = (User) Listemployee.get(i);
//			 List<User> pieDataList1  = new ArrayList<User>();;
//
//
//		}
//		
		class KeyValue {
			String firstname;
			float cost_h;
			public KeyValue(String firstname, float cost_h) {
				super();
				this.firstname = firstname;
				this.cost_h = cost_h;
			}
			
			
//			tuto
			
			
			public String getFirstname() {
				return firstname;
			}
			public void setFirstname(String firstname) {
				this.firstname = firstname;
			}
			public float getCost_h() {
				return cost_h;
			}
			public void setCost_h(float cost_h) {
				this.cost_h = cost_h;
			}

		}
	}
}
