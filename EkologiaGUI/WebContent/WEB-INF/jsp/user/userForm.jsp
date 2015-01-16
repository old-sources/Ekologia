<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<et:front>
	<div classe="uk-grid">
		<c:choose>
			<c:when test="${empty user}">
				<c:set var="urlForm"
					value="${pageContext.request.contextPath}/admin/userForm/create"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="urlForm"
					value="${pageContext.request.contextPath}/admin/userForm/update/${user.id}"></c:set>
			</c:otherwise>
		</c:choose>

		<form id="userForm"
			class="uk-form uk-form-horizontal uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center"
			method="post" action="${urlForm}">
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
					<label class="uk-form-label" for="name">email</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${user.email}" name="email" />
				</div>

			</div>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">password</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${user.password}"
						name="password" />
				</div>

			</div>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">role</label>
				</div>
				<div class="uk-form-controls">
					<%--<input id="role" type="text" value="${user.roles[1]}" name="role" />--%>
					<select form="userForm" id="role" type="text"
						value="${user.roles[0]}" name="role">
						<c:set var="selected" value=""></c:set>
						<c:if test="${empty user.roles[0]}">
							<c:set var="selected" value="selected"></c:set>
						</c:if>
						<option value="" ${selected}></option>
						<c:set var="selected" value=""></c:set>
						<c:if test="${fn:contains(user.roles[0],'admin')}">
							<c:set var="selected" value="selected"></c:set>
						</c:if>
						<option value="admin" ${selected}>admin</option>
					</select>
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
