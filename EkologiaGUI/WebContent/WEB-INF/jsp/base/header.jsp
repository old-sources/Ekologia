<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="coop.ekologia.presentation.controller.group.GroupListServlet"%>
<%@ page import="coop.ekologia.presentation.controller.cms.PageListServlet"%>
<%@ page import="coop.ekologia.presentation.controller.user.UserListServlet"%>
<%@ page import="coop.ekologia.presentation.controller.user.LoginConnectionServlet"%>
<%@ page import="coop.ekologia.presentation.controller.user.LoginDeconnectionServlet"%>
<%@ page import="coop.ekologia.presentation.controller.user.RegistrationServlet"%>
<%@ page import="coop.ekologia.presentation.controller.cms.PageServlet"%>
<%@ page import="coop.ekologia.presentation.controller.user.UserCreateServlet"%>
<%@ page import="coop.ekologia.presentation.controller.user.UserListServlet"%>
<%@ page import="coop.ekologia.presentation.controller.cms.PageListServlet"%>
<%@ page import="coop.ekologia.presentation.controller.group.GroupListServlet"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main" />

<div class="header">
	<div class="header-degrade">
		<!-- .row implique max-width: 62.5rem, soit quelque chose de limité ^^' -->
		<!-- Equivalent à .container .row de Bootstrap -->
		<div class="row">
			<!-- Equivalent à .col-lg-2 de Bootstrap -->
			<div class="logo large-2 columns">
				<!--blablabla-->
				<a href="${routing.homepage}"></a>
			</div>
			<div class="large-8 columns">
				<!-- Ici, .row réinitialise une grille de 12 colonnes, donc la largeur des colonnes sera moins importante que pour le .row parent (cf. ligne 14) -->
				<!-- Les sauts de ligne mettent en évidence les différentes lignes. -->
				<ul class="row no-bullet">
					<li class="large-4 columns">
						<a class="header-link" href="<%=PageServlet.routing(request, "qui-sommes-nous")%>">
							Qui sommes-nous?
						</a>
					</li>
					<li class="large-4 columns">
						<a class="header-link" href="<%=PageServlet.routing(request, "communaute")%>">
							La communauté
						</a>
					</li>
					<li class="large-4 columns">
						<a class="header-link" href="<%=PageServlet.routing(request, "forum-ouvert")%>">
							Forum ouvert
						</a>
					</li>
				</ul>
			</div>

			<div class="large-2 columns">
				<!-- styles à exporter en css plus tard, juste des tests rapides ici -->
				<div style="border: 1px solid #aaa; border-radius: 2px; padding: 0">
					<h5 style="text-align: center; padding: 5px; margin: 0; background-color: #eee">Mon compte</h5>
					<hr style="margin: 0; padding: 2px" />
					<c:choose>
						<c:when test="${empty connectedUser}">
							<ul class="no-bullet" style="font-size: 12px; margin: 5px">
								<li><a href="<%=RegistrationServlet.routing(request)%>"> Créer un compte </a></li>
								<li><a href="${routing.connection}"> Se connecter </a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<div style="padding: 0 5px"><c:out value="${connectedUser.email}" /></div>
							<ul class="no-bullet" style="font-size: 12px; margin: 5px">
								<li><a class="uk-button uk-width-1-1" href="${routing.deconnection}">déconnexion</a></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>

	<div class="contain-to-grid sticky">
		<div class="nav-bar-color">
			<nav class="top-bar" data-topbar role="navigation">
				<ul class="title-area">
					<li class="name">
						<h1>
							<a href="${routing.homepage}">Ekologia</a>
						</h1>
					</li>
				</ul>

				<section class="top-bar-section">
					<!-- TODO: Active page management -->
					<ul class="right">
						<c:if test="${ !empty connectedUser && connectedUser.admin }">
							<li class="has-dropdown not-click">
								<a href="#">Administration</a>
								<ul class="dropdown">
									<li><a href="${routing.userList}">Comptes</a></li>
									<li><a href="${routing.pageList}">CMS</a></li>
									<li><a href="<%=GroupListServlet.routing(request)%>">Liste des groupes</a></li>
								</ul>
							</li>
						</c:if>
					</ul>
					<ul class="left">
						<!--<li class="active"><a href="${routing.homepage}">Home</a></li>-->
						<li><a href="${routing.homepage}">Home</a></li>
					</ul>
				</section>
			</nav>
		</div>
	</div>
</div>