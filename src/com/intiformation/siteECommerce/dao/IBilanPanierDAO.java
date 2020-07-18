package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.BilanPanier;

public interface IBilanPanierDAO extends IUniversalDAO<BilanPanier> {

	public boolean AjoutPanierDansBilanPanier();
	
}//end interface
