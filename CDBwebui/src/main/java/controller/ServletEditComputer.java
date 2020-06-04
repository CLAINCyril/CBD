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


	@PostMapping
	public ModelAndView editComputer(EditComputerParameter editComputerParameter){

		ModelAndView modelAndView = new ModelAndView("redirect:/ListComputer");
		modelAndView.addObject("computerId", Integer.parseInt(editComputerParameter.getCompanyId()));
		CompanyDTO companyDTO = new CompanyDTO(Integer.parseInt(editComputerParameter.getCompanyId()));
		ComputerDTO computerDTO = new ComputerDTO(Integer.parseInt(editComputerParameter.getComputerId()), editComputerParameter.getComputerName(), editComputerParameter.getIntroduced(), editComputerParameter.getDiscontinued(), companyDTO);
		
		Computer computer = serviceServletEditComputer.mapDTOtoComputer(computerDTO);
		try {
			serviceServletEditComputer.isValidEdit(editComputerParameter.getCompanyId(), computer);
		} catch (DateException dateExecption) {
			showEditComputer(editComputerParameter.getCompanyId());
		}
	
		return modelAndView;
		}

	
}
