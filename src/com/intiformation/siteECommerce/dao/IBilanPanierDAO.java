package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.BilanPanier;
import com.intiformation.siteECommerce.modele.Panier;

public interface IBilanPanierDAO extends IUniversalDAO<BilanPanier> {

	public boolean AjoutPanierDansBilanPanier(Panier pan, int IdCommande);
	
}//end interface
