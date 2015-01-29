<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
          <!-- HTML editor CSS and JavaScript -->
        <link rel="stylesheet"
			href="${pageContext.request.contextPath}/uikit-2.15.0/css/components/htmleditor.css">
        <script
			src="${pageContext.request.contextPath}/uikit-2.15.0/js/components/htmleditor.js"></script>

	</jsp:attribute>
	<jsp:body>
	<c:choose>
	<c:when test="${empty user}">
		<c:set var="urlForm"
					value="${fn:replace(routing.pageForm,'*','create')}"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="urlForm"
					value="${fn:replace(routing.pageForm,'*','update')}/${user.id}"></c:set>
	</c:otherwise>
</c:choose>
	<div classe="uk-grid">
	
		
		<form
				class="uk-form uk-form-horizontal uk-panel uk-panel-box uk-panel-header uk-width-4-5 uk-container-center"
				method="post">
			<h3 class="uk-panel-title">

				<c:choose>
					<c:when test="${empty page}">
								créer une page
							</c:when>
					<c:otherwise>
								modifier une page
							</c:otherwise>
				</c:choose>
			</h3>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">url</label>
				</div>
				<div class="uk-form-controls">
					<input id="name" type="text" value="${page.url}" name="url" />
				</div>

			</div>
			<div class="uk-form-row ">
				<div>
					<label class="uk-form-label" for="name">html</label>
				</div>
				<div class="uk-form-controls">
				
					<textarea data-uk-htmleditor="{height:300}" name="html">${page.html}</textarea>
				
				</div>

			</div>
			<div class="uk-form-row">
				<div class="uk-grid">
					<div class="uk-button-group uk-container-center">

						<c:url var="urlList" value="/admin/pageList"></c:url>
						<a href="${urlList}" class="uk-button uk-button-danger"><span
								class="uk-icon-backward"></span></a>

						<c:choose>
							<c:when test="${empty page}">
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
