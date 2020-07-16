package com.intiformation.siteECommerce.dao;

import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;

public interface ICategorieDAO extends IUniversalDAO<Categorie> {
	
	/**
	 * permet de suprimer une catégorie dans la bdd.
	 * @param name : nom de la catégorie à supprimer
	 * @return : true si suppression OK, sinon false
	 */
	public boolean delete(String name);
	
	/**
	 * permet de récupérer une catégorie dans la BDD par son nom.
	 * @param name : nom de la catégorie a récupérer
	 * @return : Categorie a récupérer
	 */
	public Categorie getByName(String name);
	
	public List<Categorie> getByRecherche (String motCle); 
	
}//end interface
