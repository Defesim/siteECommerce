package com.intiformation.siteECommerce.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.siteECommerce.dao.UtilisateurDAOImpl;
import com.intiformation.siteECommerce.modele.Utilisateur;


@ManagedBean(name="utilisateurBean")
@SessionScoped
public class UtilisateurBean {
	
	private List<Utilisateur> listeLivrebdd;
	private Utilisateur livre;
	private UtilisateurDAOImpl livreDao;
	
	public UtilisateurBean() {
		livreDao = new UtilisateurDAOImpl();
	}
	
	
	public List<Utilisateur> findAllUtilisateurBdd(){
		listeLivrebdd = livreDao.getAll();
		
		return listeLivrebdd;
	}

}
