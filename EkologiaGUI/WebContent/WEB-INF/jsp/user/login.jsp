<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ekologia</title>
</head>
<body>
	La ressource à laquelle vous essayez d'accéder nécessite une authentification
	<form method="post" action="login">
		<div>
			<label for="login">login : </label><input id="login" type="text"
				name="login" />
		</div>
		<div>
			<label for="passw">passw : </label><input id="passw" type="password"
				name="passw" />
		</div>
		<input type="submit"/>
	</form>
</body>
</html>