package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import modele.Company;
import modele.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import service.ServiceServletAddComputer;

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
		System.out.println(companysDTO);
		return modelAndView;

	}


	@PostMapping
	public ModelAndView addComputer(AddComputerParameter addComputerParameter) {

		ModelAndView modelAndView = new ModelAndView();

		CompanyDTO companyDTO = new CompanyDTO(Integer.parseInt(addComputerParameter.getCompanyId()));

		ComputerDTO computerDTO = new ComputerDTO(addComputerParameter.getComputerName(), addComputerParameter.getIntroduced(), addComputerParameter.getDiscontinued(), companyDTO);

		Computer computer = serviceServletAddComputer.mapComputerDTOToComputer(computerDTO);

		serviceComputer.persisteComputer(computer);

		modelAndView.setViewName("redirect:/ListComputer");		
		
		return modelAndView;
	}

}
