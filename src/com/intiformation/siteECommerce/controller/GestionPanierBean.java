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

import com.intiformation.siteECommerce.dao.IPanierDAO;
import com.intiformation.siteECommerce.dao.PanierDAOImpl;
import com.intiformation.siteECommerce.modele.Panier;



@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {

	private List<Panier> listePanierBdd;
	private Panier Panier;
	private IPanierDAO PanierDAO;
	private int quantiteModif;



	public GestionPanierBean() {

		PanierDAO = new PanierDAOImpl();

	}// end gestionPanierBean

	
	public List<Panier> findallPanierBdd() {
		listePanierBdd = PanierDAO.getAll();
		setListePanierBdd(listePanierBdd);
		return listePanierBdd;
	}// end listePanier
	
	// @PostConstruct force l'utilisation de la méthode au début de l'application (dépend du scoped)
	@PostConstruct
	public void ViderPanierBdd() {
		
		
		//3.1 recup du context de JSFc
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//3.2 suppression du livre
		if (PanierDAO.viderPanier()) {
			
			//suppresion ok
			
			//envoi d'un message vers la vue via le context de JSF
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"vider panier", 
					"- la suppresion a été faite correctement"));
			// -> redirection vers accueil.xhtml (ref : clé d'outcom)
			
		} else {

			//suppresion échoué
			
			//=>
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"vider panier", 
					"- la suppresion a échoué ta vie est un echec total :p"));
		}//end else
		
	}// end ViderListePanier
	
	/**
	 * initialiser le Panier
	 */
	public void initialiserPanier() {

		// instanciation d'un nouveau objet de type livre
		Panier PanierAdd = new Panier();

		// affectation du livre à la variable où à la prop du livre
		setPanier(PanierAdd);

	}// end initialiserPanier

	public void ajouterAuPanier(ActionEvent event) {
		
	//1 recup du param pasé dans le composant au click sur le lien supprimer
			UIComponent composantParam = event.getComponent().findComponent("ajoutPanierID");
			
			UIParameter cp = (UIParameter) composantParam;
			
			//2. recup de la valeur du paramètre
			int PanierPanierId = (int) cp.getValue();
			
			
			//3 suppression du livre dans la bdd via l'id
			
			//3.1 recup du context de JSFc
			FacesContext contextJSF = FacesContext.getCurrentInstance();
			
			boolean PremierAjout = true;
			List<Panier> ListePanierEnCours = getListePanierBdd();
			if (ListePanierEnCours!=null) {
				for (Panier panierEnCours : ListePanierEnCours) {
					if (panierEnCours.getId_Produit()==PanierPanierId) {
						PremierAjout = false;
					}
				}
			}
			
			if (PremierAjout) {
				//3.2 ajout au panier
				if (PanierDAO.ajouterAuPanier(PanierPanierId)) {
					
					//suppresion ok
					
					//envoi d'un message vers la vue via le context de JSF
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Ajout au Panier", 
							"- l'ajout a été effectué correctement"));
					// -> redirection vers accueil.xhtml (ref : clé d'outcom)
					
				} else {
	
					//ajout échoué
					
					//=>
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
							"ajout Panier", 
							"- l'ajout a échoué ta vie est un echec total :p"));
				}//end else
			}
			else {
				//3.2 ajout au panier
				if (PanierDAO.ajouterAuPanierProduitExistant(PanierPanierId)) {
					
					//suppresion ok
					
					//envoi d'un message vers la vue via le context de JSF
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Ajout au Panier", 
							"- l'ajout a été effectué correctement"));
					// -> redirection vers accueil.xhtml (ref : clé d'outcom)
					
				} else {
	
					//ajout échoué
					
					//=>
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
							"ajout Panier", 
							"- l'ajout a échoué ta vie est un echec total :p"));
				}//end else
			}
		
	}//end ajouter

/**
 * Permet de modifier un Panier dans la bdd;
 * invoquée au click sur le lien "modifier" de editer-Panier.xhtml
 * au click, l'évènement encapsule toutes les infos concernant le composant
 * 
 */
	public void modifierPanier(ActionEvent event) {
		
		/**
		 * la prop 'livre' du MB encapsule les infos du livre à modifier dans
		 * la base de donnée
		 */
		UIComponent composantParam = event.getComponent().findComponent("updateID");
		
		UIParameter cp = (UIParameter) composantParam;
		
		//2. recup de la valeur du paramètre
		int PanierId = (int) cp.getValue();
		
		Panier panierAEditer = PanierDAO.getById(PanierId);
		System.out.println(PanierId);
		
		panierAEditer.setQuantite(getQuantiteModif());
		
		// 1. recup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. modification du livre dans la bdd
		if (PanierDAO.update(panierAEditer)) {
			this.findallPanierBdd();
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
		
		// redirection vers la page accueil.xhtml ref : la clé d'outcom : 'listPanier'

		// naviguer de edit-Panier à accueil
		
		
	}//end modifier Panier

public void supprimerPanier(ActionEvent event) {
	
	//1 recup du param pasé dans le composant au click sur le lien supprimer
	UIComponent composantParam = event.getComponent().findComponent("deleteID");
	
	UIParameter cp = (UIParameter) composantParam;
	
	//2. recup de la valeur du paramètre
	int PanierId = (int) cp.getValue();
	
	//3 suppression du livre dans la bdd via l'id
	
	//3.1 recup du context de JSFc
	FacesContext contextJSF = FacesContext.getCurrentInstance();
	
	//3.2 suppression du livre
	if (PanierDAO.delete(PanierId)) {
		
		this.findallPanierBdd();
		
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
	
	
	
	//-------------------------getter et setter-------------------------------
	
	
	
	
	public Panier getPanier() {
		return Panier;
	}

	public void setPanier(Panier panier) {
		Panier = panier;
	}

	public List<Panier> getListePanierBdd() {
		return listePanierBdd;
	}

	public void setListePanierBdd(List<Panier> listePanierBdd) {
		this.listePanierBdd = listePanierBdd;
	}
	
	public int getQuantiteModif() {
		return quantiteModif;
	}


	public void setQuantiteModif(int quantiteModif) {
		this.quantiteModif = quantiteModif;
	}
	
}//end class
