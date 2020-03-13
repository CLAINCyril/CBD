package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import modele.Computer;

/**
 * Classe de pagination
 * 
 * @author cyril
 *
 */
public class Page {
	int pageIterator;
	int taillePage;
	int sizeComputer;
	int maxPage;
	ServiceComputer serviceComputer;
	
	public Page(int pageIterator, int taillePage,ServiceComputer service) {
		this.pageIterator = pageIterator;
		this.taillePage = taillePage;
		sizeComputer =  service.getAllComputer().size();
		this.serviceComputer = service;
		maxPage = sizeComputer / taillePage;
	}

	
	
	public int getSizeComputer() {
		return sizeComputer;
	}



	public int getMaxPage() {
		return maxPage;
	}

	public List<Computer> getPage() {

		ServiceComputer service = serviceComputer;
		List<Computer> computerList = service.getPageComputer(pageIterator * taillePage, taillePage);

		return computerList;
	}

	public List<Computer> getPageByName(String search) {
		ServiceComputer service = serviceComputer;
		List<Computer> computerList = service.getPageComputerByName(search, pageIterator * taillePage, taillePage);
		return computerList;
	}

	public List<Computer> getPageOrderBy(String order) {
		ServiceComputer service = serviceComputer;

		List<Computer> computerList = service.getPageComputerOrder(pageIterator * taillePage, taillePage, order);
		return computerList;
	}

	@Override
	public String toString() {
		return "Page [pageIterator=" + pageIterator + ", taillePage=" + taillePage + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pageIterator;
		result = prime * result + taillePage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (pageIterator != other.pageIterator)
			return false;
		if (taillePage != other.taillePage)
			return false;
		return true;
	}
	
	

}
