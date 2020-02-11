import java.util.ArrayList;
import java.util.List;

import DAO.Connexion;
import DAO.DAOComputer;
import DAO.DAOcompany;
import entite.Company;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Connexion conn = new Connexion();
        conn.connect();
        conn.close();
        DAOcompany daoC = new DAOcompany();
        Company comp = new Company();
        comp = daoC.getCompany(5);
        System.out.println(comp.toString());
	    List<Company> companylist = new ArrayList<Company>();
	    companylist = daoC.getallCompany();
	    System.out.println(companylist);
    }
}
