package client;

import persistence.ConnectionHikari;
import persistence.Connexion;
import persistence.DAOComputer;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

//		new CliUI();
//		ConnectionHikari.getInstance().getConn();
		System.out.println(DAOComputer.getInstance(Connexion.getInstance().getConn()).getComputer(5).get());
	}
}