package com.intiformation.siteECommerce.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.siteECommerce.dao.UtilisateurDAOImpl;
import com.intiformation.siteECommerce.modele.Utilisateur;


@ManagedBean(name="utilisateurBean")
@SessionScoped
public class UtilisateurBean {
	
	private List<Utilisateur> listeUserbdd;
	private Utilisateur User;
	private UtilisateurDAOImpl UserDao;
	
	public UtilisateurBean() {
		UserDao = new UtilisateurDAOImpl();
	}
	
	
	public List<Utilisateur> findAllUtilisateurBdd(){
		listeUserbdd = UserDao.getAll();
		
		return listeUserbdd;
	}

}//end class
