package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javafx.scene.control.ComboBox;

@Entity
@Table(name = "RP_Tache")
public class RP_Tache implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTache")
	private int idTache;
	
	@Column(name = "idProjet")
	private int idProjet;
	
	@Column(name = "assegnedTo")
	private int assegnedTo;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "nomTache")
	private String nomTache;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "status")
	private String status;
	
	/*@Column(name = "priority")
	private String priority;*/
	
	@Column(name = "assignedDate")
	private String assignedDate;
	
	@Column(name = "DeadLine")
	private String DeadLine;
	
	/*@Column(name = "EstimatedHr")
	private int EstimatedHr;*/
	
	@Column(name = "StartDate")
	private String StartDate;
	
	@Column(name = "EndDate")
	private String EndDate;
	
	@Column(name = "ActualHr")
	private String ActualHr;
	
	@Column(name = "daysBetween")
	private float daysBetween;
        
        
    //   private ComboBox comboboxStatus;
        
      //  private CheckBox finished;

	public int getIdTache() {
		return idTache;
	}

	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}

	public int getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	public int getAssegnedTo() {
		return assegnedTo;
	}
	
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setAssegnedTo(int assegnedTo) {
		this.assegnedTo = assegnedTo;
	}

	public String getNomTache() {
		return nomTache;
	}

	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getDeadLine() {
		return DeadLine;
	}

	public void setDeadLine(String deadLine) {
		DeadLine = deadLine;
	}



	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getActualHr() {
		return ActualHr;
	}

	public void setActualHr(String actualHr) {
		ActualHr = actualHr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public float getDaysBetween() {
		return daysBetween;
	}

	public void setDaysBetween(float daysBetween) {
		this.daysBetween = daysBetween;
	}

	@Override
	public String toString() {
		return "RP_Tache [idTache=" + idTache + ", idProjet=" + idProjet + ", assegnedTo=" + assegnedTo + ", fullName="
				+ fullName + ", nomTache=" + nomTache + ", type=" + type + ", status=" + status + ", assignedDate="
				+ assignedDate + ", DeadLine=" + DeadLine + ", StartDate=" + StartDate + ", EndDate=" + EndDate
				+ ", ActualHr=" + ActualHr + ", daysBetween=" + daysBetween + "]";
	}
	
	

 /*   public ComboBox getComboboxStatus() {
        return comboboxStatus;
    }

    public void setComboboxStatus(ComboBox comboboxStatus) {
        this.comboboxStatus = comboboxStatus;
    }*/

   /* public CheckBox getFinished() {
        return finished;
    }

    public void setFinished(CheckBox finished) {
        this.finished = finished;
    }*/
	
    
    
        
        
	
	
	
	
	

}
