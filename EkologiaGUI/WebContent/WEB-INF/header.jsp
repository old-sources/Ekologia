<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${! empty connectedUser}">
	<div style="text-align: right;">
		<div style="display: inline-block;">
			Bienvenue&nbsp;
			<c:out value="${connectedUser.prenom}" />
			&nbsp;
			<c:out value="${connectedUser.nom}" />
		</div>
		<div style="display: inline-block;">
			<form action="<%=request.getContextPath()%>/login" method="post">
				<input type="submit" value="deconnecter" name="deconnection">
			</form>
		</div>
	</div>
</c:if>