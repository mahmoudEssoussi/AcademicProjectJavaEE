package tn.esprit.b4.esprit1718b4erp.mBeans;


import java.io.ByteArrayInputStream;

import java.io.InputStream;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tn.esprit.b4.esprit1718b4erp.entities.User;
import tn.esprit.b4.esprit1718b4erp.services.CongéServiceLocal;
import tn.esprit.b4.esprit1718b4erp.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class Identity {
	   static int idUserConnecte;

	public static int getIdUserConnecte() {
		return idUserConnecte;
	}

	public static void setIdUserConnecte(int idUserConnecte) {
		Identity.idUserConnecte = idUserConnecte;
	}



	private boolean isLogged = false;
	private User user = new User();
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	CongéServiceLocal congéServiceLocal;

	public String logout() {
		isLogged = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/lo?faces-redirect=true";
	}

	public String doLogin() {
		String navigateTo = "";
		User userLoggedIn = userServiceLocal.login(user.getLogin(), user.getPassword());
	    String role= userServiceLocal.getRoleByLoginAndPass(user.getLogin() ,user.getPassword());
	    idUserConnecte=userServiceLocal.getIdUserByLoginAndPass(user.getLogin(), user.getPassword());
		System.out.println("con "+idUserConnecte);

		if (userLoggedIn != null && role.equals("engineer")) {
			isLogged = true;
			user = userLoggedIn;
			System.out.println("engineer");
			//navigateTo = "/home?faces-redirect=true";
			navigateTo = "/rh/prof_eng?faces-redirect=true";

		}
		if (userLoggedIn != null && role.equals("crm")) {
			isLogged = true;
			user = userLoggedIn;
			System.out.println("crm malik");
			//navigateTo = "/home?faces-redirect=true";
			navigateTo = "/CRM?faces-redirect=true";

		}
		if (userLoggedIn != null && role.equals("humain ressource")) {
			isLogged = true;
			user = userLoggedIn;
			System.out.println("humain ressource");
			//navigateTo = "/home?faces-redirect=true";
			addMessage("info", "connewion reusite");
			navigateTo = "/rh/RH?faces-redirect=true";

		}
		if (userLoggedIn != null && role.equals("finance ressource")) {
			isLogged = true;
			user = userLoggedIn;
			//navigateTo = "/home?faces-redirect=true";
			System.out.println("financial ressource");

			navigateTo = "/RF?faces-redirect=true";

		}
		if (userLoggedIn != null && role.equals("responsable project")) {
			isLogged = true;
			user = userLoggedIn;
			System.out.println("res_projet");

			//navigateTo = "/home?faces-redirect=true";
			navigateTo = "/RES_PRO?faces-redirect=true";

		}
		if (userLoggedIn != null && role.equals("rh_responsable")) {
			isLogged = true;
			user = userLoggedIn;
			System.out.println("resp rh");

			//navigateTo = "/home?faces-redirect=true";
			navigateTo = "/rh/RES_RH?faces-redirect=true";

		}				
		
		
		
		
		
		
		
		
		
		
		
//		else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//					"Veuillez insérer un login et un mot de passe valide", ""));
//			return "/login?faces-redirect=true";
//
//		}
		return navigateTo;

	}
	
	
	
	
	private StreamedContent content; // getter and setter

	public StreamedContent getImageF() {
        System.out.println("**********************************>apl getImageF");

		user = userServiceLocal.getUserById(idUserConnecte);
		byte[] img =userServiceLocal.loadImage(idUserConnecte);
	    if (img != null) {
	        InputStream is = new ByteArrayInputStream(img);
	        System.out.println("Byte :"+user.getPicture());
	        content = new DefaultStreamedContent(is, "");
	        System.out.println("ddd ------------------------------- " + content);
	        return content;
	    }

	    return content;
	}	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	@Override
	public String toString() {
		return "Identity [isLogged=" + isLogged + ", user=" + user + ", userServiceLocal=" + userServiceLocal
				+ ",]";
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public UserServiceLocal getUserServiceLocal() {
		return userServiceLocal;
	}

	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}
	public StreamedContent getImageF1(Integer id) {
        System.out.println("**********************************>apl getImageF1");

		user = userServiceLocal.getUserById(id);
		byte[] img = new byte[Integer.MAX_VALUE];
		 img =userServiceLocal.loadImage(id);
		  System.out.println("bytefiras"+img);
        if (img != null) {
	        InputStream is = new ByteArrayInputStream(img);
	        System.out.println("Byte :"+user.getPicture());
	        content = new DefaultStreamedContent(is, "");
	        System.out.println("ddd ------------------------------- " + content);
	        return content;
	    }

	    return content;
	}

	private void addMessage(String summary , String detail){
		FacesMessage msg =new FacesMessage(FacesMessage.SEVERITY_INFO , summary , detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void affectCongétoUser( )
	{
		//congéServiceLocal.assignCongéToUser(idUserConnecte, idCongé);
	}
}
