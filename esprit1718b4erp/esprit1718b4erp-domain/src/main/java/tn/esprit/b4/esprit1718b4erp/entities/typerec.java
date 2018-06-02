package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class typerec implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idtr;
@Column(name="typerec")
private String typerec;
public String getTyperec() {
	return typerec;
}
public void setTyperec(String typerec) {
	this.typerec = typerec;
}
@Override
public String toString() {
	return "typerec [idtr=" + idtr + ", typerec=" + typerec + "]";
}
public typerec() {
	super();
}

}
