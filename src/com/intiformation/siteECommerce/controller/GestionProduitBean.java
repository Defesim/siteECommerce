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


import com.intiformation.siteECommerce.dao.IProduitDAO;
import com.intiformation.siteECommerce.dao.ProduitDAOImpl;
import com.intiformation.siteECommerce.modele.Produit;

@ManagedBean(name = "produitBean")
@SessionScoped
public class GestionProduitBean implements Serializable {

	private List<Produit> listeProduitBdd;
	private Produit produit;
	private IProduitDAO produitDAO;

	public GestionProduitBean() {

		produitDAO = new ProduitDAOImpl();

	}// end gestionProduitBean

	public List<Produit> findallProduitBdd() {
		listeProduitBdd = produitDAO.getAll();
		return listeProduitBdd;
	}// end listeLivre

	/**
	 * initialiser le produit
	 */
	public void initialiserProduit() {

		// instanciation d'un nouveau objet de type livre
		Produit produitAdd = new Produit();

		// affectation du livre � la variable o� � la prop du livre
		setProduit(produitAdd);

	}// end initialiserProduit
	
	/**
	 * ajouter Produit
	 */
	public String ajouterProduit() {
		
		//1 recup du context de JSF 
				FacesContext context = FacesContext.getCurrentInstance();
				
				//2 ajout nouveau livre
				boolean verifAjout = produitDAO.add(produit);
				
				if (verifAjout) {
					
					//ajout ok
					
					//=> envoie d'un message vers la vue 'accueil.xhtml'
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout produit ", "Ajout fait avec succ�s"));
					
					//redirection vers la page 'accueil.xhtml'
					return "accueil.xhtml?faces-redirect=true";
					
				} else { //ajout not ok

					FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout produit rat� ", "Ajout pas fait avec succ�s");

					// envoie d'un message vers la vue 'ajouter-produit.xhtml'
					return "ajouter-produit.xhtml?faces-redirect=false";
					
				}//end else
		
	}//end ajouter
	
	/**
	 * Permet de modifier un produit dans la bdd;
	 * invoqu�e au click sur le lien "modifier" de editer-produit.xhtml
	 * au click, l'�v�nement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void modifierProduit(ActionEvent event) {
		
		/**
		 * la prop 'livre' du MB encapsule les infos du livre � modifier dans
		 * la base de donn�e
		 */
		
		// 1. recup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. modification du livre dans la bdd
		if (produitDAO.update(produit)) {
			
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
		
		// redirection vers la page accueil.xhtml ref : la cl� d'outcom : 'listproduit'

		// naviguer de edit-produit � accueil
		
		
	}//end modifier produit
	
	/**
	 * Permet de editer un livre dans la bdd;
	 * invoqu�e au click sur le lien "editer" de accueil.xhtml
	 * au click, l'�v�nement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void selectionnerProduit(ActionEvent event) {
		
		//1. recup du param�tre pass� au composant au click sur le lien "�diter"
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2 recup de la valeur
		int produitID = (int) uip.getValue();
		
		//3 recup du livre � �diter via l'id dans la bdd
		Produit produitAEditer = produitDAO.getById(produitID);
		
		//4 affectation du livre � �diter � la variable 'livre' du MB
		setProduit(produitAEditer);
		
		//5 redirection vers la page �dition 'editer-livre.xhtml' (ref : les cl�s d'outcom 'editbook' du faces-config.xml)
		
		
		
		
		
	}//end selectionner produit
	
	
	/**
	 * Permet de supprimer un livre dans la bdd;
	 * invoqu�e au click sur le lien "supprimer" de accueil.xhtml
	 * au click, l'�v�nement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void supprimerProduit(ActionEvent event) {
		
		//1 recup du param pas� dans le composant au click sur le lien supprimer
		UIComponent composantParam = event.getComponent().findComponent("deleteID");
		
		UIParameter cp = (UIParameter) composantParam;
		
		//2. recup de la valeur du param�tre
		int produitId = (int) cp.getValue();
		
		//3 suppression du livre dans la bdd via l'id
		
		//3.1 recup du context de JSFc
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//3.2 suppression du livre
		if (produitDAO.delete(produitId)) {
			
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
	

	// --------------------------getter/setter------------------------------------------------

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}// end class
