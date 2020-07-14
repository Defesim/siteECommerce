package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;
import com.intiformation.siteECommerce.modele.Produit;

/**
 * Implémentation de la couche DAO pour les catégories
 * @author Kevin
 *
 */
public class CategorieDAOImpl implements ICategorieDAO {

	/**
	 * permet d'ajouter une catégorie dans la base de données
	 */
	@Override
	public boolean add(Categorie pCategorie) {
		PreparedStatement ps = null;
		
		try {
			String requeteAjout = "insert into Categorie (nom_Categorie, description) values (?, ?)";
			
			// 2. création de l'objet 'PreparedStatement' qui permet d'exécuter la requête et de récupérer le résultat
			ps = this.connection.prepareStatement(requeteAjout);
			
			ps.setString(1, pCategorie.getNom_Categorie());
			ps.setString(2, pCategorie.getDescription());
			
			int verifAjout = ps.executeUpdate();

			return (verifAjout == 1)?true:false;
			
		}//end try
		
		catch (SQLException e) {
	
			System.out.println("--> add() <-- : Erreur lors de l'ajout d'une catégorie dans CategorieDAOImpl");
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
	 * permet de modifier une catégorie dans la base de données
	 */
	@Override
	public boolean update(Categorie pCategorie) {
		PreparedStatement ps = null;
		
		try {
			
			String requeteUpdate = "UPDATE Categorie SET description=? WHERE nom_Categorie=?";
			ps = this.connection.prepareStatement(requeteUpdate);
			ps.setString(1, pCategorie.getNom_Categorie());
			ps.setString(2, pCategorie.getDescription());

			
			int verifUpdate = ps.executeUpdate();
			return (verifUpdate == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> update() <-- : Erreur lors de la modif d'une catégorie dans CategorieDAOImpl");
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
	 * N'est pas utilisée puisque les catégories n'ont pas d'ID
	 */
	@Override
	public boolean delete(Integer id) {
		return false;
	}//end delete()
	
	/**
	 * permet de supprimer une catégorie de la base de données
	 */
	@Override
	public boolean delete(String name) {
		PreparedStatement ps = null;
		
		try {
			
			String requeteSuppression = "DELETE FROM Categorie WHERE nom_Categorie=?";
			ps = this.connection.prepareStatement(requeteSuppression);
			ps.setString(1, name);
			
			int verifSuppression = ps.executeUpdate();
			return (verifSuppression == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> delete() <-- : Erreur lors de la suppresion d'une catégorie dans CategorieDAOImpl");
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
	 * permet de récupérer la liste des catégories depuis la BDD
	 */
	@Override
	public List<Categorie> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String requeteGetAllCategories = "SELECT * FROM Categorie";
			ps = this.connection.prepareStatement(requeteGetAllCategories);
			
			rs = ps.executeQuery();
			Categorie categorie = null;
			List<Categorie> listeCategories = new ArrayList<>();
			
			while(rs.next()){
				
				String nom_Categorie = rs.getString(1);
				String description = rs.getString(2);
				
				// 4.4 création d'un objet hotel et ajout a la liste
				categorie = new Categorie(nom_Categorie, description);
				listeCategories.add(categorie);
				
			}//end while
			
			return listeCategories;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getAll() <-- : Erreur lors de la récupération de la liste des Catégories dans CategorieDAOImpl");
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
	 * N'est pas utilisée puisque les catégories n'ont pas d'ID
	 */
	@Override
	public Categorie getById(Integer id) {
		return null;
	}//end getById()

	/**
	 * permet de récupérer une catégorie a partir de son nom
	 */
	@Override
	public Categorie getByName(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String requeteGetByIdProduit = "SELECT * FROM Categorie WHERE nom_Categorie = ?";
			ps = this.connection.prepareStatement(requeteGetByIdProduit);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			Categorie categorie = null;
			
			rs.next();
				
			String nom_Categorie = rs.getString(1);
			String description = rs.getString(2);
				
			categorie = new Categorie(nom_Categorie, description);
			
			return categorie;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getByName() <-- : Erreur lors de la récupération d'une Catégorie par son nom dans CategorieDAOImpl");
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
	}//end getByName()

}//end class
