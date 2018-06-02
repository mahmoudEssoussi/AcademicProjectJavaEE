package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;;
@Entity
@Table(name = "exel")
public class Cellule_excel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_emp")
	private String id_emp;
	@Column(name = "nbr_h")
	private String nbr_h;
	
	
	
	public String getId_emp() {
		return id_emp;
		
	}
	public String getNbr_h() {
		return nbr_h;
	}
	public void setNbr_h(String nbr_h) {
		this.nbr_h = nbr_h;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}
	@Override
	public String toString() {
		return "Cellule_excel [id_emp=" + id_emp + ", nbr_h=" + nbr_h + "]";
	}
	
}
