package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DTO.ComputerDTO;
import mapper.ComputerMapper;
import modele.Computer;
import service.Page;
import service.ServiceComputer;
import service.ServiceServletDashBoard;

@Controller
public class ServletDashBard{

	public ServiceComputer serviceComputer;
	public ServiceServletDashBoard serviceServletDashBoard;
	private int startItemPage = 0;
	private int lastItemPage = 20;

	public ServletDashBard(ServiceComputer serviceComputer, ServiceServletDashBoard serviceServletDashBoard) {
		this.serviceComputer = serviceComputer;
		this.serviceServletDashBoard = serviceServletDashBoard;
	}

	@GetMapping(value = "/ListComputer")
	public ModelAndView ListComputer(@RequestParam(required = false, value = "pageIterator") String pageIterator,
			@RequestParam(required = false, value = "taillePage") String taillePage,
			@RequestParam(required = false, value = "search") String search,
			@RequestParam(required = false, value = "order") String order) {
		
		ModelAndView modelAndView = new ModelAndView("ListComputer");
		List<Computer> computerList = new ArrayList<Computer>();
		
		
		Page page = serviceServletDashBoard.getFirstPage(pageIterator, taillePage, startItemPage, lastItemPage);
			
		computerList = serviceServletDashBoard.getPage(order, search, page);
		
		List<ComputerDTO> computerDTOList = serviceServletDashBoard.mapComputerToDTOList(computerList);
		
		serviceServletDashBoard.setAttributeListComputer(order, search, pageIterator, page, computerDTOList, modelAndView);
		
		return modelAndView;

	}
	

	@GetMapping(value="/deleteComputer")
	public ModelAndView deleteComputer(@RequestParam(value = "selection") String selection) {

		ModelAndView modelAndView = new ModelAndView("redirect:/ListComputer");
		List<String> computers = serviceServletDashBoard.splitSelection(selection);

		serviceComputer.deleteComputerList(computers);
		
		return modelAndView;
	}


}
