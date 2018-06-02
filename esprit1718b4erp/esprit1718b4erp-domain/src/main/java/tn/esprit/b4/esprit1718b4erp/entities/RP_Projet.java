package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RP_Projet")
public class RP_Projet implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProjet")
	private int idProjet;
	
	@Column(name = "identifiantProjet")
	private String identifiantProjet;
	
	@Column(name = "identifiantClient")
	private String identifiantClient;
	
	@Column(name = "nomProjet")
	private String nomProjet;
	
	@Column(name = "status")
	private String status;
	
	
	
	@Column(name = "projectManager")
	private String projectManager;
	

	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priority")
	private String priority;
	
	@Column(name = "plannedStartDate")
	private String plannedStartDate;
	
	@Column(name = "plannedEndDate")
	private String plannedEndDate;
	
	@Column(name = "actualEndDate")
	private String actualEndDate;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "creationDate")
	private String creationDate;
	
	@Column(name = "createdBy")
	private int createdBy;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "daysBetween")
	private float daysBetween;
	
	
	
	public RP_Projet() {
		super();
	}


	


	public int getIdProjet() {
		return idProjet;
	}


	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}


	public String getIdentifiantProjet() {
		return identifiantProjet;
	}


	public void setIdentifiantProjet(String identifiantProjet) {
		this.identifiantProjet = identifiantProjet;
	}


	public String getIdentifiantClient() {
		return identifiantClient;
	}


	public void setIdentifiantClient(String identifiantClient) {
		this.identifiantClient = identifiantClient;
	}


	public String getNomProjet() {
		return nomProjet;
	}


	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getPlannedStartDate() {
		return plannedStartDate;
	}


	public void setPlannedStartDate(String plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}


	public String getPlannedEndDate() {
		return plannedEndDate;
	}


	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}


	public String getActualEndDate() {
		return actualEndDate;
	}


	public void setActualEndDate(String actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	


	public float getDaysBetween() {
		return daysBetween;
	}


	public void setDaysBetween(float daysBetween) {
		this.daysBetween = daysBetween;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public RP_Projet(String identifiantProjet, String identifiantClient, String nomProjet, String description,
			String plannedStartDate, String plannedEndDate, String type, int createdBy, float price) {
		super();
		this.identifiantProjet = identifiantProjet;
		this.identifiantClient = identifiantClient;
		this.nomProjet = nomProjet;
		this.description = description;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.type = type;
		this.createdBy = createdBy;
		this.price = price;
	}


	public RP_Projet(String identifiantProjet, String identifiantClient, String nomProjet, String status,
			String projectManager, String description, String priority, String plannedStartDate, String plannedEndDate,
			String actualEndDate, String type, String creationDate, int createdBy, float price) {
		super();
		this.identifiantProjet = identifiantProjet;
		this.identifiantClient = identifiantClient;
		this.nomProjet = nomProjet;
		this.status = status;
		this.projectManager = projectManager;
		this.description = description;
		this.priority = priority;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
		this.actualEndDate = actualEndDate;
		this.type = type;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.price = price;
	}





	@Override
	public String toString() {
		return "RP_Projet [idProjet=" + idProjet + ", identifiantProjet=" + identifiantProjet + ", identifiantClient="
				+ identifiantClient + ", nomProjet=" + nomProjet + ", status=" + status + ", projectManager="
				+ projectManager + ", description=" + description + ", plannedStartDate=" + plannedStartDate
				+ ", plannedEndDate=" + plannedEndDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
