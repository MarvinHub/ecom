package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.Commande;
import com.ecom.form.CreationCommandeForm;

public class CreationCommande extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID 		= 1L;	
	private static final String ATT_COMMANDE		= "commande";
	private static final String ATT_FORM 			= "form";
	private static final String VUE_AFF_COMMANDE	= "/WEB-INF/afficherCommande.jsp";
	private static final String VUE_BUILD_COMMANDE	= "/WEB-INF/creationCommande.jsp";
	private static final String SESSION_COMMANDES	= "listeCommandes";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_BUILD_COMMANDE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		CreationCommandeForm form = new CreationCommandeForm();
		HttpSession session = req.getSession();
		
		// Validation et transmission des données
		Commande commande = form.creerCommande(req);
		req.setAttribute(ATT_COMMANDE, commande);
		req.setAttribute(ATT_FORM, form);
		
		// Ajout de la commande en session si validation des données
		if(form.getErreurs().isEmpty()){
			Map<String, Commande> listeCommandes = (Map<String, Commande>) session.getAttribute(SESSION_COMMANDES);
			
			// Initialisation de la Map en session
			if(listeCommandes == null){
				listeCommandes = new HashMap<String, Commande>();
				session.setAttribute(SESSION_COMMANDES, listeCommandes);
			}
			
			// Ajout de la commande en session
			if(!listeCommandes.containsKey(commande.getDate().toString()))
				listeCommandes.put(commande.getDate().toString(), commande);
		}
	
		// Affichage du client si données validées
		if(!form.getErreurs().isEmpty()){
			this.getServletContext().getRequestDispatcher(VUE_BUILD_COMMANDE).forward(req, resp);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_AFF_COMMANDE).forward(req, resp);
		}
	}
}
