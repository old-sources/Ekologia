<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLanguage}" />
<fmt:setBundle basename="i18n.main" />

<div class="container">
	<div class="row">

		<div class="off-canvas-wrap docs-wrap move-right" data-offcanvas="">
			<div class="inner-wrap">

				<a class="left-off-canvas-toggle" aria-expanded="true"></a>
				<!-- Off Canvas Menu -->

				<aside class="left-off-canvas-menu">
					<!-- whatever you want goes here -->
					<ul class="off-canvas-list">
						<li><label>${groupDTO.name}</label></li>
						<li><a href="${routing.getGroup(groupDTO.canonical)}">Présentation</a></li>
						<li><a href="${routing.getWikiList(groupDTO.canonical)}">Wiki</a></li>
						<%--<li><label>Wiki</label></li>--%>
						<%--<li><a href="">Wiki</a></li>--%>
						<%--<li><a href="#">Wiki Group</a></li>--%>
						<%--<li><label>ABCDEF</label></li>
						<li><a href="#">Partenaire</a></li>
						<li><a href="#">Rejoindre</a></li>--%>
					</ul>
				</aside>
				<!-- main content goes here -->
				<section class="main-section">
					<div class="large-9 columns">
						<jsp:doBody></jsp:doBody>
					</div>
				</section>
				<!-- close the off-canvas menu -->
				<a class="exit-off-canvas"></a>
			</div>
		</div>
	</div>
</div>