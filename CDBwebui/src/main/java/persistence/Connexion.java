package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.Loggin;

/**
 * Classe de connection au server MySql à la table "computer-database-db".<br/>
 * 
 * Exemple:<br/>
 * Connection conn = new Connexion();<br/>
 * conn.connect();<br/>
 * // do your things <br/>
 * conn.close();<br/>
 * 
 * @author cyril
 *
 */
public final class Connexion {

	private String user = "admincdb";
	private String mdp = "qwerty1234";
	private String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";

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
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Loggin.display(e.getMessage());
		}
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
