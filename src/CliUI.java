import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.Company;
import modele.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

public class CliUI {
	List<Company> companys;
	List<Computer> computers;
	Computer computer;
	Company company;
	Boolean tache;

	/**
	 * Transform un type String en type LocalDatetime.
	 * @param date
	 * @return
	 */
	private static LocalDateTime ConvertLocalDateTime(String date) {
		if (date.isEmpty()) {return null;}
		date = date+" 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime datetime = LocalDateTime.parse(date, formatter);		
		return datetime;
	}
	/**
	 * retourne une page d'ordinateur
	 * @param offset
	 * @param number
	 * @return
	 */
	public List<Computer> getOnePAgeOfComputer(int offset, int number){
		return(ServiceComputer.getInstance().getallComputer(offset, number));
	}
	/**
	 * retourne une page de company.
	 * @param offset
	 * @param number
	 * @return
	 */
	public List<Company> getOnePAgeOfCompany(int offset, int number){
		return(ServiceCompany.getInstance().getallCompany(offset, number));
	}
	/**
	 * Creer un ordinateur a l'aide des params saisie a l'entrée.
	 * @param sc
	 */
	public void createComputer(Scanner sc) {
		int id = ServiceComputer.getInstance().getlength()+1;
  	  	computer.setId(id);
  	  	System.out.println("Veuillez saisir le nom :\n");
  	  	computer.setName(sc.next());
  	  	System.out.println("Veuillez saisir la date de sortie :\n");
  	  	computer.setIntroduced(ConvertLocalDateTime(sc.next()));
  	  	System.out.println("Veuillez saisir la date de fin de serie :\n");
  	  	computer.setDiscontinued(ConvertLocalDateTime(sc.next()));
  	  	System.out.println("Veuillez saisir l'id company:\n");
  	  	company = ServiceCompany.getInstance().getCompany(sc.nextInt());
  	  	computer.setCompany(company);
  	  	ServiceComputer.getInstance().persisteComputer(computer);
		computer.setName(sc.nextLine());
		System.out.println(ServiceComputer.getInstance().persisteComputer(computer));

	}
	/**
	 * methode de pagination des computers
	 * verifie que la page suivante/precedante existe.
	 * @param sc
	 */
	public 	void pagineCompute(Scanner sc){
		List<Computer> computs = new ArrayList();
		boolean condition = true;
		String saisie;
		int offset = 0;
		int number = 20;
		int tailleL = ServiceComputer.getInstance().getlength();

		computs = getOnePAgeOfComputer(offset, number);
		System.out.println(computs);
		while(condition) {
			System.out.println("pres n for next p for previous page s fort stop");
			saisie = sc.next();
			condition = (saisie.contentEquals("n")) || (saisie.contentEquals("p"));
			if (saisie == "n") {
				if (20 > tailleL-number) {
					
					offset += 20;
					number += 20;
					computs = getOnePAgeOfComputer(offset, number);
				}
				else {
					System.out.println("vous etes a la derniere page!");
				}
			}
			if (saisie == "p") {
				if (offset < 20) {
					System.out.println("vous etes a la premiere page!");
				}
				else {
					offset -= 20;
					number -= 20;
					computs = getOnePAgeOfComputer(offset, number);
				}
				
			}
			System.out.println(computs);
		}
	}
	/**
	 * Methode de pagination des companys.
	 * verifie que la page suivante/precedante existe.
	 * @param sc
	 */
	public 	void pagineCompany(Scanner sc){
		List<Company> company = new ArrayList();
		boolean condition = true;
		String saisie;
		int offset = 0;
		int number = 20;
		int tailleL = ServiceCompany.getInstance().getlength();

		company = getOnePAgeOfCompany(offset, number);
		System.out.println(company);
		while(condition) {
			System.out.println("pres n for next p for previous page s fort stop");
			saisie = sc.next();
			condition = (saisie.contentEquals("n")) || (saisie.contentEquals("p"));
			if (saisie == "n") {
				if (20 > tailleL-number) {
					
					offset += 20;
					number += 20;
					company = getOnePAgeOfCompany(offset, number);
				}
				else {
					System.out.println("vous etes a la derniere page!");
				}
			}
			if (saisie == "p") {
				if (offset < 20) {
					System.out.println("vous etes a la premiere page!");
				}
				else {
					offset -= 20;
					number -= 20;
					company = getOnePAgeOfCompany(offset, number);
				}
				
			}
			System.out.println(company);
		}
	}
	
	public void UpdateComputer(Scanner sc) {
		
	}
	
	
	public CliUI(){
			this.computer = new Computer();
			this.company = new Company();
			this.tache= false;
			Scanner sc = new Scanner(System.in);
	    	boolean test = true;
	    	while(test) {
	    		System.out.println("============================");
	    		System.out.println("1 : List computers");
	    		System.out.println("============================");
	    		System.out.println("2 : List companies");
	    		System.out.println("============================");
	    		System.out.println("3 : Show computer details ");
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
		    		  pagineCompute(sc);
		    		  break;
		    	  case 2:
		    		  pagineCompany(sc);
		    		  break;
		    	  case 3:
		    		  System.out.println("saisir Id Computer");
		    		  System.out.println(ServiceComputer.getInstance().getComputer(sc.nextInt()));
		    	  case 4:
		    		  createComputer(sc);
		    		  break;
//		    	  case 5:
//		    	    System.out.println("Friday");
//		    	    break;
		    	  case 6:
		    		  System.out.println("saisissez l'ID computer");
		    		  System.out.println(ServiceComputer.getInstance().deleteComputer(sc.nextInt()));

		    	}
//	    	sc.close();
	    	
}
}
}
