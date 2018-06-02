package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "RP_Note")

public class RP_Note implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idNote")
	private int idNote;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "idProjet")
	private int idProjet;
	
	@Column(name = "creationDate")
	private String creationDate;
	
	@Column(name = "noteTitle")
	private String noteTitle;
	
	@Column(name = "previewNote")
	private String previewNote;

	public RP_Note() {
		super();
	}

	public RP_Note(int idNote, String createdBy, int idProjet, String creationDate, String noteTitle, String previewNote) {
		super();
		this.idNote = idNote;
		this.createdBy = createdBy;
		this.idProjet = idProjet;
		this.creationDate = creationDate;
		this.noteTitle = noteTitle;
		this.previewNote = previewNote;
	}

	@Override
	public String toString() {
		return "RP_Note [idNote=" + idNote + ", createdBy=" + createdBy + ", idProjet=" + idProjet + ", creationDate="
				+ creationDate + ", noteTitle=" + noteTitle + ", previewNote=" + previewNote + "]";
	}

	public int getIdNote() {
		return idNote;
	}

	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}

	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getPreviewNote() {
		return previewNote;
	}

	public void setPreviewNote(String previewNote) {
		this.previewNote = previewNote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	

}
