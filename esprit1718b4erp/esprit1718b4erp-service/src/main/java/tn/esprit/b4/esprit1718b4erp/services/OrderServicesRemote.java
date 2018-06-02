package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.OrderDetails;
import tn.esprit.b4.esprit1718b4erp.entities.Product;


@Remote
public interface OrderServicesRemote {
	
	public void addOrder(Order B) ;
	public void delete(Order B) ;
	public void update(Order B);
	public Order FindById(Long id);
	public List<Order> DisplayAllOrder();


}
