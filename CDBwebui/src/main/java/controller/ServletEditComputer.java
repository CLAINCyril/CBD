package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import Validator.ValidatorComputer;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import service.ServiceServletEditComputer;
import serviceException.DateException;

@Controller
@RequestMapping(value = "/EditComputer")
public class ServletEditComputer{
	
	ServiceCompany serviceCompany;
	ServiceServletEditComputer serviceServletEditComputer;
	

	public ServletEditComputer(ComputerMapper computerMapper, ServiceCompany serviceCompany,ServiceServletEditComputer serviceServletEditComputer) {
		this.serviceCompany = serviceCompany;
		this.serviceServletEditComputer = serviceServletEditComputer;
	}
	
	
	@GetMapping
	public ModelAndView showEditComputer(@RequestParam(required = false, value = "computerid") String computerid) {

		ModelAndView modelAndView = new ModelAndView("EditComputer");
		int computerId = Integer.parseInt(computerid);

		List<Company> companyList = serviceCompany.getAllCompany();
		List<CompanyDTO> companysDTO = serviceServletEditComputer.mapCompanyToDTOList(companyList);

		ComputerDTO computerDTO = serviceServletEditComputer.mapComputerToDTO(computerId);

		serviceServletEditComputer.addItemModel(modelAndView, companysDTO, computerDTO);
		
		return modelAndView;
	}


	@PostMapping(value = "/EditComputer")
	public ModelAndView editComputer(@RequestParam(value = "computerId") String computerId,
			@RequestParam(value = "computerName") String computerName,
			@RequestParam(required = false, value = "introduced") String introduced,
			@RequestParam(required = false, value = "discontinued") String discontinued,
			@RequestParam(required = false, value = "companyId") String companyId){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("computerId", Integer.parseInt(companyId));
		CompanyDTO companyDTO = new CompanyDTO(Integer.parseInt(companyId));
		ComputerDTO computerDTO = new ComputerDTO(Integer.parseInt(computerId), computerName, introduced, discontinued, companyDTO);
		
		Computer computer = serviceServletEditComputer.mapDTOtoComputer(computerDTO);
		try {
			serviceServletEditComputer.isValidEdit(companyId, computer);
		} catch (DateException dateExecption) {
			showEditComputer(companyId);
		}
	
		return modelAndView;
		}

	
}
