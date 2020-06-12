package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import Validator.ValidatorComputer;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import serviceException.DateException;

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


	public void isValidEdit(String companyId, Computer computer) throws DateException {
		new ValidatorComputer().discontinuedAfterIntroduced(computer.getDiscontinued(), computer.getIntroduced());
		serviceComputer.updateComputer(computer);
	}
}
