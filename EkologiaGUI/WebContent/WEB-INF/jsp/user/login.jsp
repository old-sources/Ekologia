<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ page
	import="coop.ekologia.presentation.controller.user.LoginServlet"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.user" />
<%
	request.setAttribute("routingFormLogin",
			LoginServlet.routing(request));
%>
<et:front>
	<div class="row">
		<div class="small-centered large-6 columns small-12">
			<div class="row panel">
				<form method="post" action="${ routingFormLogin }" class="uk-form">
					<div class="row">
						<div class="small-12 columns">
							<h1>
								<fmt:message key="login.title" />
							</h1>
						</div>
						<div class="row">
							<div class="small-3 columns">
								<label for="login" class="right inline"><fmt:message
										key="login.mail.label" /></label>
							</div>
							<div class="small-9 columns error">
								<input id="login" type="text" name="login" />
								<c:if test="${formErrors.errors.get('unknownUser') != null }">
									<small class="error"><fmt:message
											key="login.mail.unknownuser" /></small>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="small-3 columns">
								<label for="password" class="right inline"><fmt:message
										key="login.password.label" /></label>
							</div>
							<div class="small-9 columns error">
								<input id="password" type="password" name="passw" />
								<c:if test="${formErrors.errors.get('badPassword') != null }">
									<small class="error"><fmt:message
											key="login.password.badPassword" /></small>
								</c:if>

							</div>
						</div>
						<div class="row">
							<div class="small-centered small-1 columns">
								<button class="uk-button uk-button-primary" type="submit">
									<span class="uk-icon-user"></span>
								</button>
							</div>
						</div>
				</form>
			</div>
		</div>
	</div>
</et:front>