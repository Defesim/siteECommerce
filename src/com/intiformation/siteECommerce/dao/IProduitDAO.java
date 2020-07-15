package com.intiformation.siteECommerce.dao;

import java.util.List;

import com.intiformation.siteECommerce.modele.Produit;

/**
 * Interface de la DAO pour les produits.
 * @author Kevin
 *
 */
public interface IProduitDAO extends IUniversalDAO<Produit> {
	
	public List<Produit> getByCategorie (String NomCategorie);
	
	public boolean ModifierQuantite (Produit pProduit);
	
}//end interface
