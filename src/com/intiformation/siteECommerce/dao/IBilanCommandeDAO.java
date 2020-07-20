package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.BilanCommande;

public interface IBilanCommandeDAO extends IUniversalDAO<BilanCommande>{
	
	public boolean AjouterCommandeDansBilanCommande();
	
	public int getLastId();

}
