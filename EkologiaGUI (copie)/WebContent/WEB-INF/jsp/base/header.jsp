<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main" />

<c:if test="${! empty connectedUser}">
	<div style="text-align: right;">
		<div style="display: inline-block;">
			<!-- TODO: display "person firstName + lastName" or "company name" -->
		</div>
		<div style="display: inline-block;"></div>
	</div>
</c:if>
<nav class="uk-navbar">
	<ul class="uk-navbar-nav">
		<li><a href="<%=request.getContextPath()%>/"><i
				class="uk-icon-home"></i></a></li>
		<c:if test="${! empty connectedUser && connectedUser.admin}">
			<li><a href="<%=request.getContextPath()%>/admin/userList">comptes</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/pageList">cms</a></li>
		</c:if>

	</ul>
	<div class="uk-navbar-flip">
		<ul class="uk-navbar-nav">
			<c:choose>
				<c:when test="${empty connectedUser}">
					<li>
						<form action="<%=request.getContextPath()%>/login" method="post">
							<input type="submit" name="connection" value="connection" />
						</form>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<form action="<%=request.getContextPath()%>/login" method="post">
							<fmt:message key="base.header.welcome" />
							<c:out value="${connectedUser.email}"></c:out>
							<button type="submit" name="deconnection">déconnecter</button>
						</form>
					</li>
					</form>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>

</nav>