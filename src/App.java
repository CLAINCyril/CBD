import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DAO.Connexion;
import DAO.DAOComputer;
import DAO.DAOCompany;
import entite.Company;
import entite.Computer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        Connexion conn = new Connexion();
//        conn.connect();
//        conn.close();
//        DAOcompany daoC = new DAOcompany();
//        Company comp = new Company();
//        comp = daoC.getCompany(5);
//        System.out.println(comp.toString());
//	    List<Company> companylist = new ArrayList<Company>();
//	    companylist = daoC.getallCompany();
//	    System.out.println(companylist);
//	    List<Computer> computerListe = new ArrayList<Computer>();
//	    computerListe = daoCC.getallcomputer();
//	    System.out.println(computerListe);
//	    Computer comp2 = new Computer();
//	    comp2.setId(12531);
//	    comp2.setIdCompagny(1);
//	    comp2.setName("apple");
//	    comp2.setIntroduced(LocalDateTime.now());
//	    comp2.setDiscontinued(LocalDateTime.now());
////	    daoCC.persistecomputer(comp2);
	    Computer C = DAOComputer.getInstance().getcomputer(502);
	    System.out.println(C.toString());
	    
    }
}
