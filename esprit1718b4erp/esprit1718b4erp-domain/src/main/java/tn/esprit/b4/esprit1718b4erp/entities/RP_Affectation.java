package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RP_Affectation")
public class RP_Affectation implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAffectation")
	private int idAffectation;
	
	@Column(name = "idUser")
	private int idUser;
	
	@Column(name = "idProjet")
	private int idProjet;
	
	@Column(name = "jeton")
	private int jeton;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "taskName")
	private String taskName;
	
	@Column(name = "taskStatus")
	private String taskStatus;

	public int getIdAffectation() {
		return idAffectation;
	}

	public void setIdAffectation(int idAffectation) {
		this.idAffectation = idAffectation;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	public int getJeton() {
		return jeton;
	}

	public void setJeton(int jeton) {
		this.jeton = jeton;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RP_Affectation() {
		super();
	}

	@Override
	public String toString() {
		return "RP_Affectation [idAffectation=" + idAffectation + ", idUser=" + idUser + ", idProjet=" + idProjet
				+ ", jeton=" + jeton + ", fullName=" + fullName + ", taskName=" + taskName + ", taskStatus="
				+ taskStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + idAffectation;
		result = prime * result + idProjet;
		result = prime * result + idUser;
		result = prime * result + jeton;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((taskStatus == null) ? 0 : taskStatus.hashCode());
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
		RP_Affectation other = (RP_Affectation) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (idAffectation != other.idAffectation)
			return false;
		if (idProjet != other.idProjet)
			return false;
		if (idUser != other.idUser)
			return false;
		if (jeton != other.jeton)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskStatus == null) {
			if (other.taskStatus != null)
				return false;
		} else if (!taskStatus.equals(other.taskStatus))
			return false;
		return true;
	}



	
	
	
	
	
}
