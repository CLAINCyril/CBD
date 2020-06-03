package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	ComputerMapper computerMapper;
	@Autowired
	ServiceComputer serviceComputer;


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


	public void addItemModel(ModelAndView modelAndView, List<CompanyDTO> companysDTO, ComputerDTO computerDTO) {
		modelAndView.addObject("companysDTO", companysDTO);
		modelAndView.addObject("computerDTO", computerDTO);
	}




	public void isValidEdit(String companyId, Computer computer) throws DateException {
		new ValidatorComputer().discontinuedAfterIntroduced(computer.getDiscontinued(), computer.getIntroduced());
		serviceComputer.updateComputer(computer);
	}
}