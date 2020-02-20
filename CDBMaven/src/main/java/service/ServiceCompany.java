package fr.excilys.cclain.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.excilys.cclain.modele.Company;
import fr.excilys.cclain.persistence.Connexion;
import fr.excilys.cclain.persistence.DAOCompany;

public final class ServiceCompany {
	Connection conn;

	private static volatile ServiceCompany instance = null;
		
	private ServiceCompany(Connection conn) {
		this.conn = conn;
		
	}
	
	public final static ServiceCompany getInstance(Connection conn) {
        if (ServiceCompany.instance == null) {
           synchronized(ServiceCompany.class) {
             if (ServiceCompany.instance == null) {
            	 ServiceCompany.instance = new ServiceCompany(conn);
             }
           }
        }
        return ServiceCompany.instance;
	}

	public int getlength() throws SQLException {
		return getAllCompany().size();
	}

	public void persisteCompany(Company company) throws SQLException {
		DAOCompany.getInstance(conn).persisteCompany(company);
	}
	
	public void deleteCompany(Company company) throws SQLException {
		DAOCompany.getInstance(conn).deleteCompany(company.getId());
	}
	
	public Company getCompany(int Id) throws SQLException {
		return DAOCompany.getInstance(conn).getCompany(Id).get();
	}
	
	public List<Company> getAllCompany() throws SQLException {
		return DAOCompany.getInstance(conn).getAllCompany();
		
	}
	public List<Company> getPageCompany(int offset, int number) throws SQLException {
		return DAOCompany.getInstance(Connexion.getInstance().getConn()).getPageCompany(offset, number);
	}
}
