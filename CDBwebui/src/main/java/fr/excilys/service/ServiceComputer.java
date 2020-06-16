package fr.excilys.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.modele.Computer;
import fr.excilys.persistence.DAOComputer;

enum EVITEINJECTION {
	COMPUTER, INTRODUCED, DISCONTINUED, COMPANY;
	
	static String value(String string) {
		switch (string) {
		case "COMPUTER":
			return (" computer.name");
		case "INTRODUCED":
			return (" computer.introduced");
		case "DISCONTINUED":
			return (" computer.discontinued");
		case "COMPANY":
			return (" company.name");
		default:
			return (" computer.name");
		}
	}
}

@Service
public class ServiceComputer {

	private DAOComputer daoComputer;

	
	public ServiceComputer(DAOComputer daoComputer) {
		this.daoComputer = daoComputer;

	}

	@Transactional
	public void persisteComputer(Computer computer) {
		daoComputer.persisteComputer(computer);
	}

	public int getlength() {
		return (int) daoComputer.countComputer();
	}


	@Transactional
	public void deleteComputer(int id) {
		daoComputer.deleteComputer(id);
	}
	
	@Transactional
	public void deleteComputerList(List<String> listIdComputer) {
		daoComputer.deleteComputerListe(listIdComputer);
	}

	@Transactional
	public Optional<Computer> getComputer(int Id) {
		return daoComputer.getComputer(Id);
	}

	@Transactional
	public List<Computer> getPageComputer(int offset, int number) {
		return daoComputer.getPageComputer(offset, number);
	}

	@Transactional
	public void updateComputer(Computer computer) {
		daoComputer.updateComputer(computer);
	}

	@Transactional
	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		return daoComputer.getPageComputerByName(search, offset, number);
	}

	@Transactional
	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		order = EVITEINJECTION.value(order.toUpperCase());
		return daoComputer.getPageComputerOrder(offset, number, order);
	}

}
