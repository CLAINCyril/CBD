package fr.excilys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.DTO.ComputerDTO;
import fr.excilys.DTO.ListComputerParameter;
import fr.excilys.model.Computer;
import fr.excilys.service.Page;
import fr.excilys.service.ServiceComputer;
import fr.excilys.service.ServiceServletDashBoard;

@Controller
@RequestMapping(value = "/ListComputer")
public class ServletDashBard{

	public ServiceComputer serviceComputer;
	public ServiceServletDashBoard serviceServletDashBoard;
	private int startItemPage = 0;
	private int lastItemPage = 20;

	public ServletDashBard(ServiceComputer serviceComputer, ServiceServletDashBoard serviceServletDashBoard) {
		this.serviceComputer = serviceComputer;
		this.serviceServletDashBoard = serviceServletDashBoard;
	}

	@GetMapping
	public ModelAndView ListComputer(ListComputerParameter listComputerParameter) {
		
		ModelAndView modelAndView = new ModelAndView("ListComputer");
		List<Computer> computerList = new ArrayList<Computer>();
		
		
		Page page = serviceServletDashBoard.getFirstPage(listComputerParameter.getPageIterator(), listComputerParameter.getTaillePage(), startItemPage, lastItemPage);
			
		computerList = serviceServletDashBoard.getPage(listComputerParameter.getOrder(), listComputerParameter.getSearch(), page);
		List<ComputerDTO> computerDTOList = serviceServletDashBoard.mapComputerToDTOList(computerList);
		
		setAttributeListComputer(listComputerParameter.getOrder(), listComputerParameter.getSearch(), listComputerParameter.getPageIterator(), page, computerDTOList, modelAndView);
		
		return modelAndView;
		

	}
	

	@PostMapping
	public ModelAndView deleteComputer(@RequestParam(value = "selection") String selection) {

		ModelAndView modelAndView = new ModelAndView("redirect:/ListComputer");
		List<String> computers = serviceServletDashBoard.splitSelection(selection);

		serviceComputer.deleteComputerList(computers);
		
		return modelAndView;
	}
	
	public void setAttributeListComputer(String order, String search, String pageIterator, Page page,
			List<ComputerDTO> computerDTOList, ModelAndView modelAndView) {
		modelAndView.addObject("search", search);
		modelAndView.addObject("order", order);
		modelAndView.addObject("sizeComputer", page.getSizeComputer());
		modelAndView.addObject("computerList", computerDTOList);
		modelAndView.addObject("pageIterator", pageIterator);
	}

}
