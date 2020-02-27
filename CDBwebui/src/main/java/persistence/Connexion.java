package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe de connection au server MySql à la table "computer-database-db".<br/>
 * 
 * Exemple:<br/>
 * Connection conn = new Connexion();<br/>
 * conn.getconn();<br/>
 * // do your things <br/>
 * conn.close();<br/>
 * 
 * @author cyril
 *
 */
public final class Connexion {

	private String user = "admincdb";
	private String passwd = "qwerty1234";
	private String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";

	private static Logger logger = LoggerFactory.getLogger(Connexion.class);
	private static Connection conn;

	private static volatile Connexion instance = null;

	private Connexion() {

	}

	public final static Connexion getInstance() {
		if (Connexion.instance == null) {
			synchronized (Connexion.class) {
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
	 */
	public Connection getConn(){
	       try{
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	conn = DriverManager.getConnection(url, user, passwd);

	        }catch(SQLException e){
	            e.getMessage();
	        } catch (ClassNotFoundException e) {
				e.getMessage();
			}
		return conn;


	}
}