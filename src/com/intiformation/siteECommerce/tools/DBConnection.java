package com.intiformation.siteECommerce.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * utilitaire pour r�cup�rer une connexion � la BDD
 * @author Kevin
 *
 */
public class DBConnection {

	// infos de connexion � la BDD
	private static final String DB_url = "jdbc:mysql://localhost:3306/gestion_compte";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	// m�thode pour r�cup�rer la connexion � la BDD
	private static Connection connexion = null;
	
	/**
	 * ctor en private pour interdire l'instanciation d'un objet de la classe
	 */
	private DBConnection() {
	}//end ctor
	
	// m�thode pour r�cup�rer la connexion � la BDD
	/**
	 * 
	 * @return
	 */
	public static Connection getInstance() {
		
		if(connexion == null) {
			
			try {
				// 1. chargement du pilote
				Class.forName(JDBC_DRIVER_CLASS);
				
				// 2. r�cup de la connexion via le DriverManager
				connexion = DriverManager.getConnection(DB_url, DB_USER, DB_PASSWORD);
			}
			catch(ClassNotFoundException | SQLException e){
				System.out.println("--> DBConnection <-- Erreur lors de la r�cup�ration de la connexion vers la base de donn�es...");
				e.printStackTrace();
			}	
			
		}//end if
		
		return connexion;
	}//end getInstance()
	
}//end class
