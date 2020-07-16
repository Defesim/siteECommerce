package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
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
	private String motCle;
	
	private IProduitDAO produitDAO;

	public GestionProduitBean() {

		produitDAO = new ProduitDAOImpl();

	}// end gestionProduitBean

	public List<Produit> findallProduitBdd() {
		listeProduitBdd = produitDAO.getAll();
		setListeProduitBdd(listeProduitBdd);
		return listeProduitBdd;
	}// end listeProduit
	
	public List<Produit> findProduitByCategorieBdd(ActionEvent event) {
		//1. recup du paramètre passé au composant au click sur la catégorie choisie
		UIParameter uip = (UIParameter) event.getComponent().findComponent("nomCategorie");
				
		//2 recup de la valeur
		String NomCategorie = (String) uip.getValue();
		listeProduitBdd = produitDAO.getByCategorie(NomCategorie);
		setListeProduitBdd(listeProduitBdd);
		return listeProduitBdd;
	}// end listeProduit
	
	public List<Produit> findProduitByMotCle(ActionEvent event) {
		listeProduitBdd = produitDAO.getByRecherche(motCle);
		setListeProduitBdd(listeProduitBdd);
		return listeProduitBdd;
	}// end listeProduit

	/**
	 * initialiser le produit
	 */
	public void initialiserProduit() {

		// instanciation d'un nouveau objet de type produit
		Produit produitAdd = new Produit();

		// affectation du produit à la variable où à la prop du produit
		setProduit(produitAdd);

	}// end initialiserProduit
	
	/**
	 * ajouter Produit
	 */
	public String ajouterProduit() {
		
		//1 recup du context de JSF 
				FacesContext context = FacesContext.getCurrentInstance();
				
				//2 ajout nouveau produit
				boolean verifAjout = produitDAO.add(produit);
				
				if (verifAjout) {
					
					//ajout ok
					
					//=> envoie d'un message vers la vue 'accueil.xhtml'
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout produit ", "Ajout fait avec succés"));
					
					//redirection vers la page 'accueil.xhtml'
					return "accueil.xhtml?faces-redirect=true";
					
				} else { //ajout not ok

					FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout produit raté ", "Ajout pas fait avec succés");

					// envoie d'un message vers la vue 'ajouter-produit.xhtml'
					return "ajouter-produit.xhtml?faces-redirect=false";
					
				}//end else
		
	}//end ajouter
	
	/**
	 * Permet de modifier un produit dans la bdd;
	 * invoquée au click sur le lien "modifier" de editer-produit.xhtml
	 * au click, l'évènement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void modifierProduit(ActionEvent event) {
		
		/**
		 * la prop 'produit' du MB encapsule les infos du produit à modifier dans
		 * la base de donnée
		 */
		
		// 1. recup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. modification du produit dans la bdd
		if (produitDAO.update(produit)) {
			
			//modif ok
			
			//-> message vers la vue 
			FacesMessage messageOk = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification", " - La modification a été faite avec succés");
			
			//--> envoie du message
			contextJSF.addMessage(null, messageOk);
			
			
		} else {

			//modif not ok
			//-> message vers la vue 
			FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la Modification", " - La modification n'a pas fonctionnée");
			
			//--> envoie du message
			contextJSF.addMessage(null, messagenotOk);
			
			
			
		}//end if else
		
		// redirection vers la page accueil.xhtml ref : la clé d'outcom : 'listproduit'

		// naviguer de edit-produit à accueil
		
		
	}//end modifier produit
	
	/**
	 * Permet de editer un produit dans la bdd;
	 * invoquée au click sur le lien "editer" de accueil.xhtml
	 * au click, l'évènement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void selectionnerProduit(ActionEvent event) {
		
		//1. recup du paramètre passé au composant au click sur le lien "éditer"
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2 recup de la valeur
		int produitID = (int) uip.getValue();
		
		//3 recup du produit à éditer via l'id dans la bdd
		Produit produitAEditer = produitDAO.getById(produitID);
		
		//4 affectation du produit à éditer à la variable 'produit' du MB
		setProduit(produitAEditer);
		
		//5 redirection vers la page édition 'editer-produit.xhtml' (ref : les clés d'outcom 'editbook' du faces-config.xml)
		
		
	}//end selectionner produit
	
	
	/**
	 * Permet de supprimer un produit dans la bdd;
	 * invoquée au click sur le lien "supprimer" de accueil.xhtml
	 * au click, l'évènement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void supprimerProduit(ActionEvent event) {
		
		//1 recup du param pasé dans le composant au click sur le lien supprimer
		UIComponent composantParam = event.getComponent().findComponent("deleteID");
		
		UIParameter cp = (UIParameter) composantParam;
		
		//2. recup de la valeur du paramètre
		int produitId = (int) cp.getValue();
		
		//3 suppression du produit dans la bdd via l'id
		
		//3.1 recup du context de JSFc
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//3.2 suppression du produit
		if (produitDAO.delete(produitId)) {
			
			//suppresion ok
			
			//envoi d'un message vers la vue via le context de JSF
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"suppresion produit", 
					"- la suppresion a été faite correctement"));
			// -> redirection vers accueil.xhtml (ref : clé d'outcom)
			
		} else {

			//suppresion échoué
			
			//=>
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"suppresion produit", 
					"- la suppresion a échoué ta vie est un echec total :p"));
		}//end else
		
		
	}//end supprimer
	

	// --------------------------getter/setter------------------------------------------------

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public List<Produit> getListeProduitBdd() {
		return listeProduitBdd;
	}

	public void setListeProduitBdd(List<Produit> listeProduitBdd) {
		this.listeProduitBdd = listeProduitBdd;
	}
	
	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

}// end class
