package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contrat")
public class Contrat implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
private int id_contrat;

@OneToOne
private User user ;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}



}
