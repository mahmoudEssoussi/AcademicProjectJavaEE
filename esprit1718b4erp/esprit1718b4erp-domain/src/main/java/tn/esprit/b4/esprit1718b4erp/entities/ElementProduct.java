package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ElementProduct
 *
 */
@Entity
@Table(name = "ElementProduct")
public class ElementProduct implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	private Product product;
	@ManyToOne(fetch= FetchType.EAGER)
	private PrimaryMaterialsStock materials;
	
	@Column(name = "quantity")
	private int quantity;
	private static final long serialVersionUID = 1L;

	public ElementProduct() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public PrimaryMaterialsStock getMaterials() {
		return materials;
	}
	public void setMaterials(PrimaryMaterialsStock materials) {
		this.materials = materials;
	}
	@Override
	public String toString() {
		return "ElementProduct [id=" + id + ", quantity=" + quantity + "]";
	}

   
}
