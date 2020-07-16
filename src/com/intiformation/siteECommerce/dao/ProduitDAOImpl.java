package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Produit;

/**
 * Implémentation de la couche DAO pour les produits
 * @author Kevin
 *
 */
public class ProduitDAOImpl implements IProduitDAO{

	/**
	 * Permet d'ajouter un produit dans la base de données
	 */
	@Override
	public boolean add(Produit pProduit) {
		PreparedStatement ps = null;
		
		try {
			String requeteAjout = "INSERT INTO Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values( ? , ? , ? , ? , ? , ? , ? )";
			
			// 2. création de l'objet 'PreparedStatement' qui permet d'exécuter la requête et de récupérer le résultat
			ps = this.connection.prepareStatement(requeteAjout);
			
			ps.setString(1, pProduit.getNom());
			ps.setDouble(2, pProduit.getPrix());
			ps.setInt(3, pProduit.getQuantite());
			ps.setString(4, pProduit.getDescription());
			ps.setBoolean(5, pProduit.isSelectionner());
			ps.setString(6, pProduit.getPhoto());
			ps.setString(7, pProduit.getCategorie_NOM());
			
			int verifAjout = ps.executeUpdate();

			return (verifAjout == 1)?true:false;
			
		}//end try
		
		catch (SQLException e) {
	
			System.out.println("--> add() <-- : Erreur lors de l'ajout d'un produit dans ProduitDAOImpl");
			e.printStackTrace();
			
		}//end catch
		finally {
			// fermeture des ressources
			try {
				if (ps != null) {
					ps.close();
				}

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}//end finally
		return false;
	}//end add()

	/**
	 * Permet de modifier un produit dans la base de données
	 */
	@Override
	public boolean update(Produit pProduit) {
		PreparedStatement ps = null;
		
		try {
			
			String requeteUpdate = "UPDATE Produit SET nom=?, prix=?, quantite=?, description=?, selectionner=?, photo=?, categorie_NOM=? WHERE id_Produit=?";
			ps = this.connection.prepareStatement(requeteUpdate);
			ps.setString(1, pProduit.getNom());
			ps.setDouble(2, pProduit.getPrix());
			ps.setInt(3, pProduit.getQuantite());
			ps.setString(4, pProduit.getDescription());
			ps.setBoolean(5, pProduit.isSelectionner());
			ps.setString(6, pProduit.getPhoto());
			ps.setString(7, pProduit.getCategorie_NOM());
			ps.setInt(8, pProduit.getId_Produit());
			
			int verifUpdate = ps.executeUpdate();
			return (verifUpdate == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> update() <-- : Erreur lors de la modif d'un produit dans ProduitDAOImpl");
			e.printStackTrace();
			
		} //end catch
		finally {
			// fermeture des ressources
			try {
				if (ps != null) {
					ps.close();
				}		
			}
			catch(Exception e){
				e.printStackTrace();			
			}		
		}//end finally

		return false;
	}//end update()

	/**
	 * Permet de supprimer un produit dans la base de données
	 */
	@Override
	public boolean delete(Integer id) {
		PreparedStatement ps = null;
		
		try {
			
			String requeteSuppression = "DELETE FROM Produit WHERE id_Produit=?";
			ps = this.connection.prepareStatement(requeteSuppression);
			ps.setInt(1, id);
			
			int verifSuppression = ps.executeUpdate();
			return (verifSuppression == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> delete() <-- : Erreur lors de la suppresion d'un produit dans ProduitDAOImpl");
			e.printStackTrace();
			
		} //end catch
		finally {
			// fermeture des ressources
			try {
				if (ps != null) {
					ps.close();
				}		
			}
			catch(Exception e){		
				e.printStackTrace();		
			}
						
		}//end finally
		return false;
	}//end delete()

	/**
	 * Permet de récupérer la liste des produits dans la base de données par ordre décroissant
	 */
	@Override
	public List<Produit> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String requeteGetAllProduits = "SELECT * FROM Produit ORDER BY id_Produit DESC";
			ps = this.connection.prepareStatement(requeteGetAllProduits);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeProduits = new ArrayList<>();
			
			while(rs.next()){
				
				int id_Produit = rs.getInt(1);
				String nom = rs.getString(2);
				double prix = rs.getDouble(3);
				int quantite =rs.getInt(4);
				String description = rs.getString(5);
				boolean selectionner = rs.getBoolean(6);
				String photo = rs.getString(7);
				String categorie_NOM = rs.getString(8);
				
				// 4.4 création d'un objet hotel et ajout a la liste
				produit = new Produit(id_Produit, nom, prix, quantite, description, selectionner, photo, categorie_NOM);
				listeProduits.add(produit);
				
			}//end while
			
			return listeProduits;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getAll() <-- : Erreur lors de la récupération de la liste des Produits dans ProduitDAOImpl");
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
	}//end getAll()

	/**
	 * Permet de récupérer un produit depuis la BDD par son ID 
	 */
	@Override
	public Produit getById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String requeteGetByIdProduit = "SELECT * FROM Produit WHERE id_Produit = ?";
			ps = this.connection.prepareStatement(requeteGetByIdProduit);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			Produit produit = null;
			
			rs.next();
				
			int id_Produit = rs.getInt(1);
			String nom = rs.getString(2);
			double prix = rs.getDouble(3);
			int quantite =rs.getInt(4);
			String description = rs.getString(5);
			boolean selectionner = rs.getBoolean(6);
			String photo = rs.getString(7);
			String categorie_NOM = rs.getString(8);
				
			produit = new Produit(id_Produit, nom, prix, quantite, description, selectionner, photo, categorie_NOM);
			
			return produit;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getByID() <-- : Erreur lors de la récupération d'un Produit par son id dans ProduitDAOImpl");
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
	}//end getById()

	@Override
	public List<Produit> getByCategorie(String NomCategorie) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String requeteGetAllProduits = "SELECT * FROM Produit where categorie_NOM = ? ORDER BY id_Produit DESC";
			ps = this.connection.prepareStatement(requeteGetAllProduits);
			ps.setString(1, NomCategorie);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeProduits = new ArrayList<>();
			
			while(rs.next()){
				
				int id_Produit = rs.getInt(1);
				String nom = rs.getString(2);
				double prix = rs.getDouble(3);
				int quantite =rs.getInt(4);
				String description = rs.getString(5);
				boolean selectionner = rs.getBoolean(6);
				String photo = rs.getString(7);
				String categorie_NOM = rs.getString(8);
				
				// 4.4 création d'un objet hotel et ajout a la liste
				produit = new Produit(id_Produit, nom, prix, quantite, description, selectionner, photo, categorie_NOM);
				listeProduits.add(produit);
				
			}//end while
			
			return listeProduits;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getByCategorie() <-- : Erreur lors de la récupération de la liste des Produits dans ProduitDAOImpl");
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
	}// end getByCategorie

	@Override
	public boolean ModifierQuantite(int quantiteRetirer, int IdProduit) {
		PreparedStatement ps = null;
		
		try {
			
			String requeteUpdate = "update Produit set quantite = quantite - ? where id_Produit = ?;";
			ps = this.connection.prepareStatement(requeteUpdate);
			ps.setInt(1, quantiteRetirer);
			ps.setInt(2, IdProduit);
			
			
			int verifUpdate = ps.executeUpdate();
			return (verifUpdate == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> update() <-- : Erreur lors de la modif d'un produit dans ProduitDAOImpl");
			e.printStackTrace();
			
		} //end catch
		finally {
			// fermeture des ressources
			try {
				if (ps != null) {
					ps.close();
				}		
			}
			catch(Exception e){
				e.printStackTrace();			
			}		
		}//end finally

		return false;
	}//end updateQuantite

	@Override
	public List<Produit> getByRecherche(String motCle) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			motCle = "%"+motCle+"%";
			String requeteGetAllProduits = "select * from Produit WHERE nom like ?";
			System.out.println(motCle);
			ps = this.connection.prepareStatement(requeteGetAllProduits);
			ps.setString(1, motCle);
			
			rs = ps.executeQuery();
			Produit produit = null;
			List<Produit> listeProduits = new ArrayList<>();
			
			while(rs.next()){
				
				int id_Produit = rs.getInt(1);
				String nom = rs.getString(2);
				double prix = rs.getDouble(3);
				int quantite =rs.getInt(4);
				String description = rs.getString(5);
				boolean selectionner = rs.getBoolean(6);
				String photo = rs.getString(7);
				String categorie_NOM = rs.getString(8);
				
				// 4.4 création d'un objet hotel et ajout a la liste
				produit = new Produit(id_Produit, nom, prix, quantite, description, selectionner, photo, categorie_NOM);
				listeProduits.add(produit);
				
			}//end while
			
			return listeProduits;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getByRecherche() <-- : Erreur lors de la récupération de la liste des Produits dans ProduitDAOImpl");
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
	}// end getByRecherche
	

}//end class
