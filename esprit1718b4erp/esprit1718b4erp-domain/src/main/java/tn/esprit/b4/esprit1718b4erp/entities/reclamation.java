package tn.esprit.b4.esprit1718b4erp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class reclamation {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idr;
@ManyToOne
@JoinColumn(name="idp",referencedColumnName="idprojet")
private RP_Projet p;
@ManyToOne
@JoinColumn(name="idc",referencedColumnName="id_client")
private Client c;
@Column(name="typerec")
private String typerec;

public String getTyperec() {
	return typerec;
}
public void setTyperec(String typerec) {
	this.typerec = typerec;
}
public reclamation() {
	super();
	// TODO Auto-generated constructor stub
}
public RP_Projet getP() {
	return p;
}
public void setP(RP_Projet p) {
	this.p = p;
}
public Client getC() {
	return c;
}
public void setC(Client c) {
	this.c = c;
}


}
