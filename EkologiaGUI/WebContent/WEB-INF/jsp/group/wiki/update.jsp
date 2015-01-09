<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
	<h1>Modifier le wiki <c:out value="${ wiki.title }" /></h1>
	
	<form method="post" action="${ formRoute }" id="form-wiki-update">
		<div>
			<label for="form-wiki-update-content"><fmt:message key="content.label"/></label>
			<div>
				<textarea name="content" id="form-wiki-update-content"><c:out value="${ wiki.currentVersion.content }" /></textarea>
			</div>
		</div>
		
		<div>
			<label for="form-wiki-update-image"><fmt:message key="image.label"/></label>
			<div>
				<input type="url" name="image" id="form-wiki-update-image" value="${ wiki.currentVersion.image }" />
			</div>
		</div>
		
		<div>
			<fmt:message key="submit.label" var="submitText" />
			<input type="submit" value="${ submitText }" />
		</div>
	</form>
</et:front>