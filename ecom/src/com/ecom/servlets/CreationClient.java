package com.ecom.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.beans.Client;
import com.ecom.form.CreationClientForm;

public class CreationClient extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID 		= 1L;

	private static final String ATT_CLIENT		= "client";
	private static final String ATT_FORM		= "form";
	private static final String VUE_AFF_CLIENT	= "/WEB-INF/afficherClient.jsp";
	private static final String VUE_BUILD_CLIENT= "/WEB-INF/creationClient.jsp";
	private static final String SESSION_CLIENTS	= "listeClients";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_BUILD_CLIENT).forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		Client client;
		CreationClientForm form = new CreationClientForm();
		HttpSession session = req.getSession();		
		
		// Validation et transmission des données
		client = form.inscrireClient(req);
		req.setAttribute(ATT_CLIENT, client);
		req.setAttribute(ATT_FORM, form);
		
		// Ajout d'un client à la liste des clients en session si validation des données
		if(form.getErreurs().isEmpty()){
			Map<String, Client> listeClients = (Map<String, Client>)session.getAttribute(SESSION_CLIENTS);
			
			// Initialisation de la Map en session
			if(listeClients == null){
				listeClients = new HashMap<String, Client>();
				session.setAttribute(SESSION_CLIENTS, listeClients);
			} 
			
			// Ajout du nouveau client en session
			if(!listeClients.containsKey(client.getNom()))
				listeClients.put(client.getNom(), client);
		}
	
		// Affichage du client si données validées
		if(form.getErreurs().isEmpty()){
			this.getServletContext().getRequestDispatcher(VUE_AFF_CLIENT).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(VUE_BUILD_CLIENT).forward(req, resp);
		}
	}
}