<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLanguage}" />
<fmt:setBundle basename="i18n.main" />

<div class="row">
	<ul class="breadcrumbs">
		<li><a href="${routing.homepage}">Ekologia</a></li>
		<jsp:doBody></jsp:doBody>
	</ul>
</div>