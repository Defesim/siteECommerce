package com.intiformation.siteECommerce.modele;

public class BilanPanier {

	private int IDCommande;
	private int id_Produit;
	private String nom;
	private double prix;	
	private int quantite;
	
	
	//--------------------constructor--------------------------------------------------
	
	public BilanPanier(int iDCommande, int id_Produit, String nom, double prix, int quantite) {
		IDCommande = iDCommande;
		this.id_Produit = id_Produit;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
	}
	public BilanPanier() {
	}
	public BilanPanier(int id_Produit, String nom, double prix, int quantite) {
		this.id_Produit = id_Produit;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
	}
	
	
	//--------------------getter/setter--------------------------------------------------
	
	public int getIDCommande() {
		return IDCommande;
	}
	public void setIDCommande(int iDCommande) {
		IDCommande = iDCommande;
	}
	public int getId_Produit() {
		return id_Produit;
	}
	public void setId_Produit(int id_Produit) {
		this.id_Produit = id_Produit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	
}//end class
