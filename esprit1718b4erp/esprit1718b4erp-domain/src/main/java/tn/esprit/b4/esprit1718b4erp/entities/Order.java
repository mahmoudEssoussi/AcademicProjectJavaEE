package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "costumer")
	private String costumer;
	@Column(name = "orderDate")
	private java.util.Date orderDate;
	@Column(name = "shippedDate")
	private java.util.Date shippedDate;
	@Column(name = "unitSellingPrice")
	private int unitSellingPrice;
	
	@Column(name = "adress")
	private String adress;
	@Column(name = "shippingCost")
	private int shippingCost;

	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "sellingPriceUnit")
	private int sellingPriceUnit;
	
	@Column(name = "Bill")
	private int Bill;
	
	@Column(name = "Affected")
	private String Affected;
	
	
	
	@OneToMany (mappedBy="order", cascade={CascadeType.MERGE},fetch =FetchType.EAGER)
	private List <OrderDetails> listOrderProduct;
	
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCostumer() {
		return costumer;
	}

	public void setCostumer(String costumer) {
		this.costumer = costumer;
	}



	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public java.util.Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(java.util.Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public int getUnitSellingPrice() {
		return unitSellingPrice;
	}

	public void setUnitSellingPrice(int unitSellingPrice) {
		this.unitSellingPrice = unitSellingPrice;
	}

	public List<OrderDetails> getListOrderProduct() {
		return listOrderProduct;
	}

	public void setListOrderProduct(List<OrderDetails> listOrderProduct) {
		this.listOrderProduct = listOrderProduct;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSellingPriceUnit() {
		return sellingPriceUnit;
	}

	public void setSellingPriceUnit(int sellingPriceUnit) {
		this.sellingPriceUnit = sellingPriceUnit;
	}

	public int getBill() {
		return Bill;
	}

	public void setBill(int bill) {
		Bill = bill;
	}

	public String getAffected() {
		return Affected;
	}

	public void setAffected(String affected) {
		Affected = affected;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", costumer=" + costumer + ", orderDate=" + orderDate + ", shippedDate="
				+ shippedDate + ", unitSellingPrice=" + unitSellingPrice + ", adress=" + adress + ", shippingCost="
				+ shippingCost + ", quantity=" + quantity + ", sellingPriceUnit=" + sellingPriceUnit + ", Bill=" + Bill
				+ ", Affected=" + Affected + ", listOrderProduct=" + listOrderProduct + "]";
	}






	
	
	
}
