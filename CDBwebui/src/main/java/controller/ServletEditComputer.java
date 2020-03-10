package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import DTO.CompanyDTO;
import DTO.ComputerDTO;
import Validator.ValidatorComputer;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import persistence.ConnexionTest;
import service.ServiceCompany;
import service.ServiceComputer;

@Controller
public class ServletEditComputer extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(ServletEditComputer.class);
	private static ComputerMapper computerMapper = new ComputerMapper();
	
	@Autowired
	ServiceCompany serviceCompany;
	@Autowired
	ServiceComputer serviceComputer;

	public void init(ServletConfig config){
		try {
			super.init(config);
		} catch (ServletException servletException) {
			logger.debug(servletException.getMessage());
		}
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int computerid = Integer.parseInt(request.getParameter("computerid"));

		List<Company> companyList = serviceCompany.getAllCompany();
		List<CompanyDTO> companysDTO = companyList.stream()
				.map(company -> CompanyMapper.convertFromCompanyToCompanyDTO(company)).collect(Collectors.toList());

		ComputerDTO computerDTO = computerMapper
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
		Computer computer = computerMapper.fromComputerDTOToComputer(computerDTO);
		if (new ValidatorComputer().discontinuedAfterIntroduced(computer.getDiscontinued(), computer.getIntroduced())) {

			serviceComputer.updateComputer(computer);
			
		} else {
			doGet(request, response);
		}

	}

}
