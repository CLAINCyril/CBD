package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEditComputer extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
	    req.setCharacterEncoding("utf-8");

	    resp.setContentType("text/plain");
	    resp.setCharacterEncoding("utf-8");
	    resp.getWriter().write("Hello " + "!");
	}
}
