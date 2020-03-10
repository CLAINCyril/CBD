package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modele.Computer;
import persistence.DAOComputer;

enum EVITEINJECTION {
	COMPUTER, INTRODUCED, DISCONTINUED, COMPANY;

	static String value(String string) {
		switch (string) {
		case "COMPUTER":
			return ("computer.name");
		case "INTRODUCED":
			return ("computer.introduced");
		case "DISCONTINUED":
			return ("computer.discontinued");
		case "COMPANY":
			return ("company.name");
		default:
			return ("computer.name");
		}
	}
}

@Service
public final class ServiceComputer {

	private DAOComputer daoComputer;

	@Autowired
	public ServiceComputer(DAOComputer daoComputer) {
		this.daoComputer = daoComputer;

	}


	public void persisteComputer(Computer computer) {
		daoComputer.persisteComputer(computer);
	}

	public int getlength() {
		return getAllComputer().size();
	}

	public void deleteComputer(int id) {
		daoComputer.deleteComputer(id);
	}

	public void deleteComputerList(List<String> listIdComputer) {
		daoComputer.deleteComputerListe(listIdComputer);
	}

	public Optional<Computer> getComputer(int Id) {
		return daoComputer.getComputer(Id);
	}

	public List<Computer> getAllComputer() {
		return daoComputer.getAllComputer();
	}

	public List<Computer> getPageComputer(int offset, int number) {
		return daoComputer.getPageComputer(offset, number);
	}

	public void updateComputer(Computer computer) {
		daoComputer.updateComputer(computer);
	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		return daoComputer.getPageComputerByName(search, offset, number);
	}

	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		order = EVITEINJECTION.value(order.toUpperCase());
		return daoComputer.getPageComputerOrder(offset, number, order);
	}

}
