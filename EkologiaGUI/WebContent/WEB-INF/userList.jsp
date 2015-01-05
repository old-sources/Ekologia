<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="coop.ekologia.DTO.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL"
	value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<et:front>
	<div classe="uk-grid">
		<div
			class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
			<h3 class="uk-panel-title">
				<div class="uk-grid">
					<div class="uk-width-8-10">liste des users</div>
					<div class="uk-width-2-10">
						<button
							class="editButton uk-button uk-button-mini uk-button-primary uk-button-success"
							data-id="create">
							<span class="uk-icon-plus"></span>
						</button>
					</div>
				</div>
			</h3>

			<table class="uk-table uk-table-hover uk-table-striped ">
				<tr>
					<th>nom</th>
					<th>prenom</th>
					<th>email</th>
					<th></th>
				</tr>
				<c:forEach items="${filteredUsers}" var="user">
					<tr>
						<td><c:out value="${user.nom}" /></td>
						<td><c:out value="${user.prenom}" /></td>
						<td><c:out value="${user.eMail}" /></td>
						<td><button
								class="editButton uk-button uk-button-mini  uk-button-primary"
								data-id="${user.id}">
								<span class="uk-icon-pencil"></span>
							</button>
							<button
								class="editButton uk-button uk-button-mini uk-button-danger"
								data-id="${user.id}">
								<span class="uk-icon-trash"></span>
							</button> <%-- same with dropdown 
							<div class="uk-button-dropdown" data-uk-dropdown>

								<!-- This is the element toggling the dropdown -->

								<button class="uk-button uk-button-primary uk-button-mini">
									<i class="uk-icon-caret-down"></i>
								</button>


								<!-- This is the dropdown -->
								<div class="uk-dropdown">
									<ul class="uk-nav uk-nav-dropdown">

										<li>
											<button
												class="editButton uk-button uk-button-mini uk-button-primary"
												data-id="${user.id}">
												<span class="uk-icon-pencil"></span>edit
											</button>
										</li>
										<li><button
												class="editButton uk-button uk-button-mini uk-button-danger"
												data-id="${user.id}">
												<span class="uk-icon-times"></span>delete
											</button></li>
									</ul>
								</div>

							</div></td>
							--%>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</et:front>
<script>
	$(function() {
		$(".editButton")
				.on(
						"click",
						function(e) {
							var userFormUrl = "${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"
									+ "/admin/userForm/" + ($(this).data("id"))
							//console.log(userFormUrl);
							window.location.href = userFormUrl;
						});
		console.log("hello");
	})
</script>