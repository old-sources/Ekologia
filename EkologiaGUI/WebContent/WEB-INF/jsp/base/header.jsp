<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="coop.ekologia.presentation.controller.user.LoginConnectionServlet" %>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main" />
<div style="display: flex; width: 100%; background-color: #b1df00;"">
	<div style="flex-basis: 150px; background-color: white">
		<a href="${pageContext.request.contextPath}/">
			<div style="display: flex; justify-content: center; align-items: center">
				<div style="flex-grow: 1;">
					<img style="height: 60px" class="uk-img-preserve"
						src="https://lh6.googleusercontent.com/-S2uT3YsPddo/VLxG2Po0MBI/AAAAAAAAC9o/Zm9h5p7jyVw/w411-h400-no/logo%2Bseul.png" />
				</div>
				<div style="flex-grow: 1;">
					<h3>accueil</h3>
				</div>
			</div>
		</a>

	</div>
	<div style="flex-grow: 1;">
		<div class="uk-grid">
			<div class="uk-width-1-2">menu1</div>
			<div class="uk-width-1-2">menu2</div>
		</div>
		<div class="uk-grid">
			<div class="uk-width-1-2">menu3</div>
			<div class="uk-width-1-2">menu4</div>
		</div>
	</div>
	<div style="flex-basis: 200px">
		<c:choose>
			<c:when test="${empty connectedUser}">

				<div>
					<a class="uk-button uk-width-1-1"
						href="${pageContext.request.contextPath}/login/createAccount">créer
						un compte</a>
				</div>
				<div>
					<!-- <a class="uk-button uk-width-1-1" href="${uriConnection}">connection</a>-->
					<a class="uk-button uk-width-1-1" href="<%= LoginConnectionServlet.routing(request) %>">connection</a>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<div>
						<c:out value="${connectedUser.email}" />
					</div>
				</div>
				<div>
					<a class="uk-button uk-width-1-1"
						href="${pageContext.request.contextPath}/login/deconnection">déconnection</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>


<c:if test="${! empty connectedUser && connectedUser.admin}">
	<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
			<li><a href="${pageContext.request.contextPath}/admin/userList">comptes</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/pageList">cms</a></li>
			<li><a href="${pageContext.request.contextPath}/group/groupList">liste
					des groupes</a></li>
		</ul>
		<div class="uk-navbar-nav-flip">search</div>
	</nav>
</c:if>