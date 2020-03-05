package service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import modele.Computer;
import persistence.DAOComputer;

public final class ServiceComputer {
	private Connection conn;

	private static volatile ServiceComputer instance = null;

	private ServiceComputer(Connection conn) {
		this.conn = conn;

	}

	public final static ServiceComputer getInstance(Connection conn) {
		if (ServiceComputer.instance == null) {
			synchronized (ServiceComputer.class) {
				if (ServiceComputer.instance == null) {
					ServiceComputer.instance = new ServiceComputer(conn);
				}
			}
		}
		return ServiceComputer.instance;
	}

	public void persisteComputer(Computer computer) {
		DAOComputer.getInstance(conn).persisteComputer(computer);
	}

	public int getlength() {
		return getAllComputer().size();
	}

	public void deleteComputer(int id) {
		DAOComputer.getInstance(conn).deleteComputer(id);
	}

	public Optional<Computer> getComputer(int Id) {
		return DAOComputer.getInstance(conn).getComputer(Id);
	}

	public List<Computer> getAllComputer() {
		return DAOComputer.getInstance(conn).getAllComputer();
	}

	public List<Computer> getPageComputer(int offset, int number) {
		return DAOComputer.getInstance(conn).getPageComputer(offset, number);
	}

	public void updateComputer(Computer computer) {
		DAOComputer.getInstance(conn).updateComputer(computer);
	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		return DAOComputer.getInstance(conn).getPageComputerByName(search, offset, number);
	}

	public List<Computer> getPageComputerOrderByName(int offset, int number) {
		return DAOComputer.getInstance(conn).getPageComputerOrderByName(offset, number);
	}

}
