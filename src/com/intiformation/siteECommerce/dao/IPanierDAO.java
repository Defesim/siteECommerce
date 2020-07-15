package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.Panier;

public interface IPanierDAO extends IUniversalDAO<Panier>{

	public boolean ajouterAuPanier ( Integer pProduitIdPourPanier);
	
	public boolean viderPanier (Panier panier);
	
}//end interface
