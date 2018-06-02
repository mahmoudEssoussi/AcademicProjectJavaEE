package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PrimaryMaterialsStock
 *
 */
@Entity
@Table(name = "pmstock")
public class PrimaryMaterialsStock implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdStock")
	private Long IdStock;
	@Column(name = "ref")
	private String ref;
	@Column(name = "itemName")
	private String itemName;
	@Column(name = "dateIn")
	private String dateIn;
	@Column(name = "unitPrice")
	private int unitPrice;
	@Column(name = "stockPrice")
	private int stockPrice;
	@Column(name = "InitialStock")
	private int InitialStock;
	@Column(name = "stockIn")
	private int stockIn;
	@Column(name = "finalStock")
	private int finalStock;
	@Column(name = "stockOut")
	private int stockOut;
	@OneToMany (mappedBy="materials", cascade={CascadeType.MERGE},fetch =FetchType.EAGER)
	private List <ElementProduct> listElementProduct;


	
	
	private static final long serialVersionUID = 1L;

	  

	public PrimaryMaterialsStock() {
		super();
	} 
	public Long getIdStock() {
		return this.IdStock;
	}

	public void setIdStock(Long IdStock) {
		this.IdStock = IdStock;
	}   
	public String getRef() {
		return this.ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}   
	public int getInitialStock() {
		return this.InitialStock;
	}

	public void setInitialStock(int InitialStock) {
		this.InitialStock = InitialStock;
	}   
	public String getDateIn() {
		return this.dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}   
	public int getStockOut() {
		return this.stockOut;
	}

	public void setStockOut(int stockOut) {
		this.stockOut = stockOut;
	}   
	public int getFinalStock() {
		return this.finalStock;
	}

	public void setFinalStock(int finalStock) {
		this.finalStock = finalStock;
	}   
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}   

	public int getStockIn() {
		return stockIn;
	}
	public void setStockIn(int stockIn) {
		this.stockIn = stockIn;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}
	@Override
	public String toString() {
		return "PrimaryMaterialsStock [IdStock=" + IdStock + ", ref=" + ref + ", itemName=" + itemName + ", dateIn="
				+ dateIn + ", unitPrice=" + unitPrice + ", stockPrice=" + stockPrice + ", InitialStock=" + InitialStock
				+ ", stockIn=" + stockIn + ", finalStock=" + finalStock + ", stockOut=" + stockOut
				+ ", listElementProduct=" + listElementProduct + "]";
	}
	public List<ElementProduct> getListElementProduct() {
		return listElementProduct;
	}
	public void setListElementProduct(List<ElementProduct> listElementProduct) {
		this.listElementProduct = listElementProduct;
	}
	
	

   
}
