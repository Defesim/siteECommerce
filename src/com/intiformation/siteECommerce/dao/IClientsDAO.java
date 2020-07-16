package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;
import com.intiformation.siteECommerce.modele.Clients;

public interface IClientsDAO extends IUniversalDAO<Clients>{

	/**
	 * 
	 * @param pEmail
	 * @param pTele
	 * @return
	 */
	public boolean isClientsExists(String pEmail, String pTele);
	
	public List<Clients> getByRecherche(String motCle);
		
}//end interface
