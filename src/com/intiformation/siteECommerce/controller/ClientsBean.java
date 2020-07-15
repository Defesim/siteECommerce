package com.intiformation.siteECommerce.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.siteECommerce.dao.ClientsDAOImpl;
import com.intiformation.siteECommerce.modele.Clients;



@ManagedBean(name="ClientsBean")
@SessionScoped
public class ClientsBean {

	private List<Clients> listeClientsbdd;
	private Clients Clients;
	private ClientsDAOImpl ClientsDAO;
	
	public ClientsBean() {
		ClientsDAO = new ClientsDAOImpl();
	}
	
	
	public List<Clients> findAllClientsBdd(){
		listeClientsbdd = ClientsDAO.getAll();
		
		return listeClientsbdd;
	}//end findAllClients
	
	/**
	 * initialiser le Clients
	 */
	public void initialiserClients() {

		// instanciation d'un nouveau objet de type Clients
		Clients ClientsAdd = new Clients();

		// affectation du Clients � la variable o� � la prop du Clients
		setClients(ClientsAdd);

	}// end initialiserClients

	/**
	 * ajouter Clients
	 */
	public String ajouterClients() {
		
		//1 recup du context de JSF 
				FacesContext context = FacesContext.getCurrentInstance();
				
				//2 ajout nouveau Clients
				boolean verifAjout = ClientsDAO.add(Clients);
				
				if (verifAjout) {
					
					//ajout ok
					
					//=> envoie d'un message vers la vue 'accueil.xhtml'
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout Clients ", "Ajout fait avec succ�s"));
					
					//redirection vers la page 'accueil.xhtml'
					return "accueil.xhtml?faces-redirect=true";
					
				} else { //ajout not ok

					FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout Clients rat� ", "Ajout pas fait avec succ�s");

					// envoie d'un message vers la vue 'ajouter-Clients.xhtml'
					return "ajouter-Clients.xhtml?faces-redirect=false";
					
				}//end else
		
	}//end ajouter
	
	/**
	 * Permet de modifier un Clients dans la bdd;
	 * invoqu�e au click sur le lien "modifier" de editer-Clients.xhtml
	 * au click, l'�v�nement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void modifierClients(ActionEvent event) {
		
		/**
		 * la prop 'Clients' du MB encapsule les infos du Clients � modifier dans
		 * la base de donn�e
		 */
		
		// 1. recup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. modification du Clients dans la bdd
		if (ClientsDAO.update(Clients)) {
			
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
		
		// redirection vers la page accueil.xhtml ref : la cl� d'outcom : 'listClients'

		// naviguer de edit-Clients � accueil
		
		
	}//end modifier Clients
	
	/**
	 * Permet de editer un Clients dans la bdd;
	 * invoqu�e au click sur le lien "editer" de accueil.xhtml
	 * au click, l'�v�nement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void selectionnerClients(ActionEvent event) {
		
		//1. recup du param�tre pass� au composant au click sur le lien "�diter"
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2 recup de la valeur
		int ClientsID = (int) uip.getValue();
		
		//3 recup du Clients � �diter via l'id dans la bdd
		Clients ClientsAEditer = ClientsDAO.getById(ClientsID);
		
		//4 affectation du Clients � �diter � la variable 'Clients ' du MB
		setClients(ClientsAEditer);
		
		//5 redirection vers la page �dition 'editer-Clients .xhtml' (ref : les cl�s d'outcom 'editClients ' du faces-config.xml)
		
		
		
		
		
	}//end selectionner Clients 
	
	
	/**
	 * Permet de supprimer une Clients dans la bdd;
	 * invoqu�e au click sur le lien "supprimer" de accueil.xhtml
	 * au click, l'�v�nement encapsule toutes les infos concernant le composant
	 * 
	 */
	public void supprimerClients (ActionEvent event) {
		
		//1 recup du param pas� dans le composant au click sur le lien supprimer
		UIComponent composantParam = event.getComponent().findComponent("deleteID");
		
		UIParameter cp = (UIParameter) composantParam;
		
		//2. recup de la valeur du param�tre
		int ClientsId = (int) cp.getValue();
		
		//3 suppression du Clients dans la bdd via l'id
		
		//3.1 recup du context de JSFc
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//3.2 suppression du Clients
		if (ClientsDAO.delete(ClientsId)) {
			
			//suppresion ok
			
			//envoi d'un message vers la vue via le context de JSF
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"suppresion Clients", 
					"- la suppresion a �t� faite correctement"));
			// -> redirection vers accueil.xhtml (ref : cl� d'outcom)
			
		} else {

			//suppresion �chou�
			
			//=>
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"suppresion Clients", 
					"- la suppresion a �chou� ta vie est un echec total :p"));
		}//end else
		
		
	}//end supprimer


	
	
	
	//----------------------------getter et setter-----------------------------------------------------------------
	
	public Clients getClients() {
		return Clients;
	}


	public void setClients(Clients clients) {
		Clients = clients;
	}
	
	
	
	
	
}//end class
