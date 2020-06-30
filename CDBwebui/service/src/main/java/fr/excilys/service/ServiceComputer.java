package fr.excilys.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.DTO.ComputerDTO;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Computer;
import fr.excilys.persistence.DAOComputer;

enum EVITEINJECTION {
	COMPUTER, INTRODUCED, DISCONTINUED, COMPANY;
	
	static String value(String string) {
		switch (string) {
		case "COMPUTER":
			return (" computer.name");
		case "INTRODUCED":
			return (" computer.introduced");
		case "DISCONTINUED":
			return (" computer.discontinued");
		case "COMPANY":
			return (" company.name");
		default:
			return (" computer.name");
		}
	}
}

@Service
public class ServiceComputer {

	private DAOComputer daoComputer;
	private ComputerMapper computerMapper;
	
	public ServiceComputer(DAOComputer daoComputer, ComputerMapper computerMapper) {
		this.daoComputer = daoComputer;
		this.computerMapper = computerMapper;

	}

	@Transactional
	public void persisteComputer(Computer computer) {
		daoComputer.persisteComputer(computer);
	}

	public int getlength() throws JDBCConnectionException {
		try {
			return (int) daoComputer.countComputer();
		} catch (JDBCConnectionException jdbcConnectionException) {
			throw jdbcConnectionException;
		}
	}


	@Transactional
	public void deleteComputer(int id) {
		daoComputer.deleteComputer(id);
	}
	
	@Transactional
	public void deleteComputerList(List<String> listIdComputer) {
		daoComputer.deleteComputerListe(listIdComputer);
	}

	@Transactional
	public Optional<Computer> getComputer(int Id) {
		return daoComputer.getComputer(Id);
	}

	@Transactional
	public List<Computer> getPageComputer(int offset, int number) {
		return daoComputer.getPageComputer(offset, number);
	}

	@Transactional
	public void updateComputer(Computer computer) {
		daoComputer.updateComputer(computer);
	}

	@Transactional
	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		return daoComputer.getPageComputerByName(search, offset, number);
	}

	@Transactional
	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		order = EVITEINJECTION.value(order.toUpperCase());
		return daoComputer.getPageComputerOrder(offset, number, order);
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
			page = new Page(pageIterator, taillePage, this);

		} else {
			if ((pageIterator != null) && !(taillePage != null)) {
				page = new Page(pageIterator, "20", this);

			} else {
				page = new Page(startItemPage, lastItemPage, this);
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

	public ComputerDTO mapFromComputerToDTO(Computer computer) {
		
		return computerMapper.convertFromComputerToComputerDTO(computer);
	}

	public Computer mapComputerDTOToComputer(ComputerDTO computerDTO) {

		return (computerMapper.fromComputerDTOToComputer(computerDTO));
	}
}
