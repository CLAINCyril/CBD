package fr.excilys.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.modele.Company;
import fr.excilys.modele.Computer;

@Service
public class ServiceServletAddComputer {
	
	CompanyMapper companyMapper;
	ComputerMapper computerMapper;
	
	public ServiceServletAddComputer(CompanyMapper companyMapper,	ComputerMapper computerMapper) {
		this.companyMapper = companyMapper;
		this.computerMapper = computerMapper;
	}

	public List<CompanyDTO> mapCompanyToDTOList(List<Company> companyList) {

		List<CompanyDTO> companysDTO = companyList.stream()
				.map(company -> companyMapper.convertFromCompanyToCompanyDTO(company)).collect(Collectors.toList());
		return companysDTO;
	}

	public Computer mapComputerDTOToComputer(ComputerDTO computerDTO) {

		return (computerMapper.fromComputerDTOToComputer(computerDTO));
	}
}
