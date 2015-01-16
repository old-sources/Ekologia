<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<nav class="uk-navbar">
	<ul class="uk-navbar-nav">
		<li><a href="${pageContext.request.contextPath}/"><i
				class="uk-icon-home"></i></a></li>
		<c:if test="${! empty connectedUser && connectedUser.admin}">
			<li><a href="${pageContext.request.contextPath}/admin/userList">comptes</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/pageList">cms</a></li>
		</c:if>
	</ul>
	<div class="uk-navbar-flip">
		<ul class="uk-navbar-nav">
			<c:if
				test="${! fn:containsIgnoreCase(pageContext.request.requestURI, 'login')}">
				<c:choose>
				<c:when test="${empty connectedUser}">
					<li><a href="${pageContext.request.contextPath}/login/connection">connection</a>
					</li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/login/deconnection">deconnection</a>
					</li>
				</c:otherwise>
			</c:choose></c:if>
		</ul>
	</div>
</nav>