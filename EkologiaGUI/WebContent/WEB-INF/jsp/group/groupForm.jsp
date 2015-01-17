<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<et:front>

	<jsp:attribute name="head">
		<!-- Codemirror and marked dependencies -->
        <link rel="stylesheet"
			href="${pageContext.request.contextPath}/codemirror/lib/codemirror.css">
        <script
			src="${pageContext.request.contextPath}/codemirror/lib/codemirror.js"></script>
        <script
			src="${pageContext.request.contextPath}/codemirror/mode/markdown/markdown.js"></script>
        <script
			src="${pageContext.request.contextPath}/codemirror/addon/mode/overlay.js"></script>
        <script
			src="${pageContext.request.contextPath}/codemirror/mode/xml/xml.js"></script>
        <script
			src="${pageContext.request.contextPath}/codemirror/mode/gfm/gfm.js"></script>
        <script
			src="${pageContext.request.contextPath}/marked/marked.min.js"></script>

	</jsp:attribute>
	<jsp:body>
	<div classe="uk-grid">
		<form
				class="uk-form uk-form-horizontal uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center"
				method="post">
			<h3 class="uk-panel-title">

				<c:choose>
					<c:when test="${empty group}">
								créer un groupe
							</c:when>
					<c:otherwise>
								modifier un groupe
							</c:otherwise>
				</c:choose>
			</h3>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">Nom du groupe</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${group.name}" name="name" />
				</div>

			</div>
			<div class="uk-form-row">
				<div class="uk-grid">
					<div class="uk-button-group uk-container-center">

						<c:url var="urlList" value="/group/groupList"></c:url>
						<a href="${urlList}" class="uk-button uk-button-danger"><span
								class="uk-icon-backward"></span></a>

						<c:choose>
							<c:when test="${empty group}">
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
	</jsp:body>
</et:front>