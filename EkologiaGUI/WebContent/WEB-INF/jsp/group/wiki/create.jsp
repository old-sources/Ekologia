<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
	<h1>Créer un wiki</h1>
	
    <c:choose>
        <c:when test="${ parent == null }">
            <c:set var="action" value="${ routing.getGroupWikiCreate(groupCanonical) }" />
        </c:when>
        <c:otherwise>
            <c:set var="action" value="${ routing.getGroupWikiCreate(groupCanonical, parent) }" />
        </c:otherwise>
    </c:choose>
    
    <form method="post" action="${ action }" id="form-wiki-create">
		<div>
			<label for="form-wiki-create-title"><fmt:message key="title.label"/></label>
			<div>
				<input type="text" name="title" id="form-wiki-create-title" required />
			</div>
		</div>
		
		<div>
			<label for="form-wiki-create-content"><fmt:message key="content.label"/></label>
			<div>
				<textarea name="content" id="form-wiki-create-content"></textarea>
			</div>
		</div>
		
		<div>
			<label for="form-wiki-create-image"><fmt:message key="image.label"/></label>
			<div>
				<input type="url" name="image" id="form-wiki-create-image" />
			</div>
		</div>
		
		<div>
			<fmt:message key="submit.label" var="submitText" />
			<input type="submit" value="${ submitText }" />
		</div>
	</form>
</et:front>