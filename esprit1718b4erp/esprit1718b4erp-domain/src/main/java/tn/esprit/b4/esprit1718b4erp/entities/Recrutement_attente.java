package tn.esprit.b4.esprit1718b4erp.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Recrutement_attente")
public class Recrutement_attente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2038728143675787045L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
    @Column(name = "cin")
	private int cin;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "phone_number")
	private String phone_number;
	@Column(name = "nationality")
	private String nationality;
	@Column(name = "dispo")
	private boolean dispo;
	@Column(name = "gender")
	private String gender;
	@Column(name = "date_birth")
	private String date_birth;
	@Column(name = "marital_status")
	private String marital_status;
	@Column(name = "role")
	private String role;
	@Column(name = "number_h")
	private int number_h;
	@Column(name = "cost_h")
	private float cost_h;
	@Column(name = "comment")
	private String comment;
	   @Lob
	    private byte[] picture;
	    @Column(nullable = false)
	public String getComment() {
		return comment;
	}




	public byte[] getPicture() {
			return picture;
		}




		public void setPicture(byte[] picture) {
			this.picture = picture;
		}




	public void setComment(String comment) {
		this.comment = comment;
	}




	public int getId() {
		return id;
	}
	
	
	




	public Recrutement_attente() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Recrutement_attente( int cin, String firstname, String lastname,String email, String phone_number,
			 int number_h, float cost_h , byte[] picture) {
		super();
		this.cin = cin;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone_number = phone_number;
		this.number_h = number_h;
		this.cost_h = cost_h;
		this.picture = picture;

		
	}




	public void setId(int id) {
		this.id = id;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String string) {
		this.phone_number = string;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public boolean isDispo() {
		return dispo;
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate_birth() {
		return date_birth;
	}
	public void setDate_birth(String date_birth) {
		this.date_birth = date_birth;
	}






	@Override
	public String toString() {
		return "Recrutement_attente [id=" + id + ", cin=" + cin + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", login=" + login + ", password=" + password + ", email=" + email + ", phone_number=" + phone_number
				+ ", nationality=" + nationality + ", dispo=" + dispo + ", gender=" + gender + ", date_birth="
				+ date_birth + ", marital_status=" + marital_status + ", role=" + role + ", number_h=" + number_h
				+ ", cost_h=" + cost_h + ", comment=" + comment + ", picture=" + Arrays.toString(picture) + "]";
	}




	public String getMarital_status() {
		return marital_status;
	}




	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}




	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getNumber_h() {
		return number_h;
	}
	public void setNumber_h(int number_h) {
		this.number_h = number_h;
	}
	public float getCost_h() {
		return cost_h;
	}
	public void setCost_h(float cost_h) {
		this.cost_h = cost_h;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
