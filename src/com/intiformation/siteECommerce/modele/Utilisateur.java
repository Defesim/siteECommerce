package com.intiformation.siteECommerce.modele;

/**
 * Classe servant de modele pour les utilisateurs
 * @author Kevin
 *
 */
public class Utilisateur {

	/*__________ props __________*/
	private int id_Utilisateur;
	private String identifiant;
	private String mot_de_passe;

	
	/*__________ ctors __________*/
	/**
	 * ctor vide
	 */
	public Utilisateur() {
	}//end ctor vide

	/**
	 * ctor chargé sans id
	 * @param identifiant
	 * @param mot_de_passe
	 */
	public Utilisateur(String identifiant, String mot_de_passe) {
		this.identifiant = identifiant;
		this.mot_de_passe = mot_de_passe;
	}//end ctor chargé sans id

	/**
	 * ctor chargé
	 * @param id_Utilisateur
	 * @param identifiant
	 * @param mot_de_passe
	 */
	public Utilisateur(int id_Utilisateur, String identifiant, String mot_de_passe) {
		this.id_Utilisateur = id_Utilisateur;
		this.identifiant = identifiant;
		this.mot_de_passe = mot_de_passe;
	}//end ctor chargé
	
	
	/*__________ gt&st __________*/
	public int getId_Utilisateur() {
		return id_Utilisateur;
	}
	public void setId_Utilisateur(int id_Utilisateur) {
		this.id_Utilisateur = id_Utilisateur;
	}

	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	
	/*__________ mtods __________*/
	
}//end class
