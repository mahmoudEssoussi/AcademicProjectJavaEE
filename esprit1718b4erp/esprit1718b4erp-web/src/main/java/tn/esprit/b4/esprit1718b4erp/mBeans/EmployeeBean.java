package tn.esprit.b4.esprit1718b4erp.mBeans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b4.esprit1718b4erp.entities.Congé;
import tn.esprit.b4.esprit1718b4erp.entities.EtatCongé;
import tn.esprit.b4.esprit1718b4erp.entities.TypeCongé;
import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.CongéServiceLocal;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceLocal;

@ManagedBean(name = "EmployeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

	public EmployeeBean() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private List<User> Listemployee = new ArrayList<User>();
	
	private List<Congé> Listcongé = new ArrayList<Congé>();

	private static User transUser;

	public User getTransUser() {
		return transUser;
	}



	public static void setTransUser(User transUser) {
		EmployeeBean.transUser = transUser;
	}

	// private String cin;
	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private Date dateDebut;
	private Date dateFin;
	private TypeCongé type;
	private String comment;
	// private String email;
	// private int phone_number;
	// private String nationality;
	// private boolean dispo;
	// private String gender;
	// private String date_birth;
	// private String marital_status;
	private String role;
	private List<User> users;
	private User user1 = new User();
	public User getUser1() {
		return user1;
	}



	public void setUser1(User user1) {
		this.user1 = user1;
	}

	private int id;
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private CongéServiceLocal congéServiceLocal;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		users = userService.DisplayAllUser();
		return users;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserServiceLocal getUserService() {
		return userService;
	}

	public void setUserService(UserServiceLocal userService) {
		this.userService = userService;
	}

	@EJB
	UserServiceLocal userService;

	@PostConstruct
	public void init() {
		Listemployee = userService.findAll();
		user = new User();
		System.out.println("init work");
		Listcongé = congéServiceLocal.findAll();

	}

	public List<Congé> getListcongé() {
		return Listcongé;
	}



	public void setListcongé(List<Congé> listcongé) {
		Listcongé = listcongé;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListemployee() {
		return Listemployee;
	}

	public void setListemployee(List<User> listemployee) {
		Listemployee = listemployee;
	}

	public void addUser() {
		User u = new User(firstname, lastname, login, password, role);
		userService.save(u);
	}
	public void addConge() {
		System.out.println("fi addcong");
//		Congé u = new Congé( dateDebut,dateFin, type, comment);
		Congé c = new Congé();
		c.setDateDebut(dateDebut);
		c.setDateFin(dateFin);
		c.setComment(comment);
		c.setType(type);
		c.setUser(userServiceLocal.getUserById(Identity.idUserConnecte));
		congéServiceLocal.save(c);
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



	public TypeCongé getType() {
		return type;
	}



	public void setType(TypeCongé type) {
		this.type = type;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public void delete(Integer user_id) {
		userService.removeUser(user_id);
	}

	public void update(Integer user_id) {
		User user = userService.getUserById(user_id);

		this.setFirstname(user.getFirstname());
		this.setLastname(user.getLastname());
		this.setLogin(user.getLogin());
		this.setPassword(user.getPassword());
		this.setRole(user.getRole());
		this.setId(user.getId());
	}

	private StreamedContent content;

	public void miseajour() {
		System.out.println("toptop");
		userService.update(new User(id, firstname, lastname, login, password, role));
	}

	public StreamedContent getContent() {// (Integer id) {
		System.out.println("**********************************>apl getImageF1");

		user = userServiceLocal.getUserById(transUser.getId());
		byte[] img = new byte[Integer.MAX_VALUE];
		img = userServiceLocal.loadImage(transUser.getId());
		System.out.println("bytefiras" + img);
		if (img != null) {
			InputStream is = new ByteArrayInputStream(img);
			System.out.println("Byte :" + user.getPicture());
			content = new DefaultStreamedContent(is, "");
			System.out.println("ddd ------------------------------- " + content);
			return content;
		}

		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}

	public String MoreDetails(User u) {

		setTransUser(u);
		setUser1(transUser);
		System.out.println("mpre majdi");
		return "/rh/main?faces-redirect=true";
	}

	public User MoreDetailsUser(Integer id) {
		this.user = userService.getUserById(id);
		System.out.println("get user work ");
		//

		return user;
	}

	public StreamedContent getImageF() {
        System.out.println("**********************************>apl getImageF");

		user = userServiceLocal.getUserById(transUser.getId());
		byte[] img =userServiceLocal.loadImage(transUser.getId());
	    if (img != null) {
	        InputStream is = new ByteArrayInputStream(img);
	        System.out.println("Byte :"+user.getPicture());
	        content = new DefaultStreamedContent(is, "");
	        System.out.println("ddd ------------------------------- " + content);
	        return content;
	    }

	    return content;
	}
	public StreamedContent getImageF1() {
        System.out.println("**********************************>apl getImageF");

		user = userServiceLocal.getUserById(transUser.getId());
		byte[] img =userServiceLocal.loadImage(transUser.getId());
	    if (img != null) {
	        InputStream is = new ByteArrayInputStream(img);
	        System.out.println("Byte :"+user.getPicture());
	        content = new DefaultStreamedContent(is, "");
	        System.out.println("ddd ------------------------------- " + content);
	        return content;
	    }

	    return content;
	}
	public long getNbrDays(int id){
		Congé c =congéServiceLocal.getCongéById(id);
		return userService.getDateDiff(c.getDateDebut(), c.getDateFin(), TimeUnit.DAYS);
	}
	public void accept(int id){
		Congé c= congéServiceLocal.getCongéById(id);
		c.setEtat(EtatCongé.accepted);
		congéServiceLocal.update(c);
		
	}
	public void refuse(int id){
		Congé c= congéServiceLocal.getCongéById(id);
		c.setEtat(EtatCongé.refused);
		congéServiceLocal.update(c);
	}
	
}
