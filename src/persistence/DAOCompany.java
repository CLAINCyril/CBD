package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mapper.CompanyMapper;
import modele.Company;

/**
 * Classe d'accès aux données de l'objet Company.
 * Permets les verbes CRUD.
 * @author cyril
 *
 */
public final class DAOCompany {
	private Connexion conn;
	
    private static volatile DAOCompany instance = null;
    private static final String PERSISTE_COMPANY = "INSERT INTO company (name)"+" values( ?)";
    private static final String DELETE_COMPANY = "DELETE FROM company WHERE id=?";
    private static final String GET_By_ID =" SELECT id,name FROM company WHERE id=?";
    private static final String UPDATE_COMPANY = "UPDATE company " + "SET name = ? WHERE Id = ?";
    private static final String SELECT_ALL_COMPANY = "SELECT id,name FROM company";
	
	private DAOCompany() {
		this.conn = Connexion.getInstance();	
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
	public void persisteCompany(Company company) {
		this.conn = Connexion.getInstance();
        conn.connect();
        
        try {
            PreparedStatement statementPersisteCompany = conn.getConn().prepareStatement(PERSISTE_COMPANY);
            statementPersisteCompany.setString(1, company.getName());
            statementPersisteCompany.executeUpdate();
            statementPersisteCompany.close();
            conn.close();

        } catch (SQLException e) {
            conn.close();
	}
	}
	
	
	/**
	 * Supprime un element de "company" par Id.
	 * @author cyril
	 * @param Id
	 */
	public void deletecompany(int Id) {
		this.conn = Connexion.getInstance();
        conn.connect();
        
        boolean res = false;
        
        try {
        	PreparedStatement statementSupresisoncompany = conn.getConn().prepareStatement(DELETE_COMPANY);
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
		this.conn = Connexion.getInstance();
		conn.connect();
		
		Company company = new Company();
		
		try {
			PreparedStatement statementGetCompany = conn.getConn().prepareStatement(GET_By_ID);
			statementGetCompany.setInt(1,Id);
			ResultSet resDetailCompany = statementGetCompany.executeQuery();
			statementGetCompany.close();
			resDetailCompany.next();
			company = CompanyMapper.getInstance().getCompany(resDetailCompany);
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return company;

		}
	
	
	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	 public void updateCompany(Company company) {
	        this.conn = Connexion.getInstance();
	        conn.connect();
	        

	        Company comp = getCompany(company.getId());

	        try {

	            if (comp.getName() != company.getName()) {
	                PreparedStatement statementUpdatecompany = conn.getConn().prepareStatement(UPDATE_COMPANY);
	                statementUpdatecompany.setString(1, company.getName());
	                statementUpdatecompany.executeUpdate();
	                statementUpdatecompany.close();

	            }
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	        }
	        conn.close();
}
	 /**
	  * Interroge la BDD et retourne la liste de toutes les company.
	  * 
	  * @return List 
	  */
	 public List<Company> getAllCompany(){

		 this.conn = Connexion.getInstance();
	     conn.connect();
	     
	     List<Company> companylist = new ArrayList<Company>();

	     try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListeCompany = statementSelectall.executeQuery(SELECT_ALL_COMPANY);
            while(resListeCompany.next()){

                companylist.add(CompanyMapper.getInstance().getCompany(resListeCompany));
            }

            resListeCompany.close();


	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        conn.close();
	        return companylist;
	        
	    }
	 /**
	  * Interroge la BDD et retourne la liste de toutes les company pagine.
	  * 
	  * @param int 
	  * @param int
	  * @return List 
	  */
	 public List<Company> getPageCompany(int offset, int number){

		 this.conn = Connexion.getInstance();
	     conn.connect();
	     
	     List<Company> companylist = new ArrayList<Company>();

	     String req = "SELECT * FROM company LIMIT ?,? ";
	     try {
            PreparedStatement statementSelectPage = conn.getConn().prepareStatement(req);
            statementSelectPage.setInt(1, offset);
            statementSelectPage.setInt(2, number);
            ResultSet resListeCompany = statementSelectPage.executeQuery();
      
            while(resListeCompany.next()){
                companylist.add(CompanyMapper.getInstance().getCompany(resListeCompany));
            }

            statementSelectPage.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        conn.close();
	        return companylist;
	    }

}
