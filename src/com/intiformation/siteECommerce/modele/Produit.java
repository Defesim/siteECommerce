package com.intiformation.siteECommerce.modele;

/**
 * Classe servant de modele pour les produits
 * @author Kevin
 *
 */
public class Produit {

	/*__________ props __________*/
	private int id_Produit;
	private String nom;
	private double prix;	
	private int quantite;
	private String description;
	private boolean selectionner;
	private String photo;
	private String categorie_NOM;

	
	/*__________ ctors __________*/
	/**
	 * ctor vide
	 */
	public Produit() {
	}//end ctor vide

	/**
	 * ctor chargé sans id
	 * @param prix
	 * @param quantite
	 * @param description
	 * @param selectionner
	 * @param photo
	 * @param categorie_NOM
	 */
	public Produit(String nom, double prix, int quantite, String description, boolean selectionner, String photo,
			String categorie_NOM) {
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.description = description;
		this.selectionner = selectionner;
		this.photo = photo;
		this.categorie_NOM = categorie_NOM;
	}//end ctor chargé sans id

	/**
	 * ctor chargé
	 * @param id_Produit
	 * @param prix
	 * @param quantite
	 * @param description
	 * @param selectionner
	 * @param photo
	 * @param categorie_NOM
	 */
	public Produit(int id_Produit, String nom, double prix, int quantite, String description, boolean selectionner, String photo,
			String categorie_NOM) {
		this.id_Produit = id_Produit;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.description = description;
		this.selectionner = selectionner;
		this.photo = photo;
		this.categorie_NOM = categorie_NOM;
	}//end ctor chargé

	
	/*__________ gt&st __________*/
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId_Produit() {
		return id_Produit;
	}
	public void setId_Produit(int id_Produit) {
		this.id_Produit = id_Produit;
	}

	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSelectionner() {
		return selectionner;
	}
	public void setSelectionner(boolean selectionner) {
		this.selectionner = selectionner;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCategorie_NOM() {
		return categorie_NOM;
	}
	public void setCategorie_NOM(String categorie_NOM) {
		this.categorie_NOM = categorie_NOM;
	}
	
	/*__________ mtods __________*/
	
}//end class
