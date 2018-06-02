package tn.esprit.b4.esprit1718b4erp.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.OrderDetails;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

/**
 * Session Bean implementation class OrdersDetailsServices
 */
@Stateless
@LocalBean
public class OrdersDetailsServices implements OrdersDetailsServicesRemote, OrdersDetailsServicesLocal {

	@PersistenceContext(unitName="erp-ejb")
	EntityManager em;
	
	@EJB
	ProductServicesRemote P;
	@EJB
	OrderServicesRemote O;
    /**
     * Default constructor. 
     */
    public OrdersDetailsServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void add(OrderDetails B) {
		em.persist(B);
		
	}

	@Override
	public void delete(OrderDetails B) {
		em.remove(em.merge(B));
		
	}

	@Override
	public void update(OrderDetails B) {
		em.merge(B);
		
	}

	@Override
	public OrderDetails FindById(Long id) {
		 return	em.find(OrderDetails.class, id) ;
	}

	@Override
	public List<OrderDetails> DisplayAll() {
		TypedQuery<OrderDetails> q=em.createQuery("SELECT p FROM OrderDetails p", OrderDetails.class);
		List<OrderDetails> res=q.getResultList();
		
		
		return res;
	}
	
	@Override
	public void SuperAdd(OrderDetails details, Product product, Order order, Integer quantity) {
		int Final = product.getUnitInStock();
		int Final2 = product.getUnitInOrder();

		if (Final < quantity) {
		


		} else {

			if (order.getListOrderProduct().isEmpty())

			{// mis a jour du produit
				product.setUnitInStock(Final - quantity);
				product.setUnitInOrder(Final2 + quantity);
				P.update(product);
				// mis a jour du order

				int shippingCost = order.getShippingCost();
				int totalQuantity = order.getQuantity() + quantity;
				int unitprice = (shippingCost + order.getQuantity() * order.getUnitSellingPrice()
						+ quantity * product.getUnitPrice()) / totalQuantity;
				order.setUnitSellingPrice(unitprice);
				order.setQuantity(totalQuantity);
				O.update(order);

				// mis a jour du tableau en commun
				details.setOrder(order);
				details.setProductOrder(product);
				details.setQuantity(quantity);
				details.setUnitprice(product.getUnitPrice());
				LocalDate date = null;
				details.setDate(date);

				add(details);
			}

			else {
			

			}
		
	}
	}

}
