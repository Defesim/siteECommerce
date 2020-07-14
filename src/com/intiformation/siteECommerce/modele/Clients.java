package com.intiformation.siteECommerce.modele;

/**
 * Classe servant de modele pour les clients
 * @author Kevin
 *
 */
public class Clients {

	/*__________ props __________*/
	private int id_Client;
	private String nom_Client;
	private String adresse;
	private String email;
	private String telephone;

	
	/*__________ ctors __________*/
	/**
	 * ctor vide
	 */
	public Clients() {
	}//end ctor vide

	/**
	 * ctor chargé sand id
	 * @param nom_Client
	 * @param adresse
	 * @param email
	 * @param telephone
	 */
	public Clients(String nom_Client, String adresse, String email, String telephone) {
		this.nom_Client = nom_Client;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
	}//end ctor chargé sand id

	/**
	 * ctor chargé
	 * @param id_Client
	 * @param nom_Client
	 * @param adresse
	 * @param email
	 * @param telephone
	 */
	public Clients(int id_Client, String nom_Client, String adresse, String email, String telephone) {
		this.id_Client = id_Client;
		this.nom_Client = nom_Client;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
	}//end ctor chargé

	
	/*__________ gt&st __________*/
	public int getId_Client() {
		return id_Client;
	}
	public void setId_Client(int id_Client) {
		this.id_Client = id_Client;
	}

	public String getNom_Client() {
		return nom_Client;
	}
	public void setNom_Client(String nom_Client) {
		this.nom_Client = nom_Client;
	}

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	/*__________ mtods __________*/
	
	
}//end class
