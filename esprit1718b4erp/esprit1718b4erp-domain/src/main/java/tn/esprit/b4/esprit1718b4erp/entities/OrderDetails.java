package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	private Product productOrder;
	@ManyToOne
	private Order order;
	@Column(name = "unitPrice")
	private int unitprice;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "Date")
	private LocalDate Date;
	
	private static final long serialVersionUID = 1L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(Product productOrder) {
		this.productOrder = productOrder;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		LocalDate currentDate = LocalDate.now();
		this.Date=currentDate;
	}
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", productOrder=" + productOrder + ", order=" + order + ", unitprice="
				+ unitprice + ", quantity=" + quantity + ", Date=" + Date + "]";
	}
	
	
	

}
