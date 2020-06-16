package fr.excilys.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.excilys.DTO.ComputerDTO;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.modele.Computer;

@Service
public class ServiceServletDashBoard {

	public ServiceComputer serviceComputer;
	public ComputerMapper computerMapper;

	public ServiceServletDashBoard(ServiceComputer serviceComputer,	ComputerMapper computerMapper) {
		this.serviceComputer = serviceComputer;
		this.computerMapper = computerMapper;
	}
	
	public List<Computer> getPage(String order, String search, Page page) {
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

	public List<String> splitSelection(String selection) {
		List<String> computers = Arrays.asList(selection.split(","));
		return computers;
	}


	public Page getFirstPage(String pageIterator, String taillePage, int startItemPage, int lastItemPage) {
		Page page;

		if (("" != taillePage) && (taillePage != null)) {
			page = new Page(pageIterator, taillePage, serviceComputer);

		} else {
			if ((pageIterator != null) && !(taillePage != null)) {
				page = new Page(pageIterator, "20", serviceComputer);

			} else {
				page = new Page(startItemPage, lastItemPage, serviceComputer);
			}
		}
		return page;
	}

	public List<ComputerDTO> mapComputerToDTOList(List<Computer> computerList) {
		List<ComputerDTO> computerDTOList = computerList.stream()
				.map(computer -> computerMapper.convertFromComputerToComputerDTO(computer))
				.collect(Collectors.toList());
		return computerDTOList;
	}
}
