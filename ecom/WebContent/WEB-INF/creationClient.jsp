<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Création d'un Client</title>
		<link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
	<body>
		<c:import url="/inc/menu.jsp" charEncoding="UTF-8" />
		<div>
			<p class="info">${ form.resultat }</p>
			<form method="post" action="<c:url value="creationClient" />" >
				<c:import url="/inc/client_fieldset.jsp" charEncoding="UTF-8" />
				<input type="submit" value="valider" />
				<input type="reset" value="Remettre à zéro" /> 
				<br />
			</form>
		</div>
	</body>
</html>