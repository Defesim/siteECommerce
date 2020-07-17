package com.intiformation.siteECommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.siteECommerce.modele.Categorie;
import com.intiformation.siteECommerce.modele.Commande;
import com.intiformation.siteECommerce.modele.Commande;

public class CommandeDAOImpl implements ICommandeDAO {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * laisser vide
	 */
	@Override
	public boolean add(Commande t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * laisser vide
	 */
	@Override
	public boolean update(Commande t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
try {
			
			String requeteSuppression = "DELETE FROM Commande WHERE id_Command=?";
			ps = this.connection.prepareStatement(requeteSuppression);
			ps.setInt(1, id);
			
			int verifSuppression = ps.executeUpdate();
			return (verifSuppression == 1)?true:false;
			
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> delete() <-- : Erreur lors de la suppresion d'un panier dans CommandeDAOImpl");
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

	@Override
	public List<Commande> getAll() {
try {
			
			String requeteGetAllCommande = "SELECT * FROM Commande";
			ps = this.connection.prepareStatement(requeteGetAllCommande);
			
			rs = ps.executeQuery();
			Commande Commande = null;
			List<Commande> listeCommande = new ArrayList<>();
			
			while(rs.next()){
				
				double prixTotale = rs.getDouble(1);
				int id_Commande = rs.getInt(2);
				String date =rs.getString(3);
				
				
				// 4.4 création d'un objet hotel et ajout a la liste
				Commande = new Commande(id_Commande, prixTotale, date);
				listeCommande.add(Commande);
				
			}//end while
			
			return listeCommande;
		} //end try
		catch (SQLException e) {
			
			System.out.println("--> getAll() <-- : Erreur lors de la récupération de la liste des Commandes dans CommandeDAOImpl");
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
	 * laisser vide
	 */
	@Override
	public Commande getById(Integer id) {
try {
			
			String requeteGetByIdProduit = "SELECT * FROM Commande WHERE id_Command = ?";
			ps = this.connection.prepareStatement(requeteGetByIdProduit);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			Commande commande = null;
			
			rs.next();
				
			double prixtotale = rs.getDouble(1);
			String date = rs.getString(3);
				
			commande = new Commande(prixtotale, date);
			
			return commande;
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

	@Override
	public boolean PanierDansCommande() {
		try {

			// 1 def du contenue de la requete SQL
			String requeteDelete = "insert into Commande (prixTotale)\r\n" + 
					"SELECT SUM(prix * quantite)\r\n" + 
					"FROM Panier;";

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
	
	@Override
	public boolean ViderCommande() {
		try {

			// 1 def du contenue de la requete SQL
			String requeteDelete = "truncate table Commande;";

			// 2 def de l'objet 'PreparedSatement' pour envoyer la requete à partir de
			// l'objet connexion
			ps = this.connection.prepareStatement(requeteDelete);

			

			// 4 envoie de la requete + execution + recup resultat
			int verifDelete = ps.executeUpdate();

			// 5 renvoi du resultat
			return (verifDelete == 1);

		} catch (SQLException e) {
			System.out.println("erreur lors de l'execution de la méthode delete de l'hotel");
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

	}// ViderCommande

	@Override
	public boolean DetruireView() {
		try {

			// 1 def du contenue de la requete SQL
			String requeteDelete = "drop view TotaleCommande;";

			// 2 def de l'objet 'PreparedSatement' pour envoyer la requete à partir de
			// l'objet connexion
			ps = this.connection.prepareStatement(requeteDelete);

			

			// 4 envoie de la requete + execution + recup resultat
			int verifDelete = ps.executeUpdate();

			// 5 renvoi du resultat
			return (verifDelete == 1);

		} catch (SQLException e) {
			System.out.println("erreur lors de l'execution de la méthode destructionView");
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

	}// ViderCommande

}
