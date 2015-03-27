<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki" />

<et:front>
	<et:breadcrumbs>
		<li><a href="${ routing.getGroup(wiki.group.canonical) }">${ wiki.group.name }</a></li>
		<c:forEach var="parent" items="${ wiki.getParents(true) }">
			<li><a href="${routing.getGroupWikiRead(wiki.group.canonical, parent.canonical)}">${parent.title}</a></li>
		</c:forEach>
		<li><a class="current">${ wiki.title }</a></li>
	</et:breadcrumbs>

	<et:group>
		<div class="content wiki">
			<h2>
				<c:out value="${ wiki.title }" />
			</h2>

			<ul class="small-block-grid-12" style="text-align: center">
				<c:forEach var="child" items="${ wiki.children }">
					<li><a
						href="${ routing.getGroupWikiRead(wiki.group.canonical, child.canonical) }">
							<c:choose>
								<c:when
									test="${ child.currentVersion.image == null or child.currentVersion.image == '' }">
									<c:out value="${ child.title }" />
								</c:when>
								<c:otherwise>
									<img src="${ child.currentVersion.image }"
										alt="${ child.title }" />
								</c:otherwise>
							</c:choose>
					</a></li>
				</c:forEach>
			</ul>

			<p>
				<c:out value="${ wiki.currentVersion.htmlContent }"
					escapeXml="false" />
			</p>
		</div>
	</et:group>
</et:front>