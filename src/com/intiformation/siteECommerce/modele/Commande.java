package com.intiformation.siteECommerce.modele;

public class Commande {

	private double prixTotale;
	private int IDCommande;
	private String DateCommande;
	
	
	//----------------------------cstor----------------------------------
	
	public Commande(double prixTotale, int iDCommande, String dateCommande) {
		this.prixTotale = prixTotale;
		IDCommande = iDCommande;
		DateCommande = dateCommande;
	}
	public Commande() {
	}
	public Commande(double prixTotale, String dateCommande) {
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
	
}//end class
