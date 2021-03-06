package com.ecom.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListerClients extends HttpServlet {

	private static final String VUE_LISTE_CLIENTS	= "/WEB-INF/listeClients.jsp";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_CLIENTS).forward(req, resp);
	}	
}
