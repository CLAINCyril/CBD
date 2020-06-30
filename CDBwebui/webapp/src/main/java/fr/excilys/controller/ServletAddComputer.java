package fr.excilys.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.service.ServiceCompany;
import fr.excilys.service.ServiceComputer;
import fr.excilys.service.ServiceServletAddComputer;

@Controller
@RequestMapping(value="/AddComputer")
public class ServletAddComputer {

	ServiceServletAddComputer serviceServletAddComputer;
	ServiceCompany serviceCompany;
	ServiceComputer serviceComputer;

	public ServletAddComputer(ServiceServletAddComputer serviceServletAddComputer,ServiceCompany serviceCompany, ServiceComputer serviceComputer) {
		this.serviceCompany = serviceCompany;
		this.serviceComputer = serviceComputer;
		this.serviceServletAddComputer = serviceServletAddComputer;
	}

	
	@GetMapping
	public ModelAndView addComputer() {

		ModelAndView modelAndView = new ModelAndView("AddComputer");

		List<Company> companyList = serviceCompany.getAllCompany();

		List<CompanyDTO> companysDTO = serviceServletAddComputer.mapCompanyToDTOList(companyList);

		modelAndView.addObject("companysDTO", companysDTO);
		return modelAndView;

	}


	@PostMapping
	public ModelAndView addComputer(ComputerDTO computerDTO) {

		ModelAndView modelAndView = new ModelAndView();

		Computer computer = serviceServletAddComputer.mapComputerDTOToComputer(computerDTO);

		serviceComputer.persisteComputer(computer);

		modelAndView.setViewName("redirect:/ListComputer");		
		
		return modelAndView;
	}

}
