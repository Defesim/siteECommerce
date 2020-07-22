package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.intiformation.siteECommerce.dao.IProduitDAO;
import com.intiformation.siteECommerce.dao.PanierDAOImpl;
import com.intiformation.siteECommerce.dao.ProduitDAOImpl;
import com.intiformation.siteECommerce.modele.Panier;
import com.intiformation.siteECommerce.modele.Produit;



@ManagedBean(name = "panierBean")
@SessionScoped
public class GestionPanierBean implements Serializable {

	private List<Panier> listePanierBdd;
	private Panier Panier;
	private IPanierDAO PanierDAO;
	private boolean validation;



	public GestionPanierBean() {

		PanierDAO = new PanierDAOImpl();

	}// end gestionPanierBean

	
	public List<Panier> findallPanierBdd() {
		listePanierBdd = PanierDAO.getAll();
		setListePanierBdd(listePanierBdd);
		return listePanierBdd;
	}// end listePanier
	
	@PostConstruct
	public void initialisationValidation() {
		setValidation(false);
	}
	
	// @PostConstruct force l'utilisation de la m�thode au d�but de l'application (d�pend du scoped)
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
					"- la suppresion a �t� faite correctement"));
			// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
			
		} else {

			//suppresion �chou�
			
			//=>
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"vider panier", 
					"- la suppresion a �chou� ta vie est un echec total :p"));
		}//end else
		
	}// end ViderListePanier
	
	/**
	 * initialiser le Panier
	 */
	public void initialiserPanier() {

		// instanciation d'un nouveau objet de type livre
		Panier PanierAdd = new Panier();

		// affectation du livre � la variable o� � la prop du livre
		setPanier(PanierAdd);

	}// end initialiserPanier
	
	public void initialiserListePanier() {

		// instanciation d'un nouveau objet de type livre
		List<Panier> ListePanierAdd = new ArrayList<>();

		// affectation du livre � la variable o� � la prop du livre
		setListePanierBdd(ListePanierAdd);

	}// end initialiserPanier

	public void ajouterAuPanier(ActionEvent event) {
		
	//1 recup du param pas� dans le composant au click sur le lien supprimer
			UIComponent composantParam = event.getComponent().findComponent("ajoutPanierID");
			
			UIParameter cp = (UIParameter) composantParam;
			
			//2. recup de la valeur du param�tre
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
							"- l'ajout a �t� effectu� correctement"));
					// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
					
				} else {
	
					//ajout �chou�
					
					//=>
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
							"ajout Panier", 
							"- l'ajout a �chou� ta vie est un echec total :p"));
				}//end else
			}
			else {
				//3.2 ajout au panier
				if (PanierDAO.ajouterAuPanierProduitExistant(PanierPanierId)) {
					
					//suppresion ok
					
					//envoi d'un message vers la vue via le context de JSF
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Ajout au Panier", 
							"- l'ajout a �t� effectu� correctement"));
					// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
					
				} else {
	
					//ajout �chou�
					
					//=>
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
							"ajout Panier", 
							"- l'ajout a �chou� ta vie est un echec total :p"));
				}//end else
			}
		
	}//end ajouter

/**
 * Permet de modifier un Panier dans la bdd;
 * invoqu�e au click sur le lien "modifier" de editer-Panier.xhtml
 * au click, l'�v�nement encapsule toutes les infos concernant le composant
 * 
 */
	public void modifierPanier(ActionEvent event) {
		
		
		// 1. recup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		/**
		 * la prop 'livre' du MB encapsule les infos du livre � modifier dans
		 * la base de donn�e
		 */
		UIComponent composantParam = event.getComponent().findComponent("updateID");
		
		UIParameter cp = (UIParameter) composantParam;
		
		//2. recup de la valeur du param�tre
		int PanierId = (int) cp.getValue();
		
		
		
		IProduitDAO ProduitDAOPourQuantite;
		ProduitDAOPourQuantite = new ProduitDAOImpl();
		
		Panier panierAEditer = PanierDAO.getById(PanierId);
		Produit ProduitSelection = ProduitDAOPourQuantite.getById(PanierId);
		int QuantiteDisponible = ProduitSelection.getQuantite();
		
		for(Panier pPanier : listePanierBdd) {
			if (pPanier.getId_Produit()==PanierId) {
				if (pPanier.getQuantite() < QuantiteDisponible) {
					panierAEditer.setQuantite(pPanier.getQuantite());
				}
				else {
					panierAEditer.setQuantite(QuantiteDisponible);
					FacesMessage messageYouWantTooMuch = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification", "La quantit� a �t� fix�e au maximum disponible, celle que vous aviez demand�e �tait plus grande...");
					
					//--> envoie du message
					contextJSF.addMessage(null, messageYouWantTooMuch);
				}
			}
		}
		
		
		
		//2. modification du livre dans la bdd
		if (PanierDAO.update(panierAEditer)) {
			this.findallPanierBdd();
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
		
		this.findallPanierBdd();
		
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

	public List<Panier> getListePanierBdd() {
		return listePanierBdd;
	}

	public void setListePanierBdd(List<Panier> listePanierBdd) {
		this.listePanierBdd = listePanierBdd;
	}
	
	public boolean isValidation() {
		return validation;
	}


	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	
	
}//end class
