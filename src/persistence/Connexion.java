package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import service.ServiceCompany;

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
	 * ouvre une connection.
	 */
    public void connect() {
        String host = "127.0.0.1";
        int port = 3306;
        String user = "admincdb";
        String mdp = "qwerty1234";
        String BddName = "computer-database-db";
        String url = "jdbc:mysql://" + host + ":"+ port + "/" + BddName;


        try{
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, mdp);
            System.out.println("Connexion ok");
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    /**
     * recupère la connection.
     * 
     * @return Connection
     */
    public Connection getConn() {
        return conn;
        }
    
    
    /**
     * Ferme la connection.
     */
    public void close() {
        try {
            conn.close();
            System.out.println("connexion close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
