package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import exception.Loggin;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import persistence.Connexion;
import service.ServiceCompany;
import service.ServiceComputer;

public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceCompany serviceCompany;
		List<Company> companys = new ArrayList<Company>();
		List<CompanyDTO>companysDTO = new ArrayList<CompanyDTO>();

		try {
			serviceCompany = ServiceCompany.getInstance(Connexion.getInstance().getConn());

			companys = serviceCompany.getAllCompany();
			companys.stream().forEach(company->companysDTO.add(
							   CompanyMapper.comvertFromCompanyToCompanyDTO(company)));
			
			request.setAttribute("companysDTO", companysDTO);
			request.getRequestDispatcher("views/addComputer.jsp").forward(request, response);
		
		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}


	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ServiceComputer serviceComputer;
		Computer computer;
		ComputerDTO computerDTO = new ComputerDTO();
		CompanyDTO companyDTO = new CompanyDTO();

		
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued  = request.getParameter("discontinued");
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		companyDTO.setId(companyId);
		
		computerDTO.setDiscontinued(discontinued);
		computerDTO.setIntroduced(introduced);
		computerDTO.setCompany(companyDTO);
		computerDTO.setName(computerName);
		
		computer = ComputerMapper.getInstance().fromComputerDTOToComputer(computerDTO);
		try {
			serviceComputer = ServiceComputer.getInstance(Connexion.getInstance().getConn());
			serviceComputer.persisteComputer(computer);
			response.sendRedirect("ListComputer");

		} catch (SQLException e) {
			Loggin.display("in servlet add computer : "+e.getMessage());
		}
	}
}
