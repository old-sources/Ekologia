<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki" />

<et:front>
	<div class="row">
		<ul class="breadcrumbs">
			<li><a href="${routing.homepage}">Ekologia</a></li>
			<c:forEach var="parent" items="${ wiki.getParents(true) }">
				<li><a href="${routing.getGroupWikiRead(wiki.group.canonical, parent.canonical)}">${parent.title}</a></li>
			</c:forEach>
			<li><a href="current">${ wiki.title }</a></li>
		</ul>
	</div>
	<div class="container">
		<div class="row">

			<div class="off-canvas-wrap docs-wrap move-right" data-offcanvas="">
				<div class="inner-wrap">

					<a class="left-off-canvas-toggle" aria-expanded="true"></a>
					<!-- Off Canvas Menu -->

					<aside class="left-off-canvas-menu">
						<!-- whatever you want goes here -->
						<ul class="off-canvas-list">
							<li><label>Wiki</label></li>
							<li><a href="#">Wiki Admin</a></li>
							<li><a href="#">Wiki Group</a></li>
							<li><label>ABCDEF</label></li>
							<li><a href="#">Partenaire</a></li>
							<li><a href="#">Rejoindre</a></li>
						</ul>
					</aside>
					<!-- main content goes here -->
					<section class="main-section">
						<div class="large-9 columns">
							<div class="content wiki">
								<h2>
									<c:out value="${ wiki.title }" />
								</h2>

								<ul class="small-block-grid-12" style="text-align: center">
									<c:forEach var="child" items="${ wiki.children }">
										<li>
											<a href="${ routing.getGroupWikiRead(wiki.group.canonical, child.canonical) }">
												<c:choose>
													<c:when test="${ child.currentVersion.image == null or child.currentVersion.image == '' }">
														<c:out value="${ child.title }" />
													</c:when>
													<c:otherwise>
														<img src="${ child.currentVersion.image }" alt="${ child.title }" />
													</c:otherwise>
												</c:choose>
											</a>
										</li>
									</c:forEach>
								</ul>

								<p>
									<c:out value="${ wiki.currentVersion.htmlContent }" escapeXml="false" />
								</p>
							</div>
						</div>
					</section>
					<!-- close the off-canvas menu -->
					<a class="exit-off-canvas"></a>
				</div>
			</div>
		</div>
	</div>
</et:front>