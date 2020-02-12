package service;

import java.util.List;

import DAO.DAOCompany;
import entite.Company;

public class ServiceCompany {
	
	DAOCompany dao;
	
	
	public ServiceCompany() {
		this.dao = DAOCompany.getInstance();
	}


	public boolean persisteCompany(Company company) {
		return this.dao.persisteCompany(company);
	}
	
	public boolean deleteCompany(Company company) {
		return this.dao.deletecompany(company.getId());
	}
	
	public Company getCompany(int Id) {
		return this.dao.getCompany(Id);
	}
	
	public List<Company> getallCompany(){
		return this.dao.getallCompany();
	}
}
