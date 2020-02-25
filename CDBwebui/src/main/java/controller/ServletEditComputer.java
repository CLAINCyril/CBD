package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import persistence.Connexion;
import service.ServiceCompany;
import service.ServiceComputer;

public class ServletEditComputer extends HttpServlet{
	private int computerid = 0;

	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		ServiceComputer serviceComputer;
		ServiceCompany serviceCompany;
		List<CompanyDTO> companysDTO = new ArrayList<CompanyDTO>();
		ComputerDTO computerDTO = new ComputerDTO();

		List<Company> companyList=new ArrayList<Company>();

		computerid = Integer.parseInt(request.getParameter("computerid"));
		try {
			serviceCompany = ServiceCompany.getInstance(Connexion.getInstance().getConn());
			
			companyList = serviceCompany.getAllCompany();
			companyList.stream()
					   .forEach(company->companysDTO.add(
							   CompanyMapper.comvertFromCompanyToCompanyDTO(company)));
			
		} catch (SQLException e1) {
			Loggin.display("In get list id company : "+e1.getMessage());
		}

		try {
			serviceComputer = ServiceComputer.getInstance(Connexion.getInstance().getConn());
			computerDTO = ComputerMapper.getInstance()
					.convertFromComputerToComputerDTO(serviceComputer.getComputer(computerid).get());
		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
		
		request.setAttribute("companysDTO", companysDTO);
		request.setAttribute("computerDTO", computerDTO);
		request.getRequestDispatcher("views/EditComputer.jsp").forward(request, response);

	}

}
