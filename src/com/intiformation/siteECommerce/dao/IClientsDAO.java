package com.intiformation.siteECommerce.dao;

import com.intiformation.siteECommerce.modele.Clients;

public interface IClientsDAO extends IUniversalDAO<Clients>{

	/**
	 * 
	 * @param pEmail
	 * @param pTele
	 * @return
	 */
	public boolean isClientsExists(String pEmail, String pTele);
	
}//end interface
