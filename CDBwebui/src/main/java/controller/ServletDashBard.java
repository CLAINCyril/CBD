package controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DTO.ComputerDTO;
import mapper.ComputerMapper;
import modele.Computer;
import persistence.Connexion;
import service.ServiceComputer;

public class ServletDashBard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(ServletDashBard.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageIterator;
		int taillePage = 20;
		int maxPage;
		ServiceComputer service;
		try {
			service = ServiceComputer.getInstance(Connexion.getInstance().getConn());

		int sizeComputer=service.getAllComputer().size();;
		maxPage=sizeComputer/taillePage;
		request.setAttribute("maxPage", maxPage);
		List<ComputerDTO>computerDTOList=new ArrayList<ComputerDTO>();
		List<Computer>computerList=new ArrayList<Computer>();
		if(request.getParameter("taillePage")!=null) {
			taillePage=Integer.parseInt(request.getParameter("taillePage"));
		}
		if(request.getParameter("pageIterator")!=null) {
			pageIterator=Integer.parseInt(request.getParameter("pageIterator"));
			computerList=service.getPageComputer(pageIterator*taillePage,taillePage );
		}
		else {
			pageIterator=0;
			computerList=service.getPageComputer(pageIterator*taillePage,taillePage );
		}
		computerList.stream()
					.forEach(computer->computerDTOList.add(ComputerMapper.convertFromComputerToComputerDTO(computer)));
		
		request.setAttribute("sizeComputer", sizeComputer);
		request.setAttribute("computerList", computerDTOList);
		request.setAttribute("pageIterator", pageIterator);
		request.getRequestDispatcher("views/ListComputer.jsp").forward(request, response);
		
	}
	catch(SQLException e)
	{
		logger.error(e.getMessage());

	}
}
}
