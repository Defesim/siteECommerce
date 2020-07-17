package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.siteECommerce.dao.CommandeDAOImpl;
import com.intiformation.siteECommerce.dao.ICommandeDAO;
import com.intiformation.siteECommerce.modele.Commande;
import com.intiformation.siteECommerce.modele.Commande;
import com.intiformation.siteECommerce.modele.Commande;



@ManagedBean (name = "commandeBean") 
@SessionScoped
public class CommandeBean implements Serializable {

	private List<Commande> listeCommandeBdd;
	private Commande Commande;
	private ICommandeDAO CommandeDAO;

	public CommandeBean() {

		CommandeDAO = new CommandeDAOImpl();

	}// end gestionCommandeBean

	public List<Commande> findallCommandeBdd() {
		listeCommandeBdd = CommandeDAO.getAll();
		setListeCommandeBdd(listeCommandeBdd);
		return listeCommandeBdd;
	}// end listeCommande
	
	/**
	 * initialiser le Commande
	 */
	public void initialiserCommande() {

		// instanciation d'un nouveau objet de type Commande
		Commande CommandeAdd = new Commande();

		// affectation du Commande � la variable o� � la prop du Commande
		setCommande(CommandeAdd);

	}// end initialiserCommande
	
public String PanierDansCommande() {
		
		//1 recup du context de JSF 
		FacesContext context = FacesContext.getCurrentInstance();
		
		//2 ajout nouveau commande
		boolean verifAjout = CommandeDAO.PanierDansCommande();
		
		if (verifAjout) {
			
			//ajout ok
			
			//=> envoie d'un message vers la vue 'accueil.xhtml'
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout commande ", "Ajout fait avec succ�s"));
			
			//redirection vers la page 'accueil.xhtml'
			return "commande.xhtml?faces-redirect=true";
			
		} else { //ajout not ok

			FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout commande rat� ", "Ajout pas fait avec succ�s");

			// envoie d'un message vers la vue 'ajouter-commande.xhtml'
			return "commande.xhtml?faces-redirect=true";
			
		}//end else
		
	}//end ajout

@PostConstruct
public void ViderCommandeBdd() {
	
	
	//3.1 recup du context de JSFc
	FacesContext contextJSF = FacesContext.getCurrentInstance();
	
	//3.2 suppression du commande
	if (CommandeDAO.ViderCommande()) {
		
		//suppresion ok
		
		//envoi d'un message vers la vue via le context de JSF
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"vider commande", 
				"- la suppresion a �t� faite correctement"));
		// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
		
	} else {

		//suppresion �chou�
		
		//=>
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
				"vider commande", 
				"- la suppresion a �chou� ta vie est un echec total :p"));
	}//end else
	
}// end ViderCommande

public void selectionnerCommande(ActionEvent event) {
	
	//1. recup du param�tre pass� au composant au click sur le lien "�diter"
	UIParameter uip = (UIParameter) event.getComponent().findComponent("IDCommandeSelection");
	
	//2 recup de la valeur
	int CommandeID = (int) uip.getValue();
	
	//3 recup du Commande � �diter via l'id dans la bdd
	Commande CommandeAEditer = CommandeDAO.getById(CommandeID);
	
	//4 affectation du Commande � �diter � la variable 'Commande ' du MB
	setCommande(CommandeAEditer);
	
	//5 redirection vers la page �dition 'editer-Commande .xhtml' (ref : les cl�s d'outcom 'editCommande ' du faces-config.xml)
	
	
	
	
	
}//end selectionner Commande 


/**
 * Permet de supprimer une Commande dans la bdd;
 * invoqu�e au click sur le lien "supprimer" de accueil.xhtml
 * au click, l'�v�nement encapsule toutes les infos concernant le composant
 * 
 */
public void supprimerCommande (ActionEvent event) {
	
	//1 recup du param pas� dans le composant au click sur le lien supprimer
	UIComponent composantParam = event.getComponent().findComponent("deleteID");
	
	UIParameter cp = (UIParameter) composantParam;
	
	//2. recup de la valeur du param�tre
	int CommandeId = (int) cp.getValue();
	
	//3 suppression du Commande dans la bdd via l'id
	
	//3.1 recup du context de JSFc
	FacesContext contextJSF = FacesContext.getCurrentInstance();
	
	//3.2 suppression du Commande
	if (CommandeDAO.delete(CommandeId)) {
		
		//suppresion ok
		
		//envoi d'un message vers la vue via le context de JSF
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"suppresion Commande", 
				"- la suppresion a �t� faite correctement"));
		// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
		
	} else {

		//suppresion �chou�
		
		//=>
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
				"suppresion Commande", 
				"- la suppresion a �chou� ta vie est un echec total :p"));
	}//end else
	
	
}//end supprimer
	
	//-----------------getter et setter---------------------------------------------
	
	
	public List<Commande> getListeCommandeBdd() {
		return listeCommandeBdd;
	}

	public void setListeCommandeBdd(List<Commande> listeCommandeBdd) {
		this.listeCommandeBdd = listeCommandeBdd;
	}

	public Commande getCommande() {
		return Commande;
	}

	public void setCommande(Commande commande) {
		Commande = commande;
	}
	
	
	
	
}//end class
