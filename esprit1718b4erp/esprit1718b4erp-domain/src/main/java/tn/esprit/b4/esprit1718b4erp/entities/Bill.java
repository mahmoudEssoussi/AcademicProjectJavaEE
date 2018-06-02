package tn.esprit.b4.esprit1718b4erp.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Enumerated(EnumType.STRING)
	private TypeBill type ;
    
	@Column(name = "customer_name")
	private String customer_name ;
    
	@Column(name = "billing_adress")
	private String billing_adress;
	
	@Column(name = "shipping_adress")
	private String shipping_adress;
	
	
	@Column(name = "bill_date")
	private java.util.Date bill_date;
	
	@Column(name = "type_of_payment")
	private String type_of_payment;
	
	
	@Column(name = "to_pay_before")
	private java.util.Date to_pay_before;
	
	@Column(name = "deputy_document")
	private String deputy_document;
	
	@Column(name = "payment_due")
	private int payment_due;
	
	@Column(name = "amount")
	private int amount;
	
	
	
	

	
	public java.util.Date getBill_date() {
		return bill_date;
	}


	public void setBill_date(java.util.Date bill_date) {
		this.bill_date = bill_date;
	}


	public java.util.Date getTo_pay_before() {
		return to_pay_before;
	}


	public void setTo_pay_before(java.util.Date to_pay_before) {
		this.to_pay_before = to_pay_before;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((bill_date == null) ? 0 : bill_date.hashCode());
		result = prime * result + ((billing_adress == null) ? 0 : billing_adress.hashCode());
		result = prime * result + ((customer_name == null) ? 0 : customer_name.hashCode());
		result = prime * result + ((deputy_document == null) ? 0 : deputy_document.hashCode());
		result = prime * result + id;
		result = prime * result + payment_due;
		result = prime * result + ((shipping_adress == null) ? 0 : shipping_adress.hashCode());
		result = prime * result + ((to_pay_before == null) ? 0 : to_pay_before.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((type_of_payment == null) ? 0 : type_of_payment.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (amount != other.amount)
			return false;
		if (bill_date == null) {
			if (other.bill_date != null)
				return false;
		} else if (!bill_date.equals(other.bill_date))
			return false;
		if (billing_adress == null) {
			if (other.billing_adress != null)
				return false;
		} else if (!billing_adress.equals(other.billing_adress))
			return false;
		if (customer_name == null) {
			if (other.customer_name != null)
				return false;
		} else if (!customer_name.equals(other.customer_name))
			return false;
		if (deputy_document == null) {
			if (other.deputy_document != null)
				return false;
		} else if (!deputy_document.equals(other.deputy_document))
			return false;
		if (id != other.id)
			return false;
		if (payment_due != other.payment_due)
			return false;
		if (shipping_adress == null) {
			if (other.shipping_adress != null)
				return false;
		} else if (!shipping_adress.equals(other.shipping_adress))
			return false;
		if (to_pay_before == null) {
			if (other.to_pay_before != null)
				return false;
		} else if (!to_pay_before.equals(other.to_pay_before))
			return false;
		if (type != other.type)
			return false;
		if (type_of_payment == null) {
			if (other.type_of_payment != null)
				return false;
		} else if (!type_of_payment.equals(other.type_of_payment))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", type=" + type + ", customer_name=" + customer_name + ", billing_adress="
				+ billing_adress + ", shipping_adress=" + shipping_adress + ", bill_date=" + bill_date
				+ ", type_of_payment=" + type_of_payment + ", to_pay_before=" + to_pay_before + ", deputy_document="
				+ deputy_document + ", payment_due=" + payment_due + ", amount=" + amount + "]";
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getBilling_adress() {
		return billing_adress;
	}
	public void setBilling_adress(String billing_adress) {
		this.billing_adress = billing_adress;
	}
	public String getShipping_adress() {
		return shipping_adress;
	}
	public void setShipping_adress(String shipping_adress) {
		this.shipping_adress = shipping_adress;
	}
	
	
	public String getType_of_payment() {
		return type_of_payment;
	}
	public void setType_of_payment(String type_of_payment) {
		this.type_of_payment = type_of_payment;
	}
	
	public TypeBill getType() {
		return type;
	}

	public void setType(TypeBill type) {
		this.type = type;
	}
	
	public String getDeputy_document() {
		return deputy_document;
	}
	public void setDeputy_document(String deputy_document) {
		this.deputy_document = deputy_document;
	}
	public int getPayment_due() {
		return payment_due;
	}
	public void setPayment_due(int payment_due) {
		this.payment_due = payment_due;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Bill() {
		super();
	}
	
	
	
	
	
	
	
	
    
}
