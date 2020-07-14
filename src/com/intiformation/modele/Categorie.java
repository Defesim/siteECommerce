package com.intiformation.modele;

/**
 * Classe servant de modele pour les catégories
 * @author Kevin
 *
 */
public class Categorie {

	/*__________ props __________*/
	private String nom_Categorie;
	private String description;	
	
	/*__________ ctors __________*/
	/**
	 * ctor vide
	 */
	public Categorie() {
	}//end ctor vide

	/**
	 * ctor chargé sans nom
	 * @param description
	 */
	public Categorie(String description) {
		this.description = description;
	}//end ctor charge sans nom

	/**
	 * ctor chargé
	 * @param nom_Categorie
	 * @param description
	 */
	public Categorie(String nom_Categorie, String description) {
		this.nom_Categorie = nom_Categorie;
		this.description = description;
	}//end ctor charge
	
	/*__________ gt&st __________*/
	public String getNom_Categorie() {
		return nom_Categorie;
	}
	public void setNom_Categorie(String nom_Categorie) {
		this.nom_Categorie = nom_Categorie;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*__________ mtods __________*/
	
	
}//end class
