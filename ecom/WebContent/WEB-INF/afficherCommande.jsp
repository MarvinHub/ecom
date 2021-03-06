<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une commande</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
    	<c:import url="/inc/menu.jsp" charEncoding="UTF-8" />
    	
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ form.resultat }<br />
        	<c:if test="${ !succes }">
        		<a href="<c:url value="creationCommande.jsp" >
        			<c:forEach var="i" begin="1" end="3">
        				<c:param name="param${i}" value="${i}" />
        			</c:forEach></c:url>">
        			Cliquez ici
        		</a> pour modifier vos informations.
        	</c:if>
        </p>
        
        <%-- Puis affichage des données enregistrées dans le bean "commande" transmis par la servlet --%>
        <p>Client</p>
        <%-- Les 5 expressions suivantes accèdent aux propriétés du client, qui est lui-même une propriété du bean commande --%>
        <p>Nom : <c:out value="${ commande.client.nom }" /></p>
        <p>Prénom : <c:out value="${ commande.client.prenom }" /></p>
        <p>Adresse : <c:out value="${ commande.client.adresse }" /></p>
        <p>Numéro de téléphone : <c:out value="${ commande.client.telephone }" /></p>
        <p>Email : <c:out value="${ commande.client.email }" /></p>
        <p>Commande</p>
        <p>Date  : <c:out value="${ commande.date }" /></p> 
        <p>Montant  : <c:out value="${ commande.montant }" /></p> 
        <p>Mode de paiement  : <c:out value="${ commande.modePaiement }" /></p> 
        <p>Statut du paiement  : <c:out value="${ commande.statutPaiement }" /></p> 
        <p>Mode de livraison  : <c:out value="${ commande.modeLivraison }" /></p> 
        <p>Statut de la livraison  : <c:out value="${ commande.statutLivraison }" /></p>
    </body>
</html>