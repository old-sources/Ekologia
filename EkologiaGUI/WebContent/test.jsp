<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="<%=request.getContextPath()%>/uikit-2.15.0/css/uikit.gradient.min.css"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>Ekologia</title>
</head>
<body>
	<%
		for (int i = 0; i < 10; i++) {
	%>
	<%=i%>
	<%
		}
	%>
</body>
</html>