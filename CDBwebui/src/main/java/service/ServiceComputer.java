package service;

import java.util.List;
import java.util.Optional;

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

	private static volatile ServiceComputer instance = null;

	private ServiceComputer() {

	}

	public final static ServiceComputer getInstance() {
		if (ServiceComputer.instance == null) {
			synchronized (ServiceComputer.class) {
				if (ServiceComputer.instance == null) {
					ServiceComputer.instance = new ServiceComputer();
				}
			}
		}
		return ServiceComputer.instance;
	}

	public void persisteComputer(Computer computer) {
		DAOComputer.getInstance().persisteComputer(computer);
	}

	public int getlength() {
		return getAllComputer().size();
	}

	public void deleteComputer(int id) {
		DAOComputer.getInstance().deleteComputer(id);
	}

	public void deleteComputerList(List<String> listIdComputer) {
		DAOComputer.getInstance().deleteComputerListe(listIdComputer);
	}

	public Optional<Computer> getComputer(int Id) {
		return DAOComputer.getInstance().getComputer(Id);
	}

	public List<Computer> getAllComputer() {
		return DAOComputer.getInstance().getAllComputer();
	}

	public List<Computer> getPageComputer(int offset, int number) {
		return DAOComputer.getInstance().getPageComputer(offset, number);
	}

	public void updateComputer(Computer computer) {
		DAOComputer.getInstance().updateComputer(computer);
	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		return DAOComputer.getInstance().getPageComputerByName(search, offset, number);
	}

	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		order = EVITEINJECTION.value(order.toUpperCase());
		return DAOComputer.getInstance().getPageComputerOrder(offset, number, order);
	}

}
