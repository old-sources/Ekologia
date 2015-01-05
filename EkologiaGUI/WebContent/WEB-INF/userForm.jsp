<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<et:front>
	<div classe="uk-grid">
		<form
			class="uk-form uk-form-horizontal uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center" method="post">
			<h3 class="uk-panel-title">

				<c:choose>
					<c:when test="${empty user}">
								créer un user
							</c:when>
					<c:otherwise>
								modifier un user
							</c:otherwise>
				</c:choose>
			</h3>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">nom</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${user.nom}" name="nom"/>
				</div>

			</div>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">prenom</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${user.prenom}" name="prenom" />
				</div>

			</div>

			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">email</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${user.eMail}" name="email" />
				</div>

			</div>
			<div class="uk-form-row">
				<div class="uk-grid">
					<div class="uk-button-group uk-container-center">

						<c:url var="urlList" value="/admin/userList"></c:url>
						<a href="${urlList}" class="uk-button uk-button-danger"><span
							class="uk-icon-backward"></span></a>

						<c:choose>
							<c:when test="${empty user}">
								<button class="uk-button uk-button-success" type="submit">
									<span class="uk-icon-plus"></span>
								</button>
							</c:when>
							<c:otherwise>
								<button class="uk-button uk-button-primary" type="submit">
									<span class="uk-icon-pencil"></span>
								</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

			</div>

		</form>
	</div>
</et:front>
