<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="coop.ekologia.DTO.user.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<et:front>
	<div classe="uk-grid">
		<div
			class="uk-panel uk-panel-box uk-panel-header uk-width-1-2 uk-container-center uk-panel-box-secondary">
			<h3 class="uk-panel-title">
				<div class="uk-grid">
					<div class="uk-width-8-10">liste des groupes</div>
					<div class="uk-width-2-10">
						<button
							class="createButton uk-button uk-button-mini uk-button-primary uk-button-success"
							data-id="create">
							<span class="uk-icon-plus"></span>
						</button>
					</div>
				</div>
			</h3>

			<table class="uk-table uk-table-hover uk-table-striped ">
				<tr>
					<th>Nom</th>
					<th></th>
				</tr>
				<c:forEach items="${groups}" var="group">
					<tr>
						<td><c:out value="${group.name}" /></td>

						<td><button
								class="editButton uk-button uk-button-mini  uk-button-primary"
								data-id="${group.id}">
								<span class="uk-icon-pencil"></span>
							</button>
							<button
								class="deleteButton uk-button uk-button-mini uk-button-danger"
								data-id="${group.id}">
								<span class="uk-icon-trash"></span>
							</button> 
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</et:front>
<script>
	$(".createButton")
			.on(
					"click",
					function(e) {
						var userFormUrl = "${ routing.getAdminGroupCreate() }"
						//console.log(userFormUrl);
						window.location.href = userFormUrl;
					});
	$(".editButton")
			.on(
					"click",
					function(e) {
						var userFormUrl = "${ routing.getAdminGroupUpdate(null) }" // empty id because generate with JS.
								+ ($(this).data("id"))
						//console.log(userFormUrl);
						window.location.href = userFormUrl;
					});
	$(".deleteButton")
			.on(
					"click",
					function(e) {
						var userFormUrl = "${ routing.getAdminGroupDelete(null) }"
								+ ($(this).data("id"))
						//console.log(userFormUrl);
						window.location.href = userFormUrl;
					});
</script>
