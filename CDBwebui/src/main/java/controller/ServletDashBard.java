package controller;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.Loggin;
import modele.Computer;
import persistence.Connexion;
import service.ServiceComputer;

public class ServletDashBard extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceComputer service;
		try {
			service = ServiceComputer.getInstance(Connexion.getInstance().getConn());
			List<Computer> computers = service.getAllComputer();
			request.setAttribute("list", computers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Loggin.display(e.getMessage());
		}
		
		
		request.getRequestDispatcher("ListComputer.jsp").forward(request, response);
	}
}
