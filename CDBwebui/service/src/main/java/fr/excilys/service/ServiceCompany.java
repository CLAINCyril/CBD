package fr.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.persistence.DAOCompany;

@Service
public class ServiceCompany {
	DAOCompany daoCompany;
	CompanyMapper companyMapper;
	
	@Autowired
	public ServiceCompany(DAOCompany daoCompany, CompanyMapper companyMapper) {
		this.daoCompany = daoCompany;
		this.companyMapper = companyMapper;
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


	public Company mapCompanyDTOTOCompany(CompanyDTO companyDTO) {
		
		return companyMapper.fromCompanyDTOToCompany(companyDTO);
	}

	@Transactional
	public void updateCompany(Company company) {
		daoCompany.updateCompany(company);
	}


	public List<CompanyDTO> fromCompanyListToDTOList(List<Company> companys) {
		List<CompanyDTO> CompanyDTOList = companys.stream()
				.map(company -> companyMapper.convertFromCompanyToCompanyDTO(company))
				.collect(Collectors.toList());
		return CompanyDTOList;
	}

	
		
}