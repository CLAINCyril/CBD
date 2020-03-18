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

@Controller
//@RequestMapping
public class ServletDashBard{

	public ServiceComputer service;
	public ComputerMapper computerMapper;

	public ServletDashBard(ServiceComputer service, ComputerMapper computer) {
		this.service = service;
		this.computerMapper = computer;
	}

	@GetMapping(value = "/ListComputer")
	public ModelAndView ListComputer(@RequestParam(required = false, value = "pageIterator") String pageIterator,
			@RequestParam(required = false, value = "taillePage") String taillePage,
			@RequestParam(required = false, value = "search") String search,
			@RequestParam(required = false, value = "order") String order) {
		
		ModelAndView modelAndView = new ModelAndView("ListComputer");
		List<Computer> computerList = new ArrayList<Computer>();
		Page page;
		
		if(("" != taillePage) && (taillePage != null)) {
			System.out.println(taillePage);
			page = new Page(pageIterator, taillePage, service);

		}else {
			page = new Page(0, 20, service);
		}
			

		computerList = getPage(order, search, page);
		List<ComputerDTO> computerDTOList = computerList.stream()
				.map(computer -> computerMapper.convertFromComputerToComputerDTO(computer))
				.collect(Collectors.toList());
		
		setAttributeListComputer(order, search, pageIterator, page, computerDTOList, modelAndView);
		return modelAndView;

	}

	@PostMapping(value="/deleteComputer")
	public ModelAndView deleteComputer(@RequestParam(value = "selection") String selection) {

		ModelAndView modelAndView = new ModelAndView("redirect:/ListComputer");
		List<String> computers = Arrays.asList(selection.split(","));

		service.deleteComputerList(computers);
		return modelAndView;
	}
	
	private void setAttributeListComputer(String order, String search, String pageIterator, Page page,
			List<ComputerDTO> computerDTOList, ModelAndView modelAndView) {
		modelAndView.addObject("search", search);
		modelAndView.addObject("order",order);
		modelAndView.addObject("sizeComputer", page.getSizeComputer());
		modelAndView.addObject("computerList", computerDTOList);
		modelAndView.addObject("pageIterator", pageIterator);
	}

	private List<Computer> getPage(String order, String search, Page page) {
		List<Computer> computerList;

		if (order != null) {
			computerList = page.getPageOrderBy(order);
		} else if (!("".equals(search)) && (search != null)) {
			computerList = page.getPageByName(search);

		} else {
			computerList = page.getPage();
		}
		return computerList;
	}



}
