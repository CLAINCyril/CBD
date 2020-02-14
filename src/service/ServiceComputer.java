package service;

import java.util.List;

import modele.Company;
import modele.Computer;
import persistence.Connexion;
import persistence.DAOCompany;
import persistence.DAOComputer;

public final class ServiceComputer {
	
	DAOComputer dao;
	private static volatile ServiceComputer instance = null;
		
	private ServiceComputer() {
		this.dao = DAOComputer.getInstance();
		
	}
		
	public final static ServiceComputer getInstance() {
        if (ServiceComputer.instance == null) {
           synchronized(ServiceComputer.class) {
             if (ServiceComputer.instance == null) {
            	 ServiceComputer.instance = new ServiceComputer();
             }
           }
        }
        return ServiceComputer.instance;
	}


	public void persisteComputer(Computer computer) {
		this.dao.persistecomputer(computer);
	}
	
	public int getlength(){
		return getAllComputer().size();
	}
	public void deleteComputer(int id){
		this.dao.deletecomputer(id);
	}
	
	public Computer getComputer(int Id) {
		return this.dao.getComputer(Id).get();
	}
	
	public List<Computer> getAllComputer(){
		return this.dao.getAllComputer();
	}
	
	public List<Computer> getPageComputer(int offset, int number){
		return this.dao.getPageComputer(offset, number).get();
	}
	public void updateComputer(Computer computer) {
		this.dao.updateComputer(computer);
	}
}
