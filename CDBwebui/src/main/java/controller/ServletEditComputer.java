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
import modele.Computer;
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
		List<Company> companyList=new ArrayList<Company>();

		ComputerDTO computerDTO = new ComputerDTO();

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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ServiceComputer serviceComputer;
		Computer computer;
		ComputerDTO computerDTO = new ComputerDTO();
		CompanyDTO companyDTO = new CompanyDTO();

		int computerId = Integer.parseInt(request.getParameter("computerId"));
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued  = request.getParameter("discontinued");
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		companyDTO.setId(companyId);
		
		
		computerDTO.setId(computerId);
		computerDTO.setDiscontinued(discontinued);
		computerDTO.setIntroduced(introduced);
		computerDTO.setCompany(companyDTO);
		computerDTO.setName(computerName);
		
		computer = ComputerMapper.getInstance().fromComputerDTOToComputer(computerDTO);
		System.out.println(computer);
		try {
			serviceComputer = ServiceComputer.getInstance(Connexion.getInstance().getConn());
			serviceComputer.updateComputer(computer);

		} catch (SQLException e) {
			Loggin.display("in servlet add computer : "+e.getMessage());
		}
	}

}
