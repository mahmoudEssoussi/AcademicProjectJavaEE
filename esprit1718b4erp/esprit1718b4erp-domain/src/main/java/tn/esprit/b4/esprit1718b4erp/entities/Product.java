package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name = "Product")
public class Product implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idProduct")
	private Long idProduct;
	@Column(name = "refProduct")
	private String refProduct;
	@Column(name = "name")
	private String name;
	@Column(name = "unitPrice")
	private int unitPrice;
	@Column(name = "unitInStock")
	private int unitInStock;
	@Column(name = "costPraimaryMaterials")
	private int costPraimaryMaterials;
	@Column(name = "manifacturCost")
	private int manifacturCost;
	@Column(name = "hrCost")
	private int hrCost;
	@Column(name = "unitInOrder")
	private int unitInOrder;
	@Column(name = "idProject")
	private int idProject;
	
	@OneToMany (mappedBy="product", cascade={CascadeType.MERGE},fetch =FetchType.EAGER)
	private List <ElementProduct> listElementProduct;
	@OneToMany (mappedBy="productOrder", cascade={CascadeType.MERGE},fetch =FetchType.EAGER)
	private List <OrderDetails> listOrderProduct;

	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}   
	public Long getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}   
	public String getRefProduct() {
		return this.refProduct;
	}

	public void setRefProduct(String refProduct) {
		this.refProduct = refProduct;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public int getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}   
	public int getUnitInStock() {
		return this.unitInStock;
	}

	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}   
	public int getCostPraimaryMaterials() {
		return this.costPraimaryMaterials;
	}

	public void setCostPraimaryMaterials(int costPraimaryMaterials) {
		this.costPraimaryMaterials = costPraimaryMaterials;
	}   
	public int getManifacturCost() {
		return this.manifacturCost;
	}

	public void setManifacturCost(int manifacturCost) {
		this.manifacturCost = manifacturCost;
	}   
	public int getHrCost() {
		return this.hrCost;
	}

	public void setHrCost(int hrCost) {
		this.hrCost = hrCost;
	}   
	public int getUnitInOrder() {
		return this.unitInOrder;
	}


	public void setUnitInOrder(int unitInOrder) {
		this.unitInOrder = unitInOrder;
	}
	
	public int getIdProject() {
		return idProject;
	}
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", refProduct=" + refProduct + ", name=" + name + ", unitPrice="
				+ unitPrice + ", unitInStock=" + unitInStock + ", costPraimaryMaterials=" + costPraimaryMaterials
				+ ", manifacturCost=" + manifacturCost + ", hrCost=" + hrCost + ", unitInOrder=" + unitInOrder
				+ ", idProject=" + idProject + ", listElementProduct=" + listElementProduct + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (costPraimaryMaterials != other.costPraimaryMaterials)
			return false;
		if (hrCost != other.hrCost)
			return false;
		if (idProduct != other.idProduct)
			return false;
		if (manifacturCost != other.manifacturCost)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (refProduct != other.refProduct)
			return false;
		if (unitInOrder != other.unitInOrder)
			return false;
		if (unitInStock != other.unitInStock)
			return false;
		if (unitPrice != other.unitPrice)
			return false;
		return true;
	}
	public List<ElementProduct> getListElementProduct() {
		return listElementProduct;
	}
	public void setListElementProduct(List<ElementProduct> listElementProduct) {
		this.listElementProduct = listElementProduct;
	}

	
	
	
}
