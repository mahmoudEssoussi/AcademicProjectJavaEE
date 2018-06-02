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
@Table(name = "Congé")
public class Congé implements Serializable {

	public Congé() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Congé(Date dateDebut2, Date dateFin2, TypeCongé type2, String comment2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2038728143675787045L;
	/**
	 * 
	 */

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_congé")
	private int id_congé;
	public Congé(TypeCongé type, Date dateDebut, Date dateFin, String comment) {
		super();
		this.type = type;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.comment = comment;
		;
	}

	@Enumerated(EnumType.STRING)
	private TypeCongé type ;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	@Enumerated(EnumType.STRING)
	private EtatCongé etat ;
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setType(TypeCongé type) {
		this.type = type;
	}

	private String comment;
	

@ManyToOne
	private User user ;







	public int getId_congé() {
	return id_congé;
}

public void setId_congé(int id_congé) {
	this.id_congé = id_congé;
}

public TypeCongé getType() {
	return type;
}

public void setTypeCongé(TypeCongé type) {
	this.type = type;
}

public Date getDateDebut() {
	return dateDebut;
}

public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
}

public Date getDateFin() {
	return dateFin;
}

public void setDateFin(Date dateFin) {
	this.dateFin = dateFin;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

	public EtatCongé getEtat() {
	return etat;
}

public void setEtat(EtatCongé etat) {
	this.etat = etat;
}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String setEtatString(EtatCongé t){
		if(t==EtatCongé.accepted){
		return "accepted";
		}
		else return "refused";
		
	}


}
