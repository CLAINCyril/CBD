import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import modele.Company;
import modele.Computer;
import persistence.Connexion;
import persistence.DAOCompany;
import persistence.DAOComputer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	List<Company> comp;
    	comp = DAOCompany.getInstance().getallCompany(1, 5);
    	System.out.println(comp);
    }
}
