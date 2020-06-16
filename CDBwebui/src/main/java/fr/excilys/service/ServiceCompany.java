package fr.excilys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.modele.Company;
import fr.excilys.persistence.DAOCompany;

@Service
public class ServiceCompany {
	DAOCompany daoCompany;

	
	@Autowired
	public ServiceCompany(DAOCompany daoCompany) {
		this.daoCompany = daoCompany;		
	}
	

	public int getlength() {
		return getAllCompany().size();
	}
	
	@Transactional
	public void persisteCompany(Company company){
		daoCompany.persisteCompany(company);
	}
	
	@Transactional
	public void deleteCompany(Company company){
		daoCompany.deleteCompany(company.getId());
	}
	
	@Transactional
	public Company getCompany(int Id){
		return daoCompany.getCompany(Id).get();
	}
	
	@Transactional
	public List<Company> getAllCompany() {
		return daoCompany.getAllCompany();
		
	}
	
	@Transactional
	public List<Company> getPageCompany(int offset, int number){
		return daoCompany.getPageCompany(offset, number);
	}

	@Transactional
	public List<Integer> getAllCompanyid(){
		List<Integer> listId = new ArrayList<Integer>();
		List<Company> listCompany =  getAllCompany();
		for (Company company : listCompany) {
			listId.add(company.getId());
		}
		return listId;
		
	}
}
