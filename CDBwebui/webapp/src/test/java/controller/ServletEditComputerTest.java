//package controller;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//public class ServletEditComputerTest extends Mockito{
//	private HttpServletRequest request;
//	private HttpServletResponse response;
//	private RequestDispatcher requestDispatcher;
//	private ServletEditComputer servletEditComputer;
//
//	@Before
//	public void init() {
//		servletEditComputer = new ServletEditComputer();
//		request = mock(HttpServletRequest.class);
//		response = mock(HttpServletResponse.class);
//		requestDispatcher = mock(RequestDispatcher.class);
//	}
//	
//	@Test
//	public void testServletEditComputer()throws ServletException, IOException {
//		when(request.getRequestDispatcher(eq("views/EditComputer.jsp"))).thenReturn(requestDispatcher);
//		servletEditComputer.doGet(request, response);
//	}
//}
