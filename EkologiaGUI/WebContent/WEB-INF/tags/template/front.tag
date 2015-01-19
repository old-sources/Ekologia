<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLanguage}" />
<fmt:setBundle basename="i18n.main" />
<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/uikit-2.15.0/css/uikit.gradient.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<c:choose>
	<c:when test="${! empty connectedUser && connectedUser.admin}">
		<link href="${pageContext.request.contextPath}/css/admin.css"
			rel="stylesheet" type="text/css" />
	</c:when>
	<c:otherwise>
		<link href="${pageContext.request.contextPath}/css/normal.css"
			rel="stylesheet" type="text/css" />

	</c:otherwise>
</c:choose>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/JQuery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/uikit-2.15.0/js/uikit.min.js"></script>
<jsp:invoke fragment="head" />
<meta http-equiv="Content-Type" content="text/html;UTF-8">
<title>Ekologia</title>
</head>
<body>


	<header>
		<jsp:include page="/WEB-INF/jsp/base/header.jsp"></jsp:include>
	</header>
	<section>
		<jsp:doBody></jsp:doBody>
	</section>
	<footer>
		<div class="uk-panel uk-panel-box uk-panel-box-secondary uk-width-1-1">
			<div class="uk-text-center">
				<span>Ekologia</span>
			</div>
		</div>
	</footer>
</body>
</html>