package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            PreparedStatement psmt = conn.getConn().prepareStatement(req);

            psmt.setInt(1, computer.getId());
            psmt.setString(2, computer.getName());
            psmt.setDate(3, computer.getIntroduced());
            psmt.setDate(4, computer.getDicontinued());
            psmt.setInt(5, computer.getIdCompagny());
            psmt.executeUpdate();
            conn.close();
            addBdd = true;

        } catch (SQLException e) {
            conn.close();
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
			computer.setIntroduced(resDetailcomputer.getDate(3));
			computer.setDicontinued(resDetailcomputer.getDate(4));
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
	        
	        String sqlName = "UPDATE computer " + "SET name = ? WHERE NUMETUDIANT = ?";

	        Computer comp = getcomputer(computer.getId());

	        try {

	            if (comp.getName() != computer.getName()) {
	                PreparedStatement statementUpdatecomputer = conn.getConn().prepareStatement(sqlName);
	                statementUpdatecomputer.setString(2, computer.getName());
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
                computer.setIntroduced(resListecomputer.getDate(3));
                computer.setDicontinued(resListecomputer.getDate(4));
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
