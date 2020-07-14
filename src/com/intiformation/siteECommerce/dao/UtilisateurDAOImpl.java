package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.intiformation.siteECommerce.modele.Utilisateur;

/**
 * 
 * @author Adrien
 *
 */

public class UtilisateurDAOImpl implements IUtilisateurDAO {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public boolean add(Utilisateur pUser) {
		try {

			ps = this.connection
					.prepareStatement(" INSERT INTO utilisateur (identifiant, mot_de_passe)" + " VALUES (?,?)");

			
			ps.setString(1, pUser.getIdentifiant());
			ps.setString(2, pUser.getMot_de_passe());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'exc�ution de la m�thode add() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	@Override
	public boolean update(Utilisateur pUser) {
		try {

			ps = this.connection.prepareStatement("UPDATE utilisateur SET identifiant=?, mot_de_passe=? WHERE id_utilisateur=?");

			
			ps.setString(1, pUser.getIdentifiant());
			ps.setString(2, pUser.getMot_de_passe());
			ps.setInt(3, pUser.getId_Utilisateur());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'exc�ution de la m�thode update() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	@Override
	public boolean delete(Integer id) {

		try {

			ps = this.connection.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur=?");

			ps.setInt(1, id);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'ex�cution de la m�thode delete() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	@Override
	public List<Utilisateur> getAll() {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM utilisateur");

			rs = ps.executeQuery();

			List<Utilisateur> listeUtilisateurBDD = new ArrayList<>();
			Utilisateur utilisateur = null;

			while (rs.next()) {

				utilisateur = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3));

				listeUtilisateurBDD.add(utilisateur);

			} // end while

			return listeUtilisateurBDD;

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'exc�ution de la m�thode getAll() ...");
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

	}

	@Override
	public Utilisateur getById(Integer id) {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM utilisateur WHERE id_Utilisateur=?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			Utilisateur utilisateur = null;

			while (rs.next()) {

				utilisateur  = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3));

			} // end while

			return utilisateur ;

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'exc�ution de la m�thode getById() ...");
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
	}

	@Override
	public boolean isUtilisateurExists(String pMail, String pMdp) {
		try {
						
			ps = this.connection.prepareStatement("SELECT COUNT(*) FROM utilisateur WHERE identifiant=? AND mot_de_passe=?");

			ps.setString(1, pMail);
			ps.setString(2, pMdp);

			rs = ps.executeQuery();

			rs.next();
			
			int verif = rs.getInt(1);

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'ex�cution de la m�thode isUtilisateurExists() ...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}

	
	
}
