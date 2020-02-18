package service;

import java.util.List;

import modele.Company;
import persistence.DAOCompany;

public final class ServiceCompany {
	
	DAOCompany dao;
	private static volatile ServiceCompany instance = null;
		
	private ServiceCompany() {
		this.dao = DAOCompany.getInstance();
		
	}
	
	public final static ServiceCompany getInstance() {
        if (ServiceCompany.instance == null) {
           synchronized(ServiceCompany.class) {
             if (ServiceCompany.instance == null) {
            	 ServiceCompany.instance = new ServiceCompany();
             }
           }
        }
        return ServiceCompany.instance;
	}

	public int getlength(){
		return getAllCompany().size();
	}

	public void persisteCompany(Company company) {
		this.dao.persisteCompany(company);
	}
	
	public void deleteCompany(Company company) {
		this.dao.deleteCompany(company.getId());
	}
	
	public Company getCompany(int Id) {
		return this.dao.getCompany(Id).get();
	}
	
	public List<Company> getAllCompany(){
		return this.dao.getAllCompany();
		
	}
	public List<Company> getPageCompany(int offset, int number){
		return this.dao.getPageCompany(offset, number);
	}
}
