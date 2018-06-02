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
import tn.esprit.b4.esprit1718b4erp.entities.Order;
import tn.esprit.b4.esprit1718b4erp.entities.OrderDetails;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.services.OrderServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.OrdersDetailsServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote;
import tn.esprit.b4.esprit1718b4erp.entities.Product;

@ManagedBean
@SessionScoped
public class OrderBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProductServicesRemote ProductService;
	@EJB
	private OrdersDetailsServicesRemote OrdersDetailsService;
	@EJB
	private OrderServicesRemote OrdersService;

	private Order order;
	private Product Product;
	private OrderDetails orderDetails;

	private List<Product> ListProduct = new ArrayList<Product>();
	private List<Order> ListOrder = new ArrayList<Order>();
	private List<OrderDetails> ListOrderDetails = new ArrayList<OrderDetails>();
	
	private Order o;

	int quantity;

	public OrderBean() {

	}

	@PostConstruct
	public void init() {

		ListProduct = ProductService.DisplayAllPstock();
		ListOrder = OrdersService.DisplayAllOrder();
		ListOrderDetails = OrdersDetailsService.DisplayAll();
		order = new Order();
		Product = new Product();
		orderDetails = new OrderDetails();

		quantity = 0;

		System.out.println("ya khraaé");
	}

	public String addOrder() {

		OrdersService.addOrder(order);
		order = new Order();

		return "Orders?faces-redirect=true";
	}

	public String removeOrder(Order P) {

		OrdersService.delete(P);
		return "Orders?faces-redirect=true";
	}

	public String Superadd() {
		System.out.println(quantity);
		OrdersDetailsService.SuperAdd(orderDetails, Product, order, quantity);
		order = new Order();
		Product = new Product();
		orderDetails = new OrderDetails();
		quantity = 0;

		return "Orders?faces-redirect=true";
	}

	public List<Order> displayallOrder() {

		ListOrder = OrdersService.DisplayAllOrder();
		return ListOrder;

	}

	public List<Product> displayall(Order pp) {

		List<Product> ListP = new ArrayList<Product>();
		for (OrderDetails p : pp.getListOrderProduct()) {

			ListP.add(p.getProductOrder());

		}

		return ListP;

	}
	
	
	
	
	public ProductServicesRemote getProductService() {
		return ProductService;
	}

	public void setProductService(ProductServicesRemote productService) {
		ProductService = productService;
	}

	public OrdersDetailsServicesRemote getOrdersDetailsService() {
		return OrdersDetailsService;
	}

	public void setOrdersDetailsService(OrdersDetailsServicesRemote ordersDetailsService) {
		OrdersDetailsService = ordersDetailsService;
	}

	public OrderServicesRemote getOrdersService() {
		return OrdersService;
	}

	public void setOrdersService(OrderServicesRemote ordersService) {
		OrdersService = ordersService;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<Product> getListProduct() {
		return ListProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		ListProduct = listProduct;
	}

	public List<Order> getListOrder() {
		return ListOrder;
	}

	public void setListOrder(List<Order> listOrder) {
		ListOrder = listOrder;
	}

	public List<OrderDetails> getListOrderDetails() {
		return ListOrderDetails;
	}

	public void setListOrderDetails(List<OrderDetails> listOrderDetails) {
		ListOrderDetails = listOrderDetails;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Order p : ListOrder) {

			Integer i = (int) (long) p.getId();

			if (id.equals(i)) {

				return p;
			}
		}
		return null;
	}
	
	public String showStat(Order orderSelected){
		
		o=orderSelected;
		
		return "statOrder?faces-redirect=true";
	}

	public Order getO() {
		return o;
	}

	public void setO(Order o) {
		this.o = o;
	}

	
	
}
