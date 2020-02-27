package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import persistence.Connexion;
import service.ServiceCompany;
import service.ServiceComputer;

public class ServletEditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int computerid = Integer.parseInt(request.getParameter("computerid"));

		ServiceCompany serviceCompany = ServiceCompany.getInstance(Connexion.getInstance().getConn());

		List<Company> companyList = serviceCompany.getAllCompany();
		List<CompanyDTO> companysDTO = companyList.stream().map(company -> CompanyMapper.convertFromCompanyToCompanyDTO(company)).collect(Collectors.toList());
		
		ServiceComputer serviceComputer = ServiceComputer.getInstance(Connexion.getInstance().getConn());
		ComputerDTO computerDTO = ComputerMapper.getInstance()
				.convertFromComputerToComputerDTO(serviceComputer.getComputer(computerid).get());

		request.setAttribute("companysDTO", companysDTO);
		request.setAttribute("computerDTO", computerDTO);
		request.getRequestDispatcher("views/EditComputer.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int computerId = Integer.parseInt(request.getParameter("computerId"));
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		int companyId = Integer.parseInt(request.getParameter("companyId"));

		CompanyDTO companyDTO = new CompanyDTO(companyId);
		ComputerDTO computerDTO = new ComputerDTO(computerId, computerName, introduced, discontinued, companyDTO);
		Computer computer = ComputerMapper.getInstance().fromComputerDTOToComputer(computerDTO);
		ServiceComputer serviceComputer = ServiceComputer.getInstance(Connexion.getInstance().getConn());
		serviceComputer.updateComputer(computer);

	}

}
