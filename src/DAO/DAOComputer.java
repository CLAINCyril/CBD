package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entite.Computer;

/**
 * Classe d'accès aux données de l'objet computer.
 * Permets les verbes CRUD.
 * @author cyril
 *
 */
public class DAOComputer {
	private Connexion conn;
	
	public DAOComputer() {
		this.conn = new Connexion();
		
	}
	
	/**
	 * Persiste un element de "computer" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 */
	public boolean persistecomputer(Computer computer) {
		this.conn = new Connexion();
        conn.connect();
        
        Boolean addBdd = false;
        
        String req = "INSERT INTO computer (id,  name, introduced, discontinued, company_id)" +
                "values(?, ?, ?, ?, ?)";
        
        try {
        	System.out.println("je rentre try");
            PreparedStatement psmt = conn.getConn().prepareStatement(req);
        	System.out.println("je rentre set");

            psmt.setInt(1, computer.getId());
            psmt.setString(2, computer.getName());
            psmt.setTimestamp(3, Timestamp.valueOf(computer.getIntroduced()));
            psmt.setTimestamp(4, Timestamp.valueOf(computer.getDiscontinued()));
            psmt.setInt(5, computer.getIdCompagny());
            
            System.out.println("fin set");
            psmt.executeUpdate();
            psmt.close();
            System.out.println("fin set");
            conn.close();
            addBdd = true;

        } catch (SQLException e) {
			e.printStackTrace();

	}
        return addBdd;

	}
	
	/**
	 * Supprime un element de "computer" par Id.
	 * @author cyril
	 * @param Id
	 */
	public void deletecomputer(int Id) {
		this.conn = new Connexion();
        conn.connect();
        
        String req = "DELETE FROM computer WHERE id=?";
        
        try {
        	PreparedStatement statementSupresisoncomputer = conn.getConn().prepareStatement(req);
        	statementSupresisoncomputer.setInt(1,Id);
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
	 * @param Id
	 * @return computer
	 */
	public Computer getcomputer(int Id) {
		this.conn = new Connexion();
		conn.connect();
		
		Computer computer = new Computer();
		
		String req = " SELECT * FROM computer WHERE id=?";
		try {
			PreparedStatement statementGetcomputer = conn.getConn().prepareStatement(req);
			statementGetcomputer.setInt(1,Id);
			ResultSet resDetailcomputer = statementGetcomputer.executeQuery();
			
			resDetailcomputer.next();
			computer.setId(resDetailcomputer.getInt(1));
			computer.setName(resDetailcomputer.getString(2));
			computer.setIntroduced(resDetailcomputer.getTimestamp(3).toLocalDateTime());
			computer.setDiscontinued(resDetailcomputer.getTimestamp(4).toLocalDateTime());
			computer.setId(resDetailcomputer.getInt(5));

			
			conn.close();
			return computer;

		} catch (Exception e) {
			e.printStackTrace();
			return computer;

		}
		}
	
	
	/**
	 * Modifie un element la table "computer".
	 * 
	 * @param computer
	 */
	 public void updatecomputer(Computer computer) {
	        this.conn = new Connexion();
	        conn.connect();
	        

	        Computer comp = getcomputer(computer.getId());

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
	            if (comp.getIdCompagny() != computer.getIdCompagny()) {
	            	String sqlIdCompagny = "UPDATE computer " + "SET  IdCompagny = ? WHERE Id = ?";
	                PreparedStatement statementUpdatecomputer = conn.getConn().prepareStatement(sqlIdCompagny);
	                statementUpdatecomputer.setInt(2, computer.getIdCompagny());
	                statementUpdatecomputer.executeUpdate();
	              
	            }
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	} 
}
	 
	 public List<Computer> getallcomputer(){

		 this.conn = new Connexion();
	     conn.connect();
	     
	     List<Computer> computerlist = new ArrayList<Computer>();

	     String req = "SELECT * FROM computer";
	     try {
            Statement statementSelectall = conn.getConn().createStatement();
            ResultSet resListecomputer = statementSelectall.executeQuery(req);
            while(resListecomputer.next()){
            	Computer computer = new Computer();
                computer.setId(resListecomputer.getInt(1));
                computer.setName(resListecomputer.getString(2));
                computer.setIntroduced(resListecomputer.getTimestamp(3).toLocalDateTime());
                computer.setDiscontinued(resListecomputer.getTimestamp(4).toLocalDateTime());
                computer.setIdCompagny(resListecomputer.getInt(5));
                
                computerlist.add(computer);
            }

	            statementSelectall.close();
	            conn.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return computerlist;
	    }

}
