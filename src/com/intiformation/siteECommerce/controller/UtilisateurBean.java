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
		setListeUserbdd(listeUserbdd);
		return listeUserbdd;
	}


	
	/*____________ gt&st ______________*/
	
	public Utilisateur getUser() {
		return User;
	}


	public void setUser(Utilisateur user) {
		User = user;
	}
	
	public List<Utilisateur> getListeUserbdd() {
		return listeUserbdd;
	}

	public void setListeUserbdd(List<Utilisateur> listeUserbdd) {
		this.listeUserbdd = listeUserbdd;
	}

}//end class
