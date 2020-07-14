package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;
import com.intiformation.siteECommerce.modele.Produit;

/**
 * Impl�mentation de la couche DAO pour les cat�gories
 * @author Kevin
 *
 */
public class CategorieDAOImpl implements ICategorieDAO {

	/**
	 * permet d'ajouter une cat�gorie dans la base de donn�es
	 */
	@Override
	public boolean add(Categorie pCategorie) {
		PreparedStatement ps = null;
		
		try {
			String requeteAjout = "insert into Categorie (nom_Categorie, description) values (?, ?)";
			
			// 2. cr�ation de l'objet 'PreparedStatement' qui permet d'ex�cuter la requ�te et de r�cup�rer le r�sultat
			ps = this.connection.prepareStatement(requeteAjout);
			
			ps.setString(1, pCategorie.getNom_Categorie());
			ps.setString(2, pCategorie.getDescription());
			
			int verifAjout = ps.executeUpdate();

			return (verifAjout == 1)?true:false;
			
		}//end try
		
		catch (SQLException e) {
	
			System.out.println("--> add() <-- : Erreur lors de l'ajout d'une cat�gorie dans CategorieDAOImpl");
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
	 * permet de modifier une cat�gorie dans la base de donn�es
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
			
			System.out.println("--> update() <-- : Erreur lors de la modif d'une cat�gorie dans CategorieDAOImpl");
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
	 * N'est pas utilis�e puisque les cat�gories n'ont pas d'ID
	 */
	@Override
	public boolean delete(Integer id) {
		return false;
	}//end delete()
	
	/**
	 * permet de supprimer une cat�gorie de la base de donn�es
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
			
			System.out.println("--> delete() <-- : Erreur lors de la suppresion d'une cat�gorie dans CategorieDAOImpl");
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
	 * permet de r�cup�rer la liste des cat�gories depuis la BDD
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
				
				// 4.4 cr�ation d'un objet hotel et ajout a la liste
				categorie = new Categorie(nom_Categorie, description);
				listeCategories.add(categorie);
				
			}//end while
			
			return listeCategories;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getAll() <-- : Erreur lors de la r�cup�ration de la liste des Cat�gories dans CategorieDAOImpl");
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
	 * N'est pas utilis�e puisque les cat�gories n'ont pas d'ID
	 */
	@Override
	public Categorie getById(Integer id) {
		return null;
	}//end getById()

	/**
	 * permet de r�cup�rer une cat�gorie a partir de son nom
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
			
			System.out.println("--> getByName() <-- : Erreur lors de la r�cup�ration d'une Cat�gorie par son nom dans CategorieDAOImpl");
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
