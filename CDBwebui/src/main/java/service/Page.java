package service;

import java.util.List;


import modele.Computer;
import persistence.Connexion;

/**
 * Classe de pagination 
 * @author cyril
 *
 */
public class Page {

	public List<Computer> getPage(int pageIterator, int taillePage) {

		ServiceComputer service = ServiceComputer.getInstance(Connexion.getInstance().getConn());

		List<Computer> computerList = service.getPageComputer(pageIterator * taillePage, taillePage);

		return computerList;
	}

	public List<Computer> getPageByName(String search,int pageIterator, int taillePage) {
		ServiceComputer service = ServiceComputer.getInstance(Connexion.getInstance().getConn());

		List<Computer> computerList = service.getPageComputerByName(search, pageIterator * taillePage, taillePage);
		return computerList;
	}

	public List<Computer> getPageOrderByName(int pageIterator, int taillePage) {
		ServiceComputer service = ServiceComputer.getInstance(Connexion.getInstance().getConn());

		List<Computer> computerList = service.getPageComputerOrderByName(pageIterator * taillePage, taillePage);
		return computerList;
	}

}
