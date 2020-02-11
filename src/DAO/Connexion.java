package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connexion {
	
	private static Connection conn; 
	
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
    public Connection getConn() {
        return conn;
        }
    
    
    public void close() {
        try {
            conn.close();
            System.out.println("connexion close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
