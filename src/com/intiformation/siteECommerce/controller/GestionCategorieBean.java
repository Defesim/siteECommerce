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

import com.intiformation.siteECommerce.dao.CategorieDAOImpl;
import com.intiformation.siteECommerce.dao.ICategorieDAO;
import com.intiformation.siteECommerce.modele.Categorie;
import com.intiformation.siteECommerce.modele.Produit;

@ManagedBean(name = "categorieBean")
@SessionScoped
public class GestionCategorieBean implements Serializable{

	private List<Categorie> listeCategorieBdd;
	private Categorie categorie;
	private ICategorieDAO categorieDAO;
	private String motCle;
	


	public GestionCategorieBean() {
		
		categorieDAO = new CategorieDAOImpl();
		
	}//end GestionCategorieBean
	
	@PostConstruct
	public void SetallCategorie(){
		listeCategorieBdd = categorieDAO.getAll();
		setListeCategorieBdd(listeCategorieBdd);
	}
	
	public List<Categorie> findallCategorie(){
		listeCategorieBdd = categorieDAO.getAll();
		setListeCategorieBdd(listeCategorieBdd);
		return listeCategorieBdd;
		
	}//findallCategorie
	
	public List<Categorie> findCategorieByMotCle(ActionEvent event) {
		listeCategorieBdd = categorieDAO.getByRecherche(motCle);
		setListeCategorieBdd(listeCategorieBdd);
		return listeCategorieBdd;
	}// end findProduitByMotCle
	
	/**
	 * initialiser le categorie
	 */
	public void initialiserCategorie() {

		// instanciation d'un nouveau objet de type categorie
		Categorie categorieAdd = new Categorie();

		// affectation du categorie à la variable où à la prop du categorie
		setCategorie(categorieAdd);

	}// end initialisercategorie

	/**
	 * ajouter categorie
	 */
	public String ajouterCategorie() {
		
		//1 recup du context de JSF 
				FacesContext context = FacesContext.getCurrentInstance();
				
				//2 ajout nouveau categorie
				boolean verifAjout = categorieDAO.add(categorie);
				
				if (verifAjout) {
					
					//ajout ok
					
					//=> envoie d'un message vers la vue 'accueil.xhtml'
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout categorie ", "Ajout fait avec succés"));
					
					//redirection vers la page 'accueil.xhtml'
					return "accueil.xhtml?faces-redirect=true";
					
				} else { //ajout not ok

					FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout categorie raté ", "Ajout pas fait avec succés");

					// envoie d'un message vers la vue 'ajouter-categorie.xhtml'
					return "ajouter-categorie.xhtml?faces-redirect=false";
					
				}//end else
		
	}//end ajouter
	
	/**
	 * Permet de modifier un categorie dans la bdd;
	 * invoquée au click sur le lien "modifier" de editer-categorie.xhtml
	 * au click, l'évènement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void modifierCategorie(ActionEvent event) {
		
		/**
		 * la prop 'categorie' du MB encapsule les infos du categorie à modifier dans
		 * la base de donnée
		 */
		
		// 1. recup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. modification du categorie dans la bdd
		if (categorieDAO.update(categorie)) {
			
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
		
		// redirection vers la page accueil.xhtml ref : la clé d'outcom : 'listcategorie'

		// naviguer de edit-categorie à accueil
		
		
	}//end modifier categorie
	
	/**
	 * Permet de editer un categorie dans la bdd;
	 * invoquée au click sur le lien "editer" de accueil.xhtml
	 * au click, l'évènement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void selectionnerCategorie(ActionEvent event) {
		
		//1. recup du paramètre passé au composant au click sur le lien "éditer"
		UIParameter uip = (UIParameter) event.getComponent().findComponent("nomCategorieSelection");
		
		//2 recup de la valeur
		String categorieID = (String) uip.getValue();
		
		//3 recup du categorie à éditer via l'id dans la bdd
		Categorie categorieAEditer = categorieDAO.getByName(categorieID);
		
		//4 affectation du categorie à éditer à la variable 'categorie ' du MB
		setCategorie(categorieAEditer);
		
		//5 redirection vers la page édition 'editer-categorie .xhtml' (ref : les clés d'outcom 'editcategorie ' du faces-config.xml)
		
		
		
		
		
	}//end selectionner categorie 
	
	
	/**
	 * Permet de supprimer une categorie dans la bdd;
	 * invoquée au click sur le lien "supprimer" de accueil.xhtml
	 * au click, l'évènement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void supprimerCategorie (ActionEvent event) {
		
		//1 recup du param pasé dans le composant au click sur le lien supprimer
		UIComponent composantParam = event.getComponent().findComponent("deleteID");
		
		UIParameter cp = (UIParameter) composantParam;
		
		//2. recup de la valeur du paramètre
		String categorieId = (String) cp.getValue();
		
		//3 suppression du categorie dans la bdd via l'id
		
		//3.1 recup du context de JSFc
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//3.2 suppression du categorie
		if (categorieDAO.delete(categorieId)) {
			
			this.findallCategorie();
			
			//suppresion ok
			
			//envoi d'un message vers la vue via le context de JSF
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"suppresion categorie", 
					"- la suppresion a été faite correctement"));
			// -> redirection vers accueil.xhtml (ref : clé d'outcom)
			
		} else {

			//suppresion échoué
			
			//=>
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"suppresion categorie", 
					"- la suppresion a échoué ta vie est un echec total :p"));
		}//end else
		
		
	}//end supprimer
	
	//-------------------getter/setter------------------------------------------------
	
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategorieBdd() {
		return listeCategorieBdd;
	}

	public void setListeCategorieBdd(List<Categorie> listeCategorieBdd) {
		this.listeCategorieBdd = listeCategorieBdd;
	}
	
	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	
}//end class
