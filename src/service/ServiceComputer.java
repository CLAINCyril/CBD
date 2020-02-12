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


	public boolean persisteComputer(Computer computer) {
		return this.dao.persistecomputer(computer);
	}
	
	public boolean deleteComputer(Computer computer){
		return this.dao.deletecomputer(computer.getId());
	}
	
	public Computer getComputer(int Id) {
		return this.dao.getcomputer(Id);
	}
	
	public List<Computer> getallComputer(){
		return this.dao.getallcomputer();
	}
}
