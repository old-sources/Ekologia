<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main"/>

<c:if test="${! empty connectedUser}">
	<div style="text-align: right;">
		<div style="display: inline-block;">
			<!-- TODO: display "person firstName + lastName" or "company name" -->
		</div>
		<div style="display: inline-block;">
			<form action="<%=request.getContextPath()%>/login" method="post">
				<fmt:message key="base.header.welcome"/>
				<input type="submit" value="deconnecter" name="deconnection">
			</form>
		</div>
	</div>
</c:if>
<nav class="uk-navbar">
    <ul class="uk-navbar-nav">
        <li><a href="<%=request.getContextPath()%>/"><i class="uk-icon-home"></i></a></li>
        <li><a href="<%=request.getContextPath()%>/admin/userList">les comptes</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/pageList">cms</a></li>
    </ul>
    
</nav>