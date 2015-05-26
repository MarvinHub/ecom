<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Création Commande</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
		<script type="text/javascript">
			function hideClientForm(){
				var clientForm = document.getElementById("clientFieldset");
				var clientSelect = document.getElementById("choixFieldset");
				clientForm.style.display = 'none';
				clientSelect.style.display = "block";
			}
			
			function displayClientForm(){
				var clientForm = document.getElementById("clientFieldset");
				var clientSelect = document.getElementById("choixFieldset");
				clientForm.style.display = 'block';
				clientSelect.style.display = 'none';
			}
		</script>
	</head>
	<body>
		<c:import url="/inc/menu.jsp" charEncoding="UTF-8" />
		<div>
			<p class="info">${ form.resultat }</p>
            <form method="post" action="<c:url value="/creationCommande" />" >
            	
            	<fieldset>
	            	<label for="newClient">Nouveau client ? <span class="requis">*</span></label>
	            	<input type="radio" name="newClient" value="Oui" onclick="displayClientForm()" />Oui
	            	<input type="radio" name="newClient" value="Non" checked="checked" onclick="hideClientForm()" />Non<br />
	            </fieldset>
	            
	            <fieldset id="choixFieldset">
	            	<label for="choixClient">Selectionnez un client :</label>
	            	<select name="choixClient">
	            		<c:forEach items="${sessionScope.listeClients}" var="element">
	            			<option value="${element.key}">${element.value.nom} ${element.value.prenom}</option>
	            		</c:forEach>
	            	</select>
	            </fieldset>
            	
            	<c:set var="client" value="${commande.client}" scope="request"/>
                <c:import url="/inc/client_fieldset.jsp" charEncoding="UTF-8"/>
                <script type="text/javascript">hideClientForm();</script>
                
                <fieldset>
                    <legend>Informations commande</legend>
                    
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="dateCommande" name="dateCommande" value="<c:out value="${commande.date}" />" size="20" maxlength="20" disabled />
                    <span>${ form.erreurs["dateCommande"] }</span>
                    <br />
                    
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="<c:out value="${commande.montant}" />" size="20" maxlength="20" />
                    <span>${ form.erreurs["montantCommande"] }</span>
                    <br />
                    
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="<c:out value="${commande.modePaiement}" />" size="20" maxlength="20" />
                    <span>${ form.erreurs["modePaiementCommande"] }</span>
                    <br />
                    
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="<c:out value="${commande.statutPaiement}" />" size="20" maxlength="20" />
                    <span>${ form.erreurs["statutPaiementCommande"] }</span>
                    <br />
                    
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="<c:out value="${commande.modeLivraison}" />" size="20" maxlength="20" />
                    <span>${ form.erreurs["modeLivraisonCommande"] }</span>
                    <br />
                    
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="<c:out value="${commande.statutLivraison}" />" size="20" maxlength="20" />
                    <span>${ form.erreurs["statutLivraisonCommande"] }</span>
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
	</body>
</html>