package service;

import java.util.List;
import modele.Computer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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

	
	public Page(String pageIterator, String taillePage,ServiceComputer service) {
		this.pageIterator = Integer.parseInt(pageIterator);
		this.taillePage = Integer.parseInt(taillePage);
		sizeComputer =  service.getAllComputer().size();
		this.serviceComputer = service;
		maxPage = sizeComputer / this.taillePage;
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.pageIterator).append(taillePage).toHashCode();
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
