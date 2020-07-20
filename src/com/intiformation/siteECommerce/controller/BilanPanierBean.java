package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.intiformation.siteECommerce.dao.BilanPanierDAOImpl;
import com.intiformation.siteECommerce.dao.IBilanPanierDAO;
import com.intiformation.siteECommerce.modele.BilanPanier;
import com.intiformation.siteECommerce.modele.Panier;

@ManagedBean (name = "bilanPanierBean") 
@SessionScoped
public class BilanPanierBean implements Serializable {
	
	private List<BilanPanier> ListeBilanPanierBdd;
	private BilanPanier BilanPanier;
	private IBilanPanierDAO BilanPanierDAO;
	private List<Panier> listePanier;


	private int idCommande;



	public BilanPanierBean() {

		BilanPanierDAO = new BilanPanierDAOImpl();

	}// end gestionPanierBean

	public List<BilanPanier> findallBilanPanierBdd() {
		List<BilanPanier> listeBilanPanierBdd = BilanPanierDAO.getAll();
		setListeBilanPanierBdd(listeBilanPanierBdd);
		return listeBilanPanierBdd;
	}// end listePanier
	
	public void findallBilanPanierBddParId() {
		
		List<BilanPanier> listeBilanPanier = BilanPanierDAO.getAll();
		List<BilanPanier> listeFinale = new ArrayList<>();
		for (BilanPanier bilanPanier : listeBilanPanier) {
			if(bilanPanier.getIDCommande()==idCommande) {
				listeFinale.add(bilanPanier);
			}
		}
		setListeBilanPanierBdd(listeFinale);

	}// end listePanier

	

public void PanierDansBilanPanier() {
		
		//1 recup du context de JSF 
		FacesContext context = FacesContext.getCurrentInstance();
		
		for (Panier pan : listePanier) {
			
		//2 ajout nouveau Panier
		boolean verifAjout = BilanPanierDAO.AjoutPanierDansBilanPanier(pan, idCommande);
		
		if (verifAjout) {
			
			//ajout ok
			
			//=> envoie d'un message vers la vue 'accueil.xhtml'
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout Panier ", "Ajout fait avec succés"));
			
			
		} else { //ajout not ok

			FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout Panier raté ", "Ajout pas fait avec succés");

			
		}//end else
		
		}
		
	}//end ajout



//-------------------------getter et setter---------------------------------------

public BilanPanier getBilanPanier() {
	return BilanPanier;
}

public void setBilanPanier(BilanPanier bilanPanier) {
	BilanPanier = bilanPanier;
}

public void setListeBilanPanierBdd(List<BilanPanier> listeBilanPanierBdd) {
	this.ListeBilanPanierBdd = listeBilanPanierBdd;
}

public List<BilanPanier> getListeBilanPanierBdd() {
	return ListeBilanPanierBdd;
}
public int getIdCommande() {
	return idCommande;
}

public void setIdCommande(int idCommande) {
	this.idCommande = idCommande;
}
public List<Panier> getListePanier() {
	return listePanier;
}

public void setListePanier(List<Panier> listePanier) {
	this.listePanier = listePanier;
}


}
