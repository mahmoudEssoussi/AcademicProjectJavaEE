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
@Table(name = "caisse")

public class Caisse implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "somme_caisse")
	private  long somme_caisse ;
	
	

	public java.util.Date getDate() {
		return date;
	}











	public void setDate(java.util.Date date) {
		this.date = date;
	}



	@Column(name = "date")
	private java.util.Date date ;
	
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "Caisse [id=" + id + ", somme_caisse=" + somme_caisse + ", date=" + date + "]";
	}

	

	



	



	



	public Caisse() {
		super();
	}











	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + (int) (somme_caisse ^ (somme_caisse >>> 32));
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
		Caisse other = (Caisse) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (somme_caisse != other.somme_caisse)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public long getSomme_caisse() {
		return somme_caisse;
	}



	public void setSomme_caisse(long somme_caisse) {
		this.somme_caisse = somme_caisse;
	}

	

	




}
