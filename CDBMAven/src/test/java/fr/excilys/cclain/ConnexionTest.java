package fr.excilys.cclain;

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
public final class ConnexionTest {
	
    String host = "127.0.0.1";
    int port = 8082;
    String user = "sa";
    String mdp = "";
    String BddName = "computer-database-db";
    String url = "jdbc:h2:~/" + BddName;

	
	private static Connection conn;
	
	private static volatile ConnexionTest instance = null;
	
	private ConnexionTest() {
		
	}
				
	public final static ConnexionTest getInstance() {
        if (ConnexionTest.instance == null) {
           synchronized(ConnexionTest.class) {
             if (ConnexionTest.instance == null) {
            	 ConnexionTest.instance = new ConnexionTest();
             }
           }
        }
        return ConnexionTest.instance;
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
