package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.BilanPanier;
import com.intiformation.siteECommerce.modele.Clients;

public class BilanPanierDAOImpl implements IBilanPanierDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public boolean add(BilanPanier t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(BilanPanier t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BilanPanier> getAll() {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM BilanPanier");

			rs = ps.executeQuery();

			List<BilanPanier> listeBilanPanierBDD = new ArrayList<>();
			BilanPanier BilanPanier = null;

			while (rs.next()) {

				BilanPanier = new BilanPanier(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5));


				listeBilanPanierBDD.add(BilanPanier);

			} // end while

			return listeBilanPanierBDD;

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
	public BilanPanier getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean AjoutPanierDansBilanPanier() {
		try {
			

			// 1 def du contenue de la requete SQL
			String requeteDelete = "INSERT INTO BilanPanier select (SELECT id_BilanCommande FROM BilanCommande), Panier.* from Panier;";

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
