package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;

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
