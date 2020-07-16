package com.intiformation.siteECommerce.dao;

import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;

public interface ICategorieDAO extends IUniversalDAO<Categorie> {
	
	/**
	 * permet de suprimer une cat�gorie dans la bdd.
	 * @param name : nom de la cat�gorie � supprimer
	 * @return : true si suppression OK, sinon false
	 */
	public boolean delete(String name);
	
	/**
	 * permet de r�cup�rer une cat�gorie dans la BDD par son nom.
	 * @param name : nom de la cat�gorie a r�cup�rer
	 * @return : Categorie a r�cup�rer
	 */
	public Categorie getByName(String name);
	
	public List<Categorie> getByRecherche (String motCle); 
	
}//end interface
