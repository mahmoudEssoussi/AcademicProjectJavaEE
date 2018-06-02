package tn.esprit.b4.esprit1718b4erp.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.OrderDetails;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

@Remote
public interface OrdersDetailsServicesRemote {
	public void add(OrderDetails B) ;
	public void delete(OrderDetails B) ;
	public void update(OrderDetails B);
	public OrderDetails FindById(Long id);
	public List<OrderDetails> DisplayAll();
	public void SuperAdd(OrderDetails details, Product product, Order order, Integer quantity);
}
