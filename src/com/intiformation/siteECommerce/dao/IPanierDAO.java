package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.Panier;

public interface IPanierDAO extends IUniversalDAO<Panier>{

	public boolean ajouterAuPanier ( Integer pProduitIdPourPanier);
	
	public boolean ajouterAuPanierProduitExistant (int idProduit);
	
	public boolean viderPanier ();
	
}//end interface
