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
public final class DAOCompany {
	private Connexion conn;
	
    private static volatile DAOCompany instance = null;
	
	private DAOCompany() {
		this.conn = new Connexion();
		
	}
	
	
	public final static DAOCompany getInstance() {
        if (DAOCompany.instance == null) {
           synchronized(DAOCompany.class) {
             if (DAOCompany.instance == null) {
            	 DAOCompany.instance = new DAOCompany();
             }
           }
        }
        return DAOCompany.instance;
    }
	/**
	 * Persiste un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 */
	public boolean persisteCompany(Company company) {
		this.conn = new Connexion();
        conn.connect();
        
        Boolean addBdd = false;
        
        String req = "INSERT INTO company (id,  name)" +
                "values(?, ?)";
        
        try {
            PreparedStatement psmt = conn.getConn().prepareStatement(req);

            psmt.setInt(1, company.getId());
            psmt.setString(2, company.getName());
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
	public boolean deletecompany(int Id) {
		this.conn = new Connexion();
        conn.connect();
        
        boolean res = false;
        String req = "DELETE FROM company WHERE id=?";
        
        try {
        	PreparedStatement statementSupresisoncompany = conn.getConn().prepareStatement(req);
        	statementSupresisoncompany.setInt(1,Id);
        	statementSupresisoncompany.executeUpdate();
        	statementSupresisoncompany.close();
            conn.close();
            res = true;
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        return res;
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
