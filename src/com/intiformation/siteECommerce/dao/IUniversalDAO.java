package com.intiformation.siteECommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.siteECommerce.tools.DBConnection;


/**
 * interface générique de la couche DAO qui est implémentée
 * toute autre interface de la DAO
 * @author Kevin
 *
 */
public interface IUniversalDAO<T> {

	// récup d'une connexion vers la bdd via l'utilitaire ConnexionBdd
	public Connection connection = DBConnection.getInstance();
	
	// déclaration des méthodes à exposer dans la DAO
	/**
	 * permet d'ajouter tout type d'objet dans la bdd.
	 * @param t : objet à ajouter
	 * @return true si ajout ok sinon false
	 */
	public boolean add(T t);
	
	/**
	 * permet de modifier tout type d'objet dans la bdd.
	 * @param t : objet à modifier
	 * @return true si modification ok sinon false
	 */
	public boolean update(T t);
	
	/**
	 * permet de suprimer tout type d'objet dans la bdd.
	 * @param id : id de l'objet à supprimer
	 * @return : true si suppression OK, sinon false
	 */
	public boolean delete(Integer id);
	
	/**
	 * permet de récupérer la liste de tout type d'objet depuis la bdd.
	 * @return : la liste des objets
	 */
	public List<T> getAll();
	
	/**
	 * permet de récupérer tout type d'objet via son id (pk) depuis la bdd.
	 * @param id : l'id de l'objet à récupérer
	 * @return : l'objet
	 */
	public T getById(Integer id);
	
}//end interface
