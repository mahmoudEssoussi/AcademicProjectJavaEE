package tn.esprit.b4.esprit1718b4erp.entities;



import java.io.Serializable ;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")

public class User implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
    @Column(name = "cin")
	private String cin;
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
	private int phone_number;
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
	   @Lob
	    private byte[] picture;
	    @Column(nullable = false)
	
	@OneToMany( mappedBy="user" , cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.ALL})
	private List<Congé> congés;
	
	
	public String getMarital_status() {
		return marital_status;
	}


	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	@Column(name = "role")
	private String role;
	@Column(name = "number_h")
	private int number_h;
	@Column(name = "cost_h")
	private float cost_h;

	//@Column(name = "profil_photo")
	//private String profil_photo ;


	@Column(name = "talents")
	private String talents;
	@Column(name = "affected")
	private int affected;
	@Column(name = "affectedTo")
	private int affectedTo;
	
	




	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public int getId() {
		return id;
	}


	public List<Congé> getCongés() {
		return congés;
	}


	public void setCongés(List<Congé> congés) {
		this.congés = congés;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
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


	public int getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
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
	
	

 


	public String getTalents() {
		return talents;
	}


	public void setTalents(String talents) {
		this.talents = talents;
	}


	public int getAffected() {
		return affected;
	}


	public void setAffected(int affected) {
		this.affected = affected;
	}
	
	


	public int getAffectedTo() {
		return affectedTo;
	}


	public void setAffectedTo(int affectedTo) {
		this.affectedTo = affectedTo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public User(){}
	

	public User(int id, String cin, String firstname, String lastname, String login, String password, String email,
			int phone_number, String nationality, boolean dispo, String gender, String date_birth,
			String marital_status, byte[] picture, List<Congé> congés, String role, int number_h, float cost_h,
			String talents, int affected, int affectedTo) {
		super();
		this.id = id;
		this.cin = cin;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone_number = phone_number;
		this.nationality = nationality;
		this.dispo = dispo;
		this.gender = gender;
		this.date_birth = date_birth;
		this.marital_status = marital_status;
		this.picture = picture;
		this.congés = congés;
		this.role = role;
		this.number_h = number_h;
		this.cost_h = cost_h;
		this.talents = talents;
		this.affected = affected;
		this.affectedTo = affectedTo;
	}


	public User(String firstname, String lastname, String login, String password, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", cin=" + cin + ", firstname=" + firstname + ", lastname=" + lastname + ", login="
				+ login + ", password=" + password + ", email=" + email + ", phone_number=" + phone_number
				+ ", nationality=" + nationality + ", dispo=" + dispo + ", gender=" + gender + ", date_birth="
				+ date_birth + ", maried=" + marital_status + ", role=" + role + ", number_h=" + number_h + ", cost_h=" + cost_h
				+ "]";
	}
	public User(int id, String firstname, String lastname, String login, String password, String email,
			int phone_number, String nationality, boolean dispo, String gender, String date_birth, String marital_status,
			String role, int number_h, float cost_h) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone_number = phone_number;
		this.nationality = nationality;
		this.dispo = dispo;
		this.gender = gender;
		this.date_birth = date_birth;
		this.marital_status = marital_status;
		this.role = role;
		this.number_h = number_h;
		this.cost_h = cost_h;
	}


	public User(String firstname, String lastname, String login, String password, String role, String talents,
			int affected) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.role = role;
		this.talents = talents;
		this.affected = affected;
	}
	
	public User(String firstname, String lastname, String login, String password, String role, String talents,
			int affected,int phoneNumber,String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.role = role;
		this.talents = talents;
		this.affected = affected;
		this.phone_number=phoneNumber;
		this.email=email;
	}


	public User(int id2, String firstname2, String lastname2, String login2, String password2, String role2) {
		super();
		this.id=id2;
		this.firstname = firstname2;
		this.lastname = lastname2;
		this.login = login2;
		this.password = password2;
		this.role = role2;
	}


	
	


	




}
