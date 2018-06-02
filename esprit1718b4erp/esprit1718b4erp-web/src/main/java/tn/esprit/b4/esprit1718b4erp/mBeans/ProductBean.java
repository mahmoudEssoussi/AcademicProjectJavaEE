package tn.esprit.b4.esprit1718b4erp.mBeans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import tn.esprit.b4.esprit1718b4erp.entities.ElementProduct;
import tn.esprit.b4.esprit1718b4erp.entities.PrimaryMaterialsStock;
import tn.esprit.b4.esprit1718b4erp.entities.Product;
import tn.esprit.b4.esprit1718b4erp.services.ElementProductServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.PrimaryMaterialsStockServicesRemote;
import tn.esprit.b4.esprit1718b4erp.services.ProductServicesRemote;


@ManagedBean
@SessionScoped
public class ProductBean {
	private static final long serialVersionUID = 1L;
	@EJB
	private ProductServicesRemote ProductService;
	@EJB
	private ElementProductServicesRemote ElementService;
	@EJB
	private PrimaryMaterialsStockServicesRemote PrimarymaterialsS;

	private PrimaryMaterialsStock Pm ;
	private Product Product ;
	private ElementProduct  Element ;
	
	private List<PrimaryMaterialsStock> ListPrimary=new ArrayList<PrimaryMaterialsStock>();
	private List<Product> ListProduct=new ArrayList<Product>();
	private List<ElementProduct> ListElement=new ArrayList<ElementProduct>();
	
	int quantity ;

	private Product AnalysProduct ;
	
	
	public ProductBean() {}
	
	@PostConstruct
	public void init() {
	ListPrimary=PrimarymaterialsS.DisplayAllPstock();
	ListProduct=ProductService.DisplayAllPstock();
	ListElement=ElementService.DisplayAllPstock();
	Pm =new PrimaryMaterialsStock();
	Product =new Product();
	Element =new ElementProduct();
	quantity=0;

	System.out.println("ya khraaé");
	
	}
	
	public String addProduct() {
		
		ProductService.addPRoduct(Product);;
		Product =new Product();
		
		return "Product?faces-redirect=true";
	}
	
	public String removeProduct(Product P) {
		
		ProductService.delete(P);
	return "PrimaryMaterials?faces-redirect=true";
	}
	
	

public String Superadd() {
	
	System.out.println(Element);
	System.out.println(Product);
	System.out.println(Pm);
	System.out.println(quantity);
		ElementService.SuperAdd(Element,Product, Pm, quantity);
		Pm =new PrimaryMaterialsStock();
		Product =new Product();
		Element =new ElementProduct();
		quantity=0;

	
	return "Product?faces-redirect=true";
	}
	
    public List<Product> displayallProduct(){
		
    	ListProduct=ProductService.DisplayAllPstock();
    
		return ListProduct;
		
	}
    
  public List<PrimaryMaterialsStock> displayall(Product pp){
		
	  List<PrimaryMaterialsStock> ListP=new ArrayList<PrimaryMaterialsStock>();
	   for (ElementProduct p : pp.getListElementProduct()){
	        
		   ListP.add(p.getMaterials());
	 }
    
		return ListP;
		
	}

  
  public Product getAnalysProduct() {
	return AnalysProduct;
}

public void setAnalysProduct(Product analysProduct) {
	AnalysProduct = analysProduct;
}

public String  ViewStat(Product pp){
	  AnalysProduct=pp;
	  
	  return "Analys?faces-redirect=true";
  }
    
    
	public ProductServicesRemote getProductService() {
		return ProductService;
	}

	public void setProductService(ProductServicesRemote productService) {
		ProductService = productService;
	}

	public ElementProductServicesRemote getElementService() {
		return ElementService;
	}

	public void setElementService(ElementProductServicesRemote elementService) {
		ElementService = elementService;
	}

	public PrimaryMaterialsStockServicesRemote getPrimarymaterialsS() {
		return PrimarymaterialsS;
	}

	public void setPrimarymaterialsS(PrimaryMaterialsStockServicesRemote primarymaterialsS) {
		PrimarymaterialsS = primarymaterialsS;
	}

	public PrimaryMaterialsStock getPm() {
		return Pm;
	}

	public void setPm(PrimaryMaterialsStock pm) {
		Pm = pm;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	public ElementProduct getElement() {
		return Element;
	}

	public void setElement(ElementProduct element) {
		Element = element;
	}

	public List<PrimaryMaterialsStock> getListPrimary() {
		return ListPrimary;
	}

	public void setListPrimary(List<PrimaryMaterialsStock> listPrimary) {
		ListPrimary = listPrimary;
	}

	public List<Product> getListProduct() {
		return ListProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		ListProduct = listProduct;
	}

	public List<ElementProduct> getListElement() {
		return ListElement;
	}

	public void setListElement(List<ElementProduct> listElement) {
		ListElement = listElement;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
	 public Product getProduct(Integer id) {
	        if (id == null){
	            throw new IllegalArgumentException("no id provided");
	        }
	        for (Product p : ListProduct){
	        
	     
	        	Integer i = (int) (long) p.getIdProduct();
	        
	            if (id.equals(i)){
	           
	                return p;
	            }
	        }
	        return null;
	    }

	 
	  public Map<Integer,PrimaryMaterialsStock> display(){
			
		  List<PrimaryMaterialsStock> ListP=new ArrayList<PrimaryMaterialsStock>();
		 
		  TreeMap<Integer, PrimaryMaterialsStock> Word = new TreeMap<Integer,PrimaryMaterialsStock>();
		  
		   for (ElementProduct p : AnalysProduct.getListElementProduct()){
		        
			   ListP.add(p.getMaterials());
			 
			   Word.put(p.getQuantity(), p.getMaterials());
		 }
		   
		
	    
		   System.out.println(Word);
		
			return Word;
			
		}
	
}
