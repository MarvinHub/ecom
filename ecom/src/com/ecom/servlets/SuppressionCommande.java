package com.ecom.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.Commande;

public class SuppressionCommande extends HttpServlet {

	private static final String CHAMP_ID_COMMANDE	= "idCommande";
	private static final String REDIRECT_VUE		= "listerCommandes";
	private static final String SESSION_COMMANDES	= "listeCommandes";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		// Suppression de la commande en paramètre de requête dans la Map en session
		HttpSession session = req.getSession();
		Map<String, Commande> listeCommandes = (Map<String, Commande>) session.getAttribute(SESSION_COMMANDES);
		listeCommandes.remove(req.getParameter(CHAMP_ID_COMMANDE));
		
		resp.sendRedirect(REDIRECT_VUE);
	}

	
}
