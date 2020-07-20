package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.intiformation.siteECommerce.dao.BilanCommandeDAOImpl;
import com.intiformation.siteECommerce.dao.IBilanCommandeDAO;
import com.intiformation.siteECommerce.modele.BilanCommande;

@ManagedBean(name = "bilanCommandeBean")
@SessionScoped
public class BilanCommandeBean implements Serializable {

	private List<BilanCommande> listeBilanCommandeBdd;
	private BilanCommande BilanCommande;
	private IBilanCommandeDAO BilanCommandeDAO;

	public BilanCommandeBean() {

		BilanCommandeDAO = new BilanCommandeDAOImpl();

	}// end gestionCommandeBean
	
	public List<BilanCommande> findallBilanCommandeBdd() {
		listeBilanCommandeBdd = BilanCommandeDAO.getAll();
		setListeBilanCommandeBdd(listeBilanCommandeBdd);
		return listeBilanCommandeBdd;
	}// end listeCommande

	public void CommandeDansBilanCommande() {

		// 1 recup du context de JSF
		FacesContext context = FacesContext.getCurrentInstance();

		// 2 ajout nouveau commande
		boolean verifAjout = BilanCommandeDAO.AjouterCommandeDansBilanCommande();

		if (verifAjout) {

			// ajout ok

			// => envoie d'un message vers la vue 'accueil.xhtml'
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout commande ", "Ajout fait avec succés"));


		} else { // ajout not ok

			FacesMessage messagenotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ajout commande raté ",
					"Ajout pas fait avec succés");


		} // end else

	}// end ajout

	// ---------------------------------getter et setter----------
	public List<BilanCommande> getListeBilanCommandeBdd() {
		return listeBilanCommandeBdd;
	}

	public void setListeBilanCommandeBdd(List<BilanCommande> listeBilanCommandeBdd) {
		this.listeBilanCommandeBdd = listeBilanCommandeBdd;
	}

	public BilanCommande getBilanCommande() {
		return BilanCommande;
	}

	public void setBilanCommande(BilanCommande bilanCommande) {
		BilanCommande = bilanCommande;
	}

}// end class
