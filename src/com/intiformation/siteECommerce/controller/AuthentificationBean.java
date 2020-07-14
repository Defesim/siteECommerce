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
        return "accueil.xhtml";
    }//end 	
	
}//end class
