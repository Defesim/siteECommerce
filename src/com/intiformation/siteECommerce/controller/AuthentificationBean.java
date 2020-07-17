package com.intiformation.siteECommerce.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.siteECommerce.dao.IUtilisateurDAO;
import com.intiformation.siteECommerce.dao.UtilisateurDAOImpl;



/**
 * managebean pour gestion authentification de l'user
 * @author user
 *
 */
@ManagedBean(name="authenticationBean")
@SessionScoped

public class AuthentificationBean implements Serializable{

    /*----------------prop--------------------------------*/
    private String Useridentifiant;
    private String UserMdp;

    //declaration de la dao
    private IUtilisateurDAO utilisateurDAO;



    /*----------------ctor--------------------------------*/
 /**
  * ctor vide
  */
    public AuthentificationBean() {
        utilisateurDAO = new UtilisateurDAOImpl();

}//end constructor

/*----------------methode--------------------------------*/

    public String deconnecterUtilisateur() {

        //1 recup du context de JSF
        FacesContext contextJSF = FacesContext.getCurrentInstance();

        //2 recup de la session http de l'user
        HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);

        // 3 deconnexion
        session.invalidate();

        // message de deconnexion vers la vue
        FacesMessage messageDeconnexion = new FacesMessage(FacesMessage.SEVERITY_INFO, "Déconnexion", "- vous êtes maintenant deconnecté");
        contextJSF.addMessage(null, messageDeconnexion);

        // 4 redirection vers la page du formulaire 'authentification.xhtml'
        return "Deconnection";
    }//end 	deconnecterUser
    
    public String connecterUtilisateur() {
		// déclaration du context JSF (l'objet FacesContext)
		/**
		 * FacesContext encapsule l'arbtre
		 */
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		
		//1 verif si l'user existe dans la bdd
		if (utilisateurDAO.isUtilisateurExists(Useridentifiant, UserMdp)) {
			
			//----------l'utilisateur existe
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);
			
			session.setAttribute("user_login", "userIdentifiant");
			
			// -> naviagtion vers la page acceuil.xhtml
			return "AccesAutorise";
			
			
			// -> création de la session 
			
		} else {
			
			// l'utilisateur existe pas 

			/**
			 * > envoie d'un message vers la vue via l'objet FacesMessage et l'objet FacesContexte
			 * 
			 */
			//defition du message à envoyer avec un objet de type FacesMessages
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion", "Identifiant ou Mot de passe invalide");
			
			// -> envoi du message vers la vue via le context de JSF (l'objet 'FacesContext') et sa méthode addMessage
			/**
			 * addmessage(arg1 , FacesMessage message)
			 * arg 1 = l'id du composant pour lequel le message est destiné
			 * ou peut prendre valeur null : le message st globale
			 * message : message à envoyer
			 */
			contextJSF.addMessage(null, message);
			
			//-> naviagtion vers la page formulaire 
			return "AccesNonAutorise";
			
			
		}//end else
		
	}//end connecterUser
	
	
	/*----------------getter/setter--------------------------------*/


	public String getUseridentifiant() {
		return Useridentifiant;
	}

	public void setUseridentifiant(String useridentifiant) {
		Useridentifiant = useridentifiant;
	}

	public String getUserMdp() {
		return UserMdp;
	}

	public void setUserMdp(String userMdp) {
		UserMdp = userMdp;
	}
	
}//end class
