package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

@Controller
public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(ServletAddComputer.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceCompany serviceCompany = ServiceCompany.getInstance();

		List<Company> companyList = serviceCompany.getAllCompany();
		List<CompanyDTO> companysDTO = companyList.stream()
				.map(company -> CompanyMapper.convertFromCompanyToCompanyDTO(company)).collect(Collectors.toList());

		request.setAttribute("companysDTO", companysDTO);
		request.getRequestDispatcher("views/addComputer.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		int companyId = Integer.parseInt(request.getParameter("companyId"));

		CompanyDTO companyDTO = new CompanyDTO(companyId);
		ComputerDTO computerDTO = new ComputerDTO(computerName, introduced, discontinued, companyDTO);

		Computer computer = ComputerMapper.getInstance().fromComputerDTOToComputer(computerDTO);

		ServiceComputer serviceComputer = ServiceComputer.getInstance();
		serviceComputer.persisteComputer(computer);
		try {
			response.sendRedirect("ListComputer");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
