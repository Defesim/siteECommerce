package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.siteECommerce.dao.IPanierDAO;
import com.intiformation.siteECommerce.dao.PanierDAOImpl;
import com.intiformation.siteECommerce.modele.Panier;
import com.intiformation.siteECommerce.modele.Panier;
import com.intiformation.siteECommerce.modele.Panier;


@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {

	private List<Panier> listePanierBdd;
	private Panier Panier;
	private IPanierDAO PanierDAO;

	public GestionPanierBean() {

		PanierDAO = new PanierDAOImpl();

	}// end gestionPanierBean

	public List<Panier> findallPanierBdd() {
		listePanierBdd = PanierDAO.getAll();
		return listePanierBdd;
	}// end listePanier
	
	public void ViderPanierBdd() {
		
		
		//3.1 recup du context de JSFc
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//3.2 suppression du livre
		if (PanierDAO.viderPanier()) {
			
			//suppresion ok
			
			//envoi d'un message vers la vue via le context de JSF
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"suppresion produit", 
					"- la suppresion a �t� faite correctement"));
			// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
			
		} else {

			//suppresion �chou�
			
			//=>
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"suppresion produit", 
					"- la suppresion a �chou� ta vie est un echec total :p"));
		}//end else
		
	}// end listePanier
	
	/**
	 * initialiser le Panier
	 */
	public void initialiserPanier() {

		// instanciation d'un nouveau objet de type livre
		Panier PanierAdd = new Panier();

		// affectation du livre � la variable o� � la prop du livre
		setPanier(PanierAdd);

	}// end initialiserPanier

public String ajouterAuPanier(ActionEvent event) {
		
	//1 recup du param pas� dans le composant au click sur le lien supprimer
			UIComponent composantParam = event.getComponent().findComponent("ajoutPanierID");
			
			UIParameter cp = (UIParameter) composantParam;
			
			//2. recup de la valeur du param�tre
			int PanierPanierId = (int) cp.getValue();
			
			//3 suppression du livre dans la bdd via l'id
			
			//3.1 recup du context de JSFc
			FacesContext contextJSF = FacesContext.getCurrentInstance();
			
			//3.2 suppression du livre
			if (PanierDAO.ajouterAuPanier(PanierPanierId)) {
				
				//suppresion ok
				
				//envoi d'un message vers la vue via le context de JSF
				contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"suppresion Panier", 
						"- la suppresion a �t� faite correctement"));
				// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
				return "accueil.xhtml?faces-redirect=true";
				
			} else {

				//suppresion �chou�
				
				//=>
				contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
						"suppresion Panier", 
						"- la suppresion a �chou� ta vie est un echec total :p"));
				return "panier.xhtml?faces-redirect=true";
			}//end else
		
	}//end ajouter

/**
 * Permet de modifier un Panier dans la bdd;
 * invoqu�e au click sur le lien "modifier" de editer-Panier.xhtml
 * au click, l'�v�nement encapsule toutes les infos concernant le composant
 * 
 */
public void modifierPanier(ActionEvent event) {
	
	/**
	 * la prop 'livre' du MB encapsule les infos du livre � modifier dans
	 * la base de donn�e
	 */
	
	// 1. recup du context de JSF
	FacesContext contextJSF = FacesContext.getCurrentInstance();
	
	//2. modification du livre dans la bdd
	if (PanierDAO.update(Panier)) {
		
		//modif ok
		
		//-> message vers la vue 
		FacesMessage messageOk = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification", " - La modification a �t� faite avec succ�s");
		
		//--> envoie du message
		contextJSF.addMessage(null, messageOk);
		
		
	} else {

		//modif not ok
		//-> message vers la vue 
		FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la Modification", " - La modification n'a pas fonctionn�e");
		
		//--> envoie du message
		contextJSF.addMessage(null, messagenotOk);
		
		
		
	}//end if else
	
	// redirection vers la page accueil.xhtml ref : la cl� d'outcom : 'listPanier'

	// naviguer de edit-Panier � accueil
	
	
}//end modifier Panier

public void supprimerPanier(ActionEvent event) {
	
	//1 recup du param pas� dans le composant au click sur le lien supprimer
	UIComponent composantParam = event.getComponent().findComponent("deleteID");
	
	UIParameter cp = (UIParameter) composantParam;
	
	//2. recup de la valeur du param�tre
	int PanierId = (int) cp.getValue();
	
	//3 suppression du livre dans la bdd via l'id
	
	//3.1 recup du context de JSFc
	FacesContext contextJSF = FacesContext.getCurrentInstance();
	
	//3.2 suppression du livre
	if (PanierDAO.delete(PanierId)) {
		
		//suppresion ok
		
		//envoi d'un message vers la vue via le context de JSF
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"suppresion produit", 
				"- la suppresion a �t� faite correctement"));
		// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
		
	} else {

		//suppresion �chou�
		
		//=>
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
				"suppresion produit", 
				"- la suppresion a �chou� ta vie est un echec total :p"));
	}//end else
	
	
}//end supprimer
	
	
	
	//-------------------------getter et setter-------------------------------
	
	
	
	
	public Panier getPanier() {
		return Panier;
	}

	public void setPanier(Panier panier) {
		Panier = panier;
	}
	
	
}//end class