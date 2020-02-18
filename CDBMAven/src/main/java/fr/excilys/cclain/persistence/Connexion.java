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
	
    String host = "127.0.0.1";
    int port = 3306;
    String user = "admincdb";
    String mdp = "qwerty1234";
    String BddName = "computer-database-db";
    String url = "jdbc:mysql://" + host + ":"+ port + "/" + BddName;

	
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
        return DriverManager.getConnection(url, user, mdp);
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
