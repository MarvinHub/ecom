package com.ecom.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ecom.beans.Client;

public class CreationClientForm {

	private static final String CHAMP_NOM 		= "nomClient";
	private static final String CHAMP_PRENOM 	= "prenomClient";
	private static final String CHAMP_ADRESSE	= "adresseClient";
	private static final String CHAMP_EMAIL		= "emailClient";
	private static final String CHAMP_TEL		= "telephoneClient";
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	private String resultat;
	
	public Client inscrireClient(HttpServletRequest req){
		
		// Récupération des paramètres de requête
		Client client = new Client();
		String nom = getValeurChamp(req, CHAMP_NOM);
		String prenom = getValeurChamp(req, CHAMP_PRENOM);
		String adresse = getValeurChamp(req, CHAMP_ADRESSE);
		String email = getValeurChamp(req, CHAMP_EMAIL);
		String tel = getValeurChamp(req, CHAMP_TEL);
		
		try{
			validationNom(nom);
		} catch(Exception e){
			setErreur(CHAMP_NOM, e.getMessage());
		}
		client.setNom(nom);
		
		try{
			validationPrenom(prenom);
		} catch(Exception e){
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		client.setPrenom(prenom);
		
		try{
			validationAdresse(adresse);
		} catch(Exception e){
			setErreur(CHAMP_ADRESSE, e.getMessage());
		}
		client.setAdresse(adresse);
		
		try{
			validationEmail(email);
		} catch(Exception e){
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		client.setEmail(email);
		
		try{
			validationTelephone(tel);
		} catch(Exception e){
			setErreur(CHAMP_TEL, e.getMessage());
		}
		client.setTelephone(tel);
		
		if(erreurs.isEmpty()){
			resultat="Création client réussie.";
		} else{
			resultat="Erreur lors de la création de l'utilisateur.";
		}
		
		return client;
	}
	
	/* Méthodes de validation avec Exception */
	private void validationNom(String nom) throws Exception{
		if(nom != null){
			if(nom.length() < 2){
				throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new Exception("Merci de renseigner votre nom.");
		}
	}
	
	private void validationPrenom(String prenom) throws Exception{
		if(prenom == null || prenom.length() < 2 ){
			throw new Exception("Le champ prénom doit contenir au moins 2 caractères.");
		}
	}
	
	private void validationAdresse(String adresse) throws Exception{
		if(adresse != null){
			if(adresse.length() < 10){
				throw new Exception("Le champ adresse doit contenir au moins 10 caractères.");
			}
		} else{
			throw new Exception("Merci de renseigner une adresse.");
		}
	}
	
	private void validationTelephone(String tel) throws Exception{
		if(tel != null){
			if (tel.length() < 4){
				throw new Exception("Le champ téléphone doit contenir au moins 4 caractères.");
			} else if(!tel.matches("[0-9]*")){
				throw new Exception("Le champ téléphone doit uniquement contenir des chiffres.");
			}
		} else {
			throw new Exception("Merci de renseigner un numéro de téléphone.");
		}
	}
	
	private void validationEmail(String email) throws Exception{
		if(!email.matches("([^.@]+)(\\.[^.@])*@([^.@]+\\.)+([^.@]+)")){
			throw new Exception("Merci de renseigner une adresse email correcte.");
		}
	}
	
	// Récupération d'un champ élagué (renvoie null si valeur vide)
	private String getValeurChamp(HttpServletRequest req, String champ){
		
		String valeur = req.getParameter(champ);
		if(valeur == null || valeur.trim().length() == 0){
			return null;
		} else {
			return valeur.trim();
		}
	}

	/* Getters and Setters */
	private void setErreur(String champ, String msg){
		erreurs.put(champ, msg);
	}
	
	public String getResultat(){
		return resultat;
	}
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
}