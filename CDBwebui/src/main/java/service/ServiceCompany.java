package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import modele.Company;
import persistence.DAOCompany;

@Service
public final class ServiceCompany {
	DAOCompany daoCompany;

	private static volatile ServiceCompany instance = null;
		
	private ServiceCompany() {
		this.daoCompany = DAOCompany.getInstance();		
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

	public int getlength() {
		return getAllCompany().size();
	}

	public void persisteCompany(Company company){
		daoCompany.persisteCompany(company);
	}
	
	public void deleteCompany(Company company){
		daoCompany.deleteCompany(company.getId());
	}
	
	public Company getCompany(int Id){
		return daoCompany.getCompany(Id).get();
	}
	
	public List<Company> getAllCompany() {
		return daoCompany.getAllCompany();
		
	}
	public List<Company> getPageCompany(int offset, int number){
		return daoCompany.getPageCompany(offset, number);
	}

	public List<Integer> getAllCompanyid(){
		List<Integer> listId = new ArrayList<Integer>();
		List<Company> listCompany =  getAllCompany();
		for (Company company : listCompany) {
			listId.add(company.getId());
		}
		return listId;
		
	}
}
