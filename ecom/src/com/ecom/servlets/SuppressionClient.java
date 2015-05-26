package com.ecom.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.Client;

public class SuppressionClient extends HttpServlet {

	private static final String CHAMP_ID_CLIENT		= "idClient";
	private static final String REDIRECT_VUE		= "listerClients";
	private static final String SESSION_CLIENTS		= "listeClients";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Suppression du client en paramètre de requête dans la Map en session
		HttpSession session = req.getSession();
		Map<String, Client> listeClients = (Map<String, Client>) session.getAttribute(SESSION_CLIENTS);
		listeClients.remove(req.getParameter(CHAMP_ID_CLIENT));
		
		resp.sendRedirect(REDIRECT_VUE);
	}
}
