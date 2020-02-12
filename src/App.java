import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import modele.Company;
import modele.Computer;
import persistence.Connexion;
import persistence.DAOCompany;
import persistence.DAOComputer;
import service.ServiceComputer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		Scanner sc = new Scanner(System.in);
    	boolean test = true;
    	while(test) {
    		System.out.println("Veuillez saisir une action :");
    		System.out.println("============================");
    		System.out.println("1 : List computers");
    		System.out.println("============================");
    		System.out.println("2 : List companies");
    		System.out.println("============================");
    		System.out.println("3 : Show computer details (the detailed information of only one computer)");
    		System.out.println("============================");
    		System.out.println("4 : Create a computer");
    		System.out.println("============================");
    		System.out.println("5 : Update a computer");
    		System.out.println("============================");
    		System.out.println("6 :  Delete a computer");
    		System.out.println("============================");	


	    	int cases = sc.nextInt();
	    	switch (cases) {
	    	  case 1:
	    		  List<Computer> computers;
	    		  System.out.println("saisissez pages");
	    		  computers = ServiceComputer.getInstance().getallComputer(sc.nextInt(),sc.nextInt());
	    		  System.out.println(computers);
	    		break;
	    	  case 2:
	    	    System.out.println("Tuesday");
	    	    break;
	    	  case 3:
	    	    System.out.println("Wednesday");
	    	    break;
	    	  case 4:
		    	    Computer computer = new Computer();
		    	    System.out.println("Veuillez saisir l'id :");
			    	computer.setId(sc.nextInt());
			    	computer.setName(sc.nextLine());
			    	ServiceComputer.getInstance().persisteComputer(computer);
	    	    break;
	    	  case 5:
	    	    System.out.println("Friday");
	    	    break;
	    	  case 6:
	    	    System.out.println("Saturday");
	    	    break;
	    	  case 7:
	    	    System.out.println("Sunday");
	    	    break;
	    	   default:
	    		   System.out.println("touche non pris en compte");
		    	   test = false;
	    		}
	    	}
    	sc.close();
    	
    }
}
