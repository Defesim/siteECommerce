package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Panier;
import com.intiformation.siteECommerce.modele.Produit;

public class PanierDAOImpl implements IPanierDAO {
	
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * ne sert à rien
	 */
	@Override
	public boolean add(Panier t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * modifier seukement la quantité
	 */
	@Override
	public boolean update(Panier pPannier) {

try {
			
			String requeteUpdate = "UPDATE Panier SET quantite=? WHERE id_Produit=?";
			ps = this.connection.prepareStatement(requeteUpdate);
			
			ps.setInt(3, pPannier.getQuantite());			
			ps.setInt(8, pPannier.getId_Produit());
			
			int verifUpdate = ps.executeUpdate();
			return (verifUpdate == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> update() <-- : Erreur lors de la modif d'un panier dans PannierDAOImpl");
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
	 * supprimer du panier
	 */
	@Override
	public boolean delete(Integer id) {

try {
			
			String requeteSuppression = "DELETE FROM Panier WHERE id_Produit=?";
			ps = this.connection.prepareStatement(requeteSuppression);
			ps.setInt(1, id);
			
			int verifSuppression = ps.executeUpdate();
			return (verifSuppression == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> delete() <-- : Erreur lors de la suppresion d'un panier dans PanierDAOImpl");
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
	 * afficher la liste du panier
	 */
	@Override
	public List<Panier> getAll() {

try {
			
			String requeteGetAllProduits = "SELECT * FROM Panier";
			ps = this.connection.prepareStatement(requeteGetAllProduits);
			
			rs = ps.executeQuery();
			Panier Panier = null;
			List<Panier> listePanier = new ArrayList<>();
			
			while(rs.next()){
				
				int id_Produit = rs.getInt(1);
				String nom = rs.getString(2);
				double prix = rs.getDouble(3);
				int quantite =rs.getInt(4);
				
				
				// 4.4 création d'un objet hotel et ajout a la liste
				Panier = new Panier(id_Produit, nom, prix, quantite);
				listePanier.add(Panier);
				
			}//end while
			
			return listePanier;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getAll() <-- : Erreur lors de la récupération de la liste des Paniers dans PanierDAOImpl");
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
		
	}//end getAll

	/**
	 * ne sert à rien
	 */
	@Override
	public Panier getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean ajouterAuPanier(Integer pProduitIdPourPanier) {

		try {
			String requeteAjout = "insert into Panier select id_Produit, nom, prix, quantite from Produit where id_Produit = ?;";
			
			// 2. création de l'objet 'PreparedStatement' qui permet d'exécuter la requête et de récupérer le résultat
			ps = this.connection.prepareStatement(requeteAjout);
			ps.setInt(1, pProduitIdPourPanier);
			
			
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
	}//end addAuPanier
	

	@Override
	public boolean viderPanier(Panier panier) {
		// TODO Auto-generated method stub
		return false;
	}

}
