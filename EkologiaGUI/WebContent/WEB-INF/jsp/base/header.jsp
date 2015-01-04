<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tags/i18n.tld" prefix="i18n"%>

<i18n:i18n key="base.header.welcome" />
<c:if test="${! empty connectedUser}">
	<div style="text-align: right;">
		<div style="display: inline-block;">
			<i18n:i18n key="base.header.welcome" />
			<!-- TODO: display "person firstName + lastName" or "company name" -->
		</div>
		<div style="display: inline-block;">
			<form action="<%=request.getContextPath()%>/login" method="post">
				<input type="submit" value="deconnecter" name="deconnection">
			</form>
		</div>
	</div>
</c:if>