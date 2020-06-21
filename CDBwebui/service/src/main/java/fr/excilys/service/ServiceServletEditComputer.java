package fr.excilys.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;
import fr.excilys.exception.DateException;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.validator.ValidatorComputer;

@Service
public class ServiceServletEditComputer {
	
	CompanyMapper companyMapper;
	ComputerMapper computerMapper;
	ServiceComputer serviceComputer;

	

	public ServiceServletEditComputer(CompanyMapper companyMapper, ComputerMapper computerMapper,
			ServiceComputer serviceComputer) {
		this.companyMapper = companyMapper;
		this.computerMapper = computerMapper;
		this.serviceComputer = serviceComputer;
	}


	public List<CompanyDTO> mapCompanyToDTOList(List<Company> companyList) {
		List<CompanyDTO> companysDTO = companyList.stream()
				.map(company -> companyMapper.convertFromCompanyToCompanyDTO(company)).collect(Collectors.toList());
		return companysDTO;
	}
	

	public ComputerDTO mapComputerToDTO(int computerId) {
		ComputerDTO computerDTO = computerMapper
				.convertFromComputerToComputerDTO(serviceComputer.getComputer(computerId).get());
		return computerDTO;
	}


	public Computer mapDTOtoComputer(ComputerDTO computerDTO) {
		
		return computerMapper.fromComputerDTOToComputer(computerDTO);
	}


	public void isValidEdit(Computer computer) throws DateException{
		try {
			new ValidatorComputer().discontinuedAfterIntroduced(computer.getDiscontinued(), computer.getIntroduced());
			serviceComputer.updateComputer(computer);
		} catch (DateException dateException) {
			dateException.getMessage();
			throw dateException;
		}
	}
}
