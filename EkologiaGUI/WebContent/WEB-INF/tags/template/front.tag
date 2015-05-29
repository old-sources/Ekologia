<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLanguage}" />
<%-- <fmt:setBundle basename="i18n.main" />--%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;UTF-8">

<link
	href="${pageContext.request.contextPath}/uikit-2.15.0/css/uikit.gradient.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/foundation.css"
	media="all" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/uikit.min.css" media="all" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/uikit.almost-flat.css"
	media="all" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" media="all" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jstree/style.min.css" media="all" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/JQuery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/uikit.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/foundation.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jstree/jstree.min.js"></script>
<jsp:invoke fragment="head" />
<title>Ekologia</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/base/header.jsp"></jsp:include>
	</header>
	<section class="corps">
		<div class="row">
			<jsp:doBody></jsp:doBody>
		</div>
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