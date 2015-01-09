<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
	<h1><c:out value="${ wiki.title }" /></h1>
	
	<img src="${ wiki.currentVersion.image }" alt="${ wiki.title }" />
	
	<p>
		<c:out value="${ wiki.currentVersion.content }" />
	</p>
</et:front>