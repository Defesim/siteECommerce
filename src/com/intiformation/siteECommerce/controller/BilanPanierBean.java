package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.intiformation.siteECommerce.dao.BilanPanierDAOImpl;
import com.intiformation.siteECommerce.dao.IBilanPanierDAO;
import com.intiformation.siteECommerce.modele.BilanPanier;

@ManagedBean (name = "bilanPanierBean") 
@SessionScoped
public class BilanPanierBean implements Serializable {
	
	private List<BilanPanier> listeBilanPanierBdd;
	private BilanPanier BilanPanier;
	private IBilanPanierDAO BilanPanierDAO;

	public BilanPanierBean() {

		BilanPanierDAO = new BilanPanierDAOImpl();

	}// end gestionPanierBean

	public List<BilanPanier> findallBilanPanierBdd() {
		listeBilanPanierBdd = BilanPanierDAO.getAll();
		setListeBilanPanierBdd(listeBilanPanierBdd);
		return listeBilanPanierBdd;
	}// end listePanier

	

public String PanierDansBilanPanier() {
		
		//1 recup du context de JSF 
		FacesContext context = FacesContext.getCurrentInstance();
		
		//2 ajout nouveau Panier
		boolean verifAjout = BilanPanierDAO.AjoutPanierDansBilanPanier();
		
		if (verifAjout) {
			
			//ajout ok
			
			//=> envoie d'un message vers la vue 'accueil.xhtml'
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout Panier ", "Ajout fait avec succés"));
			
			//redirection vers la page 'accueil.xhtml'
			return "Panier.xhtml?faces-redirect=true";
			
		} else { //ajout not ok

			FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout Panier raté ", "Ajout pas fait avec succés");

			// envoie d'un message vers la vue 'ajouter-Panier.xhtml'
			return "Panier.xhtml?faces-redirect=true";
			
		}//end else
		
	}//end ajout



//-------------------------getter et setter---------------------------------------

public BilanPanier getBilanPanier() {
	return BilanPanier;
}

public void setBilanPanier(BilanPanier bilanPanier) {
	BilanPanier = bilanPanier;
}

public void setListeBilanPanierBdd(List<BilanPanier> listeBilanPanierBdd) {
	this.listeBilanPanierBdd = listeBilanPanierBdd;
}

public List<BilanPanier> getListeBilanPanierBdd() {
	return listeBilanPanierBdd;
}



}
