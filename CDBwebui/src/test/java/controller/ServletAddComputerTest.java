package controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import service.ServiceCompany;

public class ServletAddComputerTest extends Mockito{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher requestDispatcher;
	private ServletAddComputer servletAddcomputer;
	
	
	@Before
	public void init() {
		 servletAddcomputer = new ServletAddComputer();
		 request = mock(HttpServletRequest.class);
		 response = mock(HttpServletResponse.class);
		 requestDispatcher = mock(RequestDispatcher.class);
	}
	
	
	@Test
	public void testServletAddComputer() throws ServletException, IOException{
		when(request.getRequestDispatcher(eq("views/addComputer.jsp"))).
		thenReturn(requestDispatcher);
		servletAddcomputer.doGet(request, response);
	}

}
