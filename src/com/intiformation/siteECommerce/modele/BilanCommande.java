package com.intiformation.siteECommerce.modele;

public class BilanCommande {

	private double prixTotale;
	private int IDCommande;
	private String DateCommande;
	
	
	//----------------------------cstor----------------------------------
	
	public BilanCommande(int iDCommande,double prixTotale,  String dateCommande) {
		this.prixTotale = prixTotale;
		IDCommande = iDCommande;
		DateCommande = dateCommande;
	}
	public BilanCommande() {
	}
	public BilanCommande(double prixTotale, String dateCommande) {
		this.prixTotale = prixTotale;
		DateCommande = dateCommande;
	}
	
	
	//-------------------------getter et setter------------------------------------
	
	public double getPrixTotale() {
		return prixTotale;
	}
	public void setPrixTotale(double prixTotale) {
		this.prixTotale = prixTotale;
	}
	public int getIDCommande() {
		return IDCommande;
	}
	public void setIDCommande(int iDCommande) {
		IDCommande = iDCommande;
	}
	public String getDateCommande() {
		return DateCommande;
	}
	public void setDateCommande(String dateCommande) {
		DateCommande = dateCommande;
	}
	
}
