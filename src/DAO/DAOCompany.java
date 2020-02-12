package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entite.Company;

/**
 * Classe d'accès aux données de l'objet Company.
 * Permets les verbes CRUD.
 * @author cyril
 *
 */
public class DAOCompany {
	private Connexion conn;
	
	public DAOCompany() {
		this.conn = new Connexion();
		
	}
	
	/**
	 * Persiste un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 */
	public boolean persisteCompany(int id,String nom) {
		this.conn = new Connexion();
        conn.connect();
        
        Boolean addBdd = false;
        
        String req = "INSERT INTO company (id,  name)" +
                "values(?, ?)";
        
        try {
            PreparedStatement psmt = conn.getConn().prepareStatement(req);

            psmt.setInt(1, id);
            psmt.setString(2, nom);
            psmt.executeUpdate();
            conn.close();
            addBdd = true;

        } catch (SQLException e) {
            conn.close();
	}
        return addBdd;

	}
	
	/**
	 * Supprime un element de "company" par Id.
	 * @author cyril
	 * @param Id
	 */
	public void deletecompany(int Id) {
		this.conn = new Connexion();
        conn.connect();
        
        String req = "DELETE FROM company WHERE id=?";
        
        try {
        	PreparedStatement statementSupresisoncompany = conn.getConn().prepareStatement(req);
        	statementSupresisoncompany.setInt(1,Id);
        	statementSupresisoncompany.executeUpdate();
        	statementSupresisoncompany.close();
            conn.close();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	

	/**
	 * Récupère un element de "company" par Id.
	 * 
	 * @param Id
	 * @return Company
	 */
	public Company getCompany(int Id) {
		this.conn = new Connexion();
		conn.connect();
		
		Company company = new Company();
		
		String req = " SELECT * FROM company WHERE id=?";
		try {
			PreparedStatement statementGetCompany = conn.getConn().prepareStatement(req);
			statementGetCompany.setInt(1,Id);
			ResultSet resDetailCompany = statementGetCompany.executeQuery();
			
			resDetailCompany.next();
			company.setId(resDetailCompany.getInt(1));
			company.setName(resDetailCompany.getString(2));
			
			conn.close();
			return company;

		} catch (Exception e) {
			e.printStackTrace();
			return company;

		}
		}
	
	
	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	 public void updateCompany(Company company) {
	        this.conn = new Connexion();
	        conn.connect();
	        
	        String sqlName = "UPDATE company " + "SET name = ? WHERE Id = ?";

	        Company comp = getCompany(company.getId());

	        try {

	            if (comp.getName() != company.getName()) {
	                PreparedStatement statementUpdatecompany = conn.getConn().prepareStatement(sqlName);
	                statementUpdatecompany.setString(2, company.getName());
	                statementUpdatecompany.executeUpdate();

	            }
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	} 
}
	 /**
	  * Interroge la BDD et retourne la liste de toutes les company.
	  * 
	  * @return List 
	  */
	 public List<Company> getallCompany(){

		 this.conn = new Connexion();
	     conn.connect();
	     
	     List<Company> companylist = new ArrayList<Company>();

	     String req = "SELECT * FROM company";
	     try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeCompany = statementSelectall.executeQuery(req);
            while(resListeCompany.next()){
                Company company = new Company();
                company.setId(resListeCompany.getInt(1));
                company.setName(resListeCompany.getString(2));

                companylist.add(company);
            }

	            statementSelectall.close();
	            conn.close();



	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return companylist;
	    }

}
