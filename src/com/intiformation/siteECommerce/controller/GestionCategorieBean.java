package com.intiformation.siteECommerce.controller;

import java.io.Serializable;
import java.util.List;

import com.intiformation.siteECommerce.dao.ICategorieDAO;
import com.intiformation.siteECommerce.modele.Categorie;



public class GestionCategorieBean implements Serializable {

	private List<Categorie> listeCategorieBdd;	
	private Categorie categorie; 
	private ICategorieDAO CategorieDAO;
	
	
}//end class
