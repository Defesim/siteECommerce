package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.Utilisateur;

public interface IUtilisateurDAO extends IUniversalDAO<Utilisateur> {

	 /**
	  * m�thodes sp�cifiques � l'utilisateur	 
	  * @param pMail
	  * @param pMdp
	  * @return
	  */
	public boolean isUtilisateurExists(String pMail, String pMdp);
	
	
}
