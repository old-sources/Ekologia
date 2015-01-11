<%@tag language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="<%=request.getContextPath()%>/uikit-2.15.0/css/uikit.gradient.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/JQuery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/uikit-2.15.0/js/uikit.min.js"></script>
<meta http-equiv="Content-Type" content="text/html;UTF-8">
<title>Ekologia</title>
</head>
<body>


	<header
		class="uk-panel uk-panel-box uk-panel-box-secondary uk-position-up">
		<jsp:include page="/WEB-INF/jsp/base/header.jsp"></jsp:include>
	</header>
	<section>
		<jsp:doBody></jsp:doBody>
	</section>
	<footer
		class="uk-panel uk-panel-box uk-panel-box-secondary uk-position-bottom uk-width-1-1">
		<div class="uk-text-center">
			<span>TP réulisé dans le cadre des cours de l'IMIE</span>
		</div>
	</footer>




</body>
</html>