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
		<form id="editForm"
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
				<div>
					<label class="uk-form-label" for="name">Description</label>
				</div>
				<div class="uk-form-controls">
					<textarea id="description" name="description">${group.description}</textarea>
				</div>
				<div>
					<label class="uk-form-label" for="name">Icône du groupe</label>
				</div>
				<div class="uk-form-controls">
					<input id="icon" type="file" value="${group.icon}" name="icon" />
				</div>
				<div>
					<label class="uk-form-label" for="name">Administrateur du groupe</label>
				</div>
				<div class="uk-form-controls">
					<select name="user" id="user">
						<c:choose>
							<c:when test="${empty group}">
								<c:forEach items="${users}" var="user">
	                              	<option value="${user.id}">${user.email}</option>
	                            </c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${users}" var="user">
									<c:choose>
										<c:when test="${group.firstAdmin eq user}">
		                              		<option selected value="${user.id}">${user.email}</option>
		                              	</c:when>
		  								<c:otherwise>
		  									<option value="${user.id}">${user.email}</option>
		  								</c:otherwise>
	  								</c:choose>
                              </c:forEach>
                              
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				<c:choose>
					<c:when test="${not empty group}">
						<div>
							<label class="uk-form-label" for="name">Gestion des utilisateurs inscrits dans ce groupe</label>
						</div>
						<div class="uk-form-controls">
							<select id='availableUsers' size='10'>
								<c:forEach items="${users}" var="user">
		                            <option value="${user.id}">${user.email}</option>
		                        </c:forEach>
							</select>
							<button class="addButton uk-button">
									<span class="uk-icon-plus"></span>
							</button>
							<button class="removeButton uk-button">
									<span class="uk-icon-trash"></span>
							</button>
							<select name="userInGroup" id='selectedUsers' multiple size='10'>
								<c:forEach items="${usersInGroup}" var="userInGroup">
	                              	<option value="${userInGroup.id}">${userInGroup.email}</option>
	                            </c:forEach>
							</select>
							
						</div>			
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>

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
								<button id="editFormButton" class="uk-button uk-button-primary"
										type="submit">
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
<script>
	$(function() {
		$(".addButton").on(
				"click",
				function(e) {
					e.preventDefault();
					var ai = document.getElementById("availableUsers");
					var si = document.getElementById("selectedUsers");
					for (i = 0; i < ai.options.length; i++) {
						if (ai.options[i].selected) {
							var opt = ai.options[i];
							var bool = true;

							for (y = 0; y < si.options.length; y++) {
								if (si.options[y].value == opt.value) {
									bool = false;
								}
							}

							if (bool) {
								si.options[si.options.length] = new Option(
										opt.innerHTML, opt.value);
							}
						}
					}
				});
		$(".removeButton").on("click", function(e) {
			e.preventDefault();
			var si = document.getElementById("availableUsers");
			var ai = document.getElementById("selectedUsers");
			for (i = 0; i < ai.options.length; i++) {
				if (ai.options[i].selected) {
					var opt = ai.options[i];
					ai.options[i] = null;
					i = i - 1;
				}
			}
		});

		$("#editFormButton").on("click", function(e) {
			$('#selectedUsers').find('option').each(function() {
					$(this).attr("selected","selected");
				});
		});
	})
</script>
