package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.Commande;

public interface ICommandeDAO extends IUniversalDAO<Commande> {

	public boolean PanierDansCommande();
	
	public boolean ViderCommande();
	
	
}//end interface

