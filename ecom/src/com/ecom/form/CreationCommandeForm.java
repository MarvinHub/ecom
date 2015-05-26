package com.ecom.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.ecom.beans.Client;
import com.ecom.beans.Commande;

public class CreationCommandeForm {

	private static final String CHAMP_MODE_LIVRAISON		= "modeLivraisonCommande";
	private static final String CHAMP_MODE_PAIEMENT			= "modePaiementCommande";
	private static final String CHAMP_MONTANT				= "montantCommande";
	private static final String CHAMP_STATUT_LIVRAISON		= "statutLivraisonCommande";
	private static final String CHAMP_STATUT_PAIEMENT		= "statutPaiementCommande";
	private static final String CHAMP_NEW_CLIENT			= "newClient";
	private static final String CHAMP_CHOIX_CLIENT			= "choixClient";
	private static final String SESSION_CLIENTS				= "listeClients";
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	private String resultat;
	
	public Commande creerCommande(HttpServletRequest req){
		
		Commande commande = new Commande();
		CreationClientForm clientForm = new CreationClientForm();
		HttpSession session = req.getSession();
		
		// Création d'un nouveau client
		if(req.getParameter(CHAMP_NEW_CLIENT).equals("Oui")){
			// Récupération et validation de la partie Client
			commande.setClient(clientForm.inscrireClient(req));
			erreurs=clientForm.getErreurs();
		} else {
			// Réutilisation du client en session
			Map<String, Client> listeClients = (Map<String, Client>)session.getAttribute(SESSION_CLIENTS);
			Client client = listeClients.get(req.getParameter(CHAMP_CHOIX_CLIENT));
			commande.setClient(client);
		}
		
		commande.setDate(new DateTime());

		// Récupération champs Commande
		String modeLivraison = getValeurChamp(req, CHAMP_MODE_LIVRAISON);
		String modePaiement = getValeurChamp(req, CHAMP_MODE_PAIEMENT);
		String montant = getValeurChamp(req, CHAMP_MONTANT);
		String statutLivraison = getValeurChamp(req, CHAMP_STATUT_LIVRAISON);
		String statutPaiement = getValeurChamp(req, CHAMP_STATUT_PAIEMENT);
		
		try{
			validationObligatoire(modeLivraison);
			validationChaineMin(modeLivraison);
		} catch(Exception e){
			setErreurs(CHAMP_MODE_LIVRAISON, e.getMessage());
		}
		commande.setModeLivraison(modeLivraison);
		
		try{
			validationObligatoire(modePaiement);
			validationChaineMin(modePaiement);
		} catch(Exception e){
			setErreurs(CHAMP_MODE_PAIEMENT, e.getMessage());
		}
		commande.setModePaiement(modePaiement);
		
		try{
			validationChaineMin(statutLivraison);
		} catch(Exception e){
			setErreurs(CHAMP_STATUT_LIVRAISON, e.getMessage());
		}
		commande.setStatutLivraison(statutLivraison);
		
		try{
			validationChaineMin(statutPaiement);
		} catch(Exception e){
			setErreurs(CHAMP_STATUT_PAIEMENT, e.getMessage());
		}
		commande.setStatutPaiement(statutPaiement);
		
		try{
			validationMontant(montant);
		} catch(Exception e){
			setErreurs(CHAMP_MONTANT, e.getMessage());
		}
		
		try{
			commande.setMontant(Double.parseDouble(montant));
		} catch(NumberFormatException e){
			// ignorer;
		}
		
		if(erreurs.isEmpty()){
			resultat="Creation commande réussie !!";
		} else {
			resultat="Echec de la création !!";
		}
		
		return commande;
	}
	
	/* Méthodes de validation avec exception */
	private void validationChaineMin(String value) throws Exception{
		
		if(value != null){
			if(value.trim().length() < 3){
				throw new Exception("Ce champ doit contenir au moins 2 caractères.");
			}
		}
	}
	
	private void validationObligatoire(String value) throws Exception{
		
		if(value == null){
			throw new Exception("Merci de renseigner ce champ.");
		}
	}
	
	private void validationMontant(String value) throws Exception{
		
		if(value != null){
			try{
				Double.parseDouble(value);
			} catch (NumberFormatException e){
				throw new Exception("Ce champ doit contenir un nombre.");
			}
		} else {
			throw new Exception("Merci de renseigner ce champ.");
		}
	}
	
	private String getValeurChamp(HttpServletRequest req, String nomChamp){
		
		String valeur=req.getParameter(nomChamp);
		if(valeur == null || valeur.trim().length() == 0){
			return null;
		} else {
			return valeur;
		}
	}
	
	// Getters and Setters
	private void setErreurs(String champ, String msg){
		erreurs.put(champ, msg);
	}
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
	
	public String getResultat(){
		return resultat;
	}
}
