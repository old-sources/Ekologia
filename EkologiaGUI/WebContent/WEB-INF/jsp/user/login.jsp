<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ page
	import="coop.ekologia.presentation.controller.user.LoginServlet"%>
<% request.setAttribute("routingFormLogin", LoginServlet.routing(request)); %>
<et:front>
	<div classe="uk-grid">
		<form
			class="uk-form uk-form-horizontal uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center"
			method="post" action="${ routingFormLogin }">
			<h3 class="uk-panel-title">Authentification</h3>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="login">login</label>
				</div>
				<div class="uk-form-controls">
					<input id="login" type="text" name="login" />
				</div>
			</div>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="password">password</label>
				</div>
				<div class="uk-form-controls">
					<input id="password" type="password" name="passw" />
				</div>
			</div>

			<div class="uk-form-row ">
				<button class="uk-button uk-button-primary" type="submit">
					<span class="uk-icon-user"></span>
				</button>
			</div>
		</form>
	</div>
</et:front>