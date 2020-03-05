package controller;

import java.util.ArrayList;
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

		ServiceComputer service = ServiceComputer.getInstance(Connexion.getInstance().getConn());

		int sizeComputer = service.getAllComputer().size();
		int maxPage = sizeComputer / taillePage;
		request.setAttribute("maxPage", maxPage);
		List<Computer> computerList = new ArrayList<Computer>();
		if (request.getParameter("taillePage") != null) {
			taillePage = Integer.parseInt(request.getParameter("taillePage"));
		}
		if (request.getParameter("pageIterator") != null) {
			pageIterator = Integer.parseInt(request.getParameter("pageIterator"));
		}
		if (request.getParameter("order") != null) {
			computerList = new Page().getPageOrderByName(pageIterator, taillePage);
		}

		if (request.getParameter("search") != null) {
			computerList = new Page().getPageByName(request.getParameter("search"), pageIterator, taillePage);
			request.setAttribute("search", request.getParameter("search"));
		} else {
			computerList = new Page().getPage(pageIterator, taillePage);
		}

		List<ComputerDTO> computerDTOList = computerList.stream()
				.map(computer -> ComputerMapper.convertFromComputerToComputerDTO(computer))
				.collect(Collectors.toList());

		request.setAttribute("sizeComputer", sizeComputer);
		request.setAttribute("computerList", computerDTOList);
		request.setAttribute("pageIterator", pageIterator);
		request.getRequestDispatcher("views/ListComputer.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceComputer service = ServiceComputer.getInstance(Connexion.getInstance().getConn());

		String[] computers = request.getParameter("selection").split(",");
		for (String s : computers) {
			service.deleteComputer(Integer.parseInt(s));
		}
		doGet(request, response);
	}
}
