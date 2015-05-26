<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="inc/style.css">
<title>Liste des Commandes</title>
</head>
<body>
<c:import url="/inc/menu.jsp" charEncoding="UTF-8" />
<table>
	<tr>
		<th>Nom Client</th>
		<th>Mode Paiement</th>
		<th>Statut Paiement</th>
		<th>Mode Livraison</th>
		<th>Statut Livraison</th>
		<th>Montant</th>
		<th>Date</th>
		<th>Suppression</th>
	</tr>
	
	<c:forEach items="${sessionScope.listeCommandes}" var="element" varStatus="status">
	<c:set value="${element.value}" var="commande" />
	<tr <c:if test="${(status.count % 2)==0}">style="background-color : grey"</c:if> >
		<td>${commande.client.nom}</td>
		<td>${commande.modePaiement}</td>
		<td>${commande.statutPaiement}</td>
		<td>${commande.modeLivraison}</td>
		<td>${commande.statutLivraison}</td>
		<td>${commande.montant}</td>
		<td>${commande.date}</td>
		<td><a href="<c:url value="/suppressionCommande">
		 				<c:param name="idCommande">${commande.date}</c:param>
		 			</c:url>">Delete</a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>