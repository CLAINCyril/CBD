package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.ComputerDTO;
import mapper.ComputerMapper;
import modele.Computer;
import persistence.Connexion;
import service.Page;
import service.ServiceComputer;

public class ServletDashBard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageIterator = 0;
		int taillePage = 20;
		List<Computer> computerList = new ArrayList<Computer>();
		
		if (request.getParameter("taillePage") != null) {
			taillePage = Integer.parseInt(request.getParameter("taillePage"));
		}
		if (request.getParameter("pageIterator") != null) {
			pageIterator = Integer.parseInt(request.getParameter("pageIterator"));
		}
		Page page = new Page(pageIterator, taillePage);
		
		computerList = getPage(request, page);
		List<ComputerDTO> computerDTOList = computerList.stream()
				.map(computer -> ComputerMapper.convertFromComputerToComputerDTO(computer))
				.collect(Collectors.toList());
		
		
		setAttributeRequest(request, pageIterator, page, computerDTOList);
		request.getRequestDispatcher("views/ListComputer.jsp").forward(request, response);

	}

	private void setAttributeRequest(HttpServletRequest request, int pageIterator, Page page,
			List<ComputerDTO> computerDTOList) {
		request.setAttribute("search", request.getParameter("search"));
		request.setAttribute("sizeComputer", page.getSizeComputer());
		request.setAttribute("computerList", computerDTOList);
		request.setAttribute("pageIterator", pageIterator);
	}

	private List<Computer> getPage(HttpServletRequest request, Page page) {
		List<Computer> computerList;
		if (request.getParameter("order") != null) {
			computerList = page.getPageOrderByName();
		}
		if (!("".equals(request.getParameter("search"))) && (request.getParameter("search") != null)) {
			computerList = page.getPageByName(request.getParameter("search"));
			
		} else {
			computerList = page.getPage();
		}
		return computerList;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceComputer service = ServiceComputer.getInstance(Connexion.getInstance().getConn());
		List<String> computers = Arrays.asList(request.getParameter("selection").split(","));

		service.deleteComputerList(computers);
		doGet(request, response);
	}

}
