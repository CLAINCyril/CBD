package fr.excilys.cclain.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Classe de connection au server MySql à la table "computer-database-db".<br/>
 * 
 * Exemple:<br/>
 * 	Connection conn = new Connexion();<br/>
 * 	conn.connect();<br/>
 * 	// do your things <br/>
 * 	conn.close();<br/>
 * @author cyril
 *
 */
public final class Connexion {
	

	private static final String USER = "admincdb";
	private static final String MDP = "qwerty1234";
	private static final  String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	
	private static Connection conn;
	
	private static volatile Connexion instance = null;
	
	private Connexion() {
		
	}
				
	public final static Connexion getInstance() {
        if (Connexion.instance == null) {
           synchronized(Connexion.class) {
             if (Connexion.instance == null) {
            	 Connexion.instance = new Connexion();
             }
           }
        }
        return Connexion.instance;
	}
	
    /**
     * recupère la connection.
     * 
     * @return Connection
     * @throws SQLException 
     */
    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL, USER, MDP);
        }
    
    
    /**
     * Ferme la connection.
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
