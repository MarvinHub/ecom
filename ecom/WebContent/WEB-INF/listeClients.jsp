<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des Clients</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
<c:import url="/inc/menu.jsp" charEncoding="UTF-8"/>
<table>
	<tr>
		<th>Nom</th>
		<th>Prenom</th>
		<th>Adresse</th>
		<th>Telephone</th>
		<th>E-mail</th>
		<th>Suppression</th>
	</tr>
	<c:forEach items="${sessionScope.listeClients}" var="element" varStatus="status">
	<c:set value="${element.value}" var="client" />
	<tr <c:if test="${(status.count % 2)==0}">style="background-color : grey"</c:if> >
		<td>${client.nom}</td>
		<td>${client.prenom}</td>
		<td>${client.adresse}</td>
		<td>${client.telephone}</td>
		<td>${client.email}</td>
		<td><a href="<c:url value="/suppressionClient">
				<c:param name="idClient">${client.nom}</c:param>
			</c:url>">Delete</a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>