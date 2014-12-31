<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="coop.ekologia.DTO.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ekologia</title>
</head>
<body>
	<%@ include file="./header.jspf"%>
	<table>
		<c:forEach items="${filteredUsers}" var="user">
			<tr>
				<td><c:out value="${user.nom}" /></td>
				<td><c:out value="${user.prenom}"/></td>
				<td><c:out value="${user.eMail}"/></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>