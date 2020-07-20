package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.BilanCommande;
import com.intiformation.siteECommerce.modele.Clients;

public class BilanCommandeDAOImpl implements IBilanCommandeDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public boolean add(BilanCommande t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(BilanCommande t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BilanCommande> getAll() {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM BilanCommande");

			rs = ps.executeQuery();

			List<BilanCommande> listeBilanCommandeBDD = new ArrayList<>();
			BilanCommande BilanCommande = null;

			while (rs.next()) {

				BilanCommande = new BilanCommande(rs.getInt(1), rs.getDouble(2), rs.getString(3));


				listeBilanCommandeBDD.add(BilanCommande);

			} // end while

			return listeBilanCommandeBDD;

		} catch (SQLException e) {
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'excéution de la méthode getAll() ...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return null;

	}//end getall

	@Override
	public BilanCommande getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getLastId() {
		try {
			

			// 1 def du contenue de la requete SQL
			String requeteDelete = "select max(id_BilanCommande) from BilanCommande;";

			// 2 def de l'objet 'PreparedSatement' pour envoyer la requete à partir de
			// l'objet connexion
			ps = this.connection.prepareStatement(requeteDelete);

			

			// 4 envoie de la requete + execution + recup resultat
			rs = ps.executeQuery();
			
			rs.next();
			
			// 5 renvoi du resultat
			return (rs.getInt(1));

		} catch (SQLException e) {
			System.out.println("erreur lors de l'execution de la méthode PanierDansCommande de commande");
			e.printStackTrace();
		} finally {
			// 6 fermeture des ressources

			try {

				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end catch

		} // end finnaly
		return 0;


	}// PanierDansCommande

	@Override
	public boolean AjouterCommandeDansBilanCommande() {
		try {
			

			// 1 def du contenue de la requete SQL
			String requeteDelete = "insert into BilanCommande (prixtotale)\r\n" + 
					"SELECT prixTotale\r\n" + 
					"FROM Commande;";

			// 2 def de l'objet 'PreparedSatement' pour envoyer la requete à partir de
			// l'objet connexion
			ps = this.connection.prepareStatement(requeteDelete);

			

			// 4 envoie de la requete + execution + recup resultat
			int verifDelete = ps.executeUpdate();
			
			// 5 renvoi du resultat
			return (verifDelete == 1);

		} catch (SQLException e) {
			System.out.println("erreur lors de l'execution de la méthode PanierDansCommande de commande");
			e.printStackTrace();
		} finally {
			// 6 fermeture des ressources

			try {

				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end catch
		} // end finnaly

		return false;

	}// PanierDansCommande

}//end class
