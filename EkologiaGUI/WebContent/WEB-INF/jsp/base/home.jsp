<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main" />
<div style="display: flex; width: 100%; background-color: #b1df00;">
	<div class="uk-grid" data-uk-grid-margin="">
		<div class="uk-width-1-1 uk-text-center">
			<img src="https://lh4.googleusercontent.com/-dUQf94H_zmw/VLMhMFbf8-I/AAAAAAAAC9M/OipBy0et2Jk/w1000-h400-no/logo%2Btexte%2Bbanniere.png"
style="height: 200px" />
			<h3 class="uk-heading-large">${ base.home.welcomemessage }</h3>
			<a href="./page/testimport">import page historique</a>
		</div>
	</div>
</div>


