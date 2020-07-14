package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.Utilisateur;

public interface IUtilisateurDAO extends IUniversalDAO<Utilisateur> {

	 /**
	  * méthodes spécifiques à l'utilisateur	 
	  * @param pMail
	  * @param pMdp
	  * @return
	  */
	public boolean isUtilisateurExists(String pMail, String pMdp);
	
	
}
