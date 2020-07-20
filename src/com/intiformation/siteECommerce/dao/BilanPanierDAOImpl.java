package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.BilanPanier;
import com.intiformation.siteECommerce.modele.Clients;
import com.intiformation.siteECommerce.modele.Panier;

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
	public boolean AjoutPanierDansBilanPanier(Panier pan, int IdCommande) {
		try {
			

			// 1 def du contenue de la requete SQL
			String requeteDelete = "INSERT INTO BilanPanier(IDBilanCommande ,id_Produit ,nom ,prix ,quantite) values (?, ?, ?, ?, ?)";

			// 2 def de l'objet 'PreparedSatement' pour envoyer la requete à partir de
			// l'objet connexion
			ps = this.connection.prepareStatement(requeteDelete);
			ps.setInt(1, IdCommande);
			ps.setInt(2, pan.getId_Produit());
			ps.setString(3, pan.getNom());
			ps.setDouble(4, pan.getPrix());
			ps.setInt(5, pan.getQuantite());

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
