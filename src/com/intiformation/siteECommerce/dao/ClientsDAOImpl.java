package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;
import com.intiformation.siteECommerce.modele.Clients;
import com.intiformation.siteECommerce.modele.Utilisateur;

public class ClientsDAOImpl implements IClientsDAO {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public boolean add(Clients pClients) {
		try {

			ps = this.connection
					.prepareStatement(" INSERT INTO Clients (nom_Client, adresse, email, telephone)" + " VALUES (?,?,?,?)");

			
			ps.setString(1, pClients.getNom_Client());
			ps.setString(2, pClients.getAdresse());
			ps.setString(3, pClients.getEmail());
			ps.setString(4, pClients.getTelephone());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'exc�ution de la m�thode add() ...");
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
			System.out.println("... (CLientsDAOImpl) Erreur lors de l'exc�ution de la m�thode update() ...");
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
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'exc�ution de la m�thode getAll() ...");
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
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'exc�ution de la m�thode getById() ...");
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
			System.out.println("... (ClientsDAOImpl) Erreur lors de l'ex�cution de la m�thode isClientsExists() ...");
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
	
	@Override
	public List<Clients> getByRecherche(String motCle) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			motCle = "%"+motCle+"%";
			String requeteGetAllProduits = "select * from Clients WHERE nom_Client like ?";
			ps = this.connection.prepareStatement(requeteGetAllProduits);
			ps.setString(1, motCle);
			
			rs = ps.executeQuery();
			 
			
			
			Clients clients = null;
			List<Clients> listeClients = new ArrayList<>();
			
			while(rs.next()){
				
				int id_Client = rs.getInt(1);
				String nom_Client = rs.getString(2);
				String adresse = rs.getString(3);
				String email = rs.getString(4);
				String telephone = rs.getString(5);
				
				// 4.4 cr�ation d'un objet hotel et ajout a la liste
				clients = new Clients(id_Client, nom_Client, adresse, email, telephone);
				listeClients.add(clients);
				
			}//end while
			
			return listeClients;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getByRecherche() <-- : Erreur lors de la r�cup�ration de la liste des Produits dans ClientsDAOImpl");
			e.printStackTrace();
			
		} //end catch
		finally {
			// fermeture des ressources
			try {
				if (ps != null) {
					ps.close();
				}	
				if (rs != null) {
					rs.close();
				}	
			}
			catch(Exception e){
				e.printStackTrace();			
			}		
		}//end finally

		return null;
	}//end getByRecherche

}
