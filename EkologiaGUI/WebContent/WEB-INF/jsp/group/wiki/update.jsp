<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
	<et:breadcrumbs>
		<li><a href="${ routing.getGroup(wiki.group.canonical) }">${ wiki.group.name }</a></li>
		<c:forEach var="parent" items="${ wiki.getParents(true) }">
			<li><a href="${routing.getGroupWikiRead(wiki.group.canonical, parent.canonical)}">${parent.title}</a></li>
		</c:forEach>
		<li><a class="current">
			<fmt:message key="update.h1"/>
			<c:out value="${ wiki.title }" />
		</a></li>
	</et:breadcrumbs>

	<et:group>
		<div class="content wiki">
			<h2>
				<fmt:message key="update.h1"/>
				<c:out value="${ wiki.title }" />
			</h2>

			<div class="row">
				<h1><fmt:message key="create.h1"/></h1>
			</div>

			<form method="post" action="${ formRoute }" id="form-wiki-update" class="uk-form">
				<div class="row panel">
					<div class="row">
						<div class="small-2 columns">
							<label for="form-wiki-update-image" class="right inline"><fmt:message key="image.label"/></label>
						</div>
						<fmt:message key="image.placeholder" var="message" />
						<div class="small-10 columns left inline">
							<input type="url" name="image" id="form-wiki-update-image"
								   placeholder="${ message }"
								   value="/Ekologia/image/wiki-default.png"/>
						</div>
					</div>

					<div class="row ">
						<div class="small-2 columns">
							<label for="form-wiki-update-content" class="right inline"><fmt:message key="content.label" /></label>
						</div>
						<fmt:message key="content.label" var="message" />
						<div id="left-label" class="small-10 columns left inline">
                    		<textarea name="content" placeholder="${ message }" rows="12"
							  id="form-wiki-update-content"></textarea>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="small-12 columns left inline">
						<ul class="right inline">
							<button type="submit" class="button tiny">
								<fmt:message key="submit.label"/>
							</button>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</et:group>
</et:front>