package persistence;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import service.ServiceCompany;

/**
 * Classe d'accès aux données de l'objet computer.
 * Permets les verbes CRUD.
 * @author cyril
 *
 */
public final class DAOComputer {
	
    private static volatile DAOComputer instance = null;
    
	private Connexion conn;
	private ServiceCompany servCompany;
	
    private static final String PERSISTE_COMPUTER = "INSERT INTO computer (id,  name, introduced, discontinued, company_id)" +
            "values(?, ?, ?, ?, ?)";
    private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";
	private static final String GET_COMPUTER = "SELECT * FROM computer "
            + "LEFT JOIN company ON company_id = company.id WHERE computer.id = ?;";
	private static final String GET_ALL_COMPUTER = "SELECT id, name, introduced , discontinued , company_id FROM computer LEFT JOIN company ON company_id = company.id";
    private static final String GET_PAGE_COMPUTER = "SELECT id, name, introduced , discontinued , company_id FROM computer FROM computer LEFT JOIN company ON company_id = company.id  LIMIT ?,?;";

    
	private DAOComputer() {
		this.conn = Connexion.getInstance();
		this.servCompany = ServiceCompany.getInstance();
	}
	
	 public final static DAOComputer getInstance() {

         if (DAOComputer.instance == null) {
            synchronized(DAOComputer.class) {
              if (DAOComputer.instance == null) {
            	  DAOComputer.instance = new DAOComputer();
              }
            }
         }
         return DAOComputer.instance;
     }
	
	/**
	 * Persiste un element de "computer" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 */
	public void persistecomputer(Computer computer) {
		this.conn = Connexion.getInstance();
        conn.connect();
        
        
        try {
            PreparedStatement statementPersisteComputer = conn.getConn().prepareStatement(PERSISTE_COMPUTER);

            statementPersisteComputer.setInt(1, computer.getId());
            statementPersisteComputer.setString(2, computer.getName());
            statementPersisteComputer.setTimestamp(3, Timestamp.valueOf(computer.getIntroduced()));
            statementPersisteComputer.setTimestamp(4, Timestamp.valueOf(computer.getDiscontinued()));
            statementPersisteComputer.setInt(5, computer.getCompany().getId());
            statementPersisteComputer.executeUpdate();
            statementPersisteComputer.close();

        } catch (SQLException e) {
			e.printStackTrace();

	}
        conn.close();


	}
	
	/**
	 * Supprime un element de "computer" par Id.
	 * @author cyril
	 * @param id
	 * @return 
	 */
	public void deletecomputer(int id) {
		this.conn = Connexion.getInstance();
        conn.connect();
        
        String statementDeleteComputer = "DELETE FROM computer WHERE id=?";
        
        try {
        	PreparedStatement statementSupresisoncomputer = conn.getConn().prepareStatement(statementDeleteComputer);
        	statementSupresisoncomputer.setInt(1,id);
        	statementSupresisoncomputer.executeUpdate();
        	statementSupresisoncomputer.close();
            conn.close();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Récupère un element de "computer" par Id.
	 * 
	 * @param id
	 * @return computer
	 */
	public Computer getComputer(int id) {
		this.conn = Connexion.getInstance();
		conn.connect();
		
		Computer computer = new Computer();

		try {
			PreparedStatement statementGetcomputer = conn.getConn().prepareStatement(GET_COMPUTER);
			statementGetcomputer.setInt(1,id);
			ResultSet resDetailcomputer = statementGetcomputer.executeQuery();
			resDetailcomputer.next();
        	computer = ComputerMapper.getInstance().getComputer(resDetailcomputer);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return computer;

		}
	
	
	/**
	 * Modifie un element la table "computer" élément par élément.
	 * 
	 * @param computer
	 */
	 public void updateComputer(Computer computer) {
	        this.conn = Connexion.getInstance();
	        conn.connect();

	        Computer comp = getComputer(computer.getId());

	        try {

	            if (comp.getName() != computer.getName()) {
	            	 String sqlName = "UPDATE computer " + "SET name = ? WHERE Id = ?";
	                PreparedStatement statementUpdatecomputer = conn.getConn().prepareStatement(sqlName);
	                statementUpdatecomputer.setString(2, computer.getName());
	                statementUpdatecomputer.executeUpdate();
	              
	            }
	            if (comp.getIntroduced() != computer.getIntroduced()) {
	            	 String sqlIntroduced = "UPDATE computer " + "SET  Introduced = ? WHERE Id = ?";
	                PreparedStatement statementUpdatecomputer = conn.getConn().prepareStatement(sqlIntroduced);
	                statementUpdatecomputer.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
	                statementUpdatecomputer.executeUpdate();
	              
	            }
	            if (comp.getDiscontinued() != computer.getDiscontinued()) {
	            	String sqlDiscontinued = "UPDATE computer " + "SET  Discontinued = ? WHERE Id = ?";
	                PreparedStatement statementUpdatecomputer = conn.getConn().prepareStatement(sqlDiscontinued);
	                statementUpdatecomputer.setTimestamp(2,  Timestamp.valueOf(computer.getDiscontinued()));
	                statementUpdatecomputer.executeUpdate();
	              
	            }
	            if (comp.getCompany().getId() != computer.getCompany().getId()) {
	            	String sqlIdCompagny = "UPDATE computer " + "SET  IdCompagny = ? WHERE Id = ?";
	                PreparedStatement statementUpdatecomputer = conn.getConn().prepareStatement(sqlIdCompagny);
	                statementUpdatecomputer.setInt(2, computer.getCompany().getId());
	                statementUpdatecomputer.executeUpdate();
	              
	            }
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	} 
}
	 /**
	  * Interroge la BDD et retourne la liste de tous les computers.
	  * @return List computer
	  */
	 public List<Computer> getAllComputer(){

		 this.conn = Connexion.getInstance();
	     conn.connect();
	     Company company = new Company();
	     
	     List<Computer> computerlist = new ArrayList<Computer>();

	     try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListecomputer = statementSelectall.executeQuery(GET_ALL_COMPUTER);
            while(resListecomputer.next()){
            	Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer);
                computerlist.add(computer);

            	}

	            statementSelectall.close();
	            conn.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return computerlist;
	    }
	 
	 /**
	  * Interroge la BDD et retourne la liste de tous les computers pagine.
	  * @return List computer
	  */
	 public List<Computer> getPageComputer(int offset, int number){

		 this.conn = Connexion.getInstance();
	     conn.connect();
	     
	     Company company = new Company();

	     
	     List<Computer> computerlist = new ArrayList<Computer>();

	     try {
            PreparedStatement statementSelecPage = conn.getConn().prepareStatement(GET_PAGE_COMPUTER);
            statementSelecPage.setInt(1, offset);
            statementSelecPage.setInt(2, number);
            ResultSet resListecomputer = statementSelecPage.executeQuery();
            statementSelecPage.close();
            while(resListecomputer.next()){
            	Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer);

                computerlist.add(computer);
            }

	            conn.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return computerlist;
	    }

}
