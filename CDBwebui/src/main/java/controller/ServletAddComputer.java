package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

@Controller
public class ServletAddComputer {

	ComputerMapper computerMapper;

	ServiceCompany serviceCompany;
	ServiceComputer serviceComputer;

	public ServletAddComputer(ServiceCompany serviceCompany, ServiceComputer serviceComputer,ComputerMapper computerMapper) {
		this.serviceCompany = serviceCompany;
		this.serviceComputer = serviceComputer;
		this.computerMapper = computerMapper;
	}

	@GetMapping(value = "/addComputer")
	public ModelAndView addComputer() {

		ModelAndView modelAndView = new ModelAndView("addComputer");

		List<Company> companyList = serviceCompany.getAllCompany();

		List<CompanyDTO> companysDTO = companyList.stream()
				.map(company -> CompanyMapper.convertFromCompanyToCompanyDTO(company)).collect(Collectors.toList());

		modelAndView.addObject("companies", companysDTO);
		return modelAndView;

	}

	@PostMapping(value = "/addComputer")
	public ModelAndView addComputer(@RequestParam(value = "computerName") String computerName,
			@RequestParam(required = false, value = "introduced") String introduced,
			@RequestParam(required = false, value = "discontinued") String discontinued,
			@RequestParam(required = false, value = "companyId") String companyId) {

		ModelAndView modelAndView = new ModelAndView();

		CompanyDTO companyDTO = new CompanyDTO(Integer.parseInt(companyId));

		ComputerDTO computerDTO = new ComputerDTO(computerName, introduced, discontinued, companyDTO);

		Computer computer = computerMapper.fromComputerDTOToComputer(computerDTO);

		serviceComputer.persisteComputer(computer);

		modelAndView.setViewName("redirect:/ListComputer");		
		
		return modelAndView;
	}

}
