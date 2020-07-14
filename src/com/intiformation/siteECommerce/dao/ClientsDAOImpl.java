package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Clients;
import com.intiformation.siteECommerce.modele.Utilisateur;

public class ClientsDAOImpl implements IClientsDAO {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public boolean add(Clients pClients) {
		try {

			ps = this.connection
					.prepareStatement(" INSERT INTO Clients (nom_Clients, adresse, email, telephone)" + " VALUES (?,?,?,?)");

			
			ps.setString(1, pClients.getNom_Client());
			ps.setString(2, pClients.getAdresse());
			ps.setString(3, pClients.getEmail());
			ps.setString(4, pClients.getTelephone());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'excéution de la méthode add() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}//end add

	@Override
	public boolean update(Clients pClients) {
		try {

			ps = this.connection.prepareStatement("UPDATE clients SET nom_Client=?, adresse=?, email=?, telephone=? WHERE id_Client=?");

			
			ps.setString(1, pClients.getNom_Client());
			ps.setString(2, pClients.getAdresse());
			ps.setString(3, pClients.getEmail());
			ps.setString(4, pClients.getTelephone());
			ps.setInt(5, pClients.getId_Client());
			

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CLientsDAOImpl) Erreur lors de l'excéution de la méthode update() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}//end update

	@Override
	public boolean delete(Integer id) {
		try {

			ps = this.connection.prepareStatement("DELETE FROM clients WHERE id_client=?");

			ps.setInt(1, id);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (UtilisateurDAOImpl) Erreur lors de l'exécution de la méthode delete() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
	}//end delete

	@Override
	public List<Clients> getAll() {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM Clients");

			rs = ps.executeQuery();

			List<Clients> listeClientsBDD = new ArrayList<>();
			Clients Clients = null;

			while (rs.next()) {

				Clients = new Clients(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));


				listeClientsBDD.add(Clients);

			} // end while

			return listeClientsBDD;

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
	public Clients getById(Integer id) {
		try {

			ps = this.connection.prepareStatement("SELECT * FROM clients WHERE id_Client=?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			Clients clients = null;

			while (rs.next()) {

				clients  = new Clients(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

			} // end while

			return clients ;

		} catch (SQLException e) {
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'excéution de la méthode getById() ...");
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
	}// end getById

	@Override
	public boolean isClientsExists(String pEmail, String pTele) {
		try {
			
			ps = this.connection.prepareStatement("SELECT COUNT(*) FROM clients WHERE email=? AND telephone=?");

			ps.setString(1, pEmail);
			ps.setString(2, pTele);

			rs = ps.executeQuery();

			rs.next();
			
			int verif = rs.getInt(1);

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'exécution de la méthode isClientsExists() ...");
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
	}// end isClientsExist

}
