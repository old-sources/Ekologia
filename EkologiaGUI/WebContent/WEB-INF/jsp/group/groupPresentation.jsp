<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
	<h1>${ group.name }</h1>
    
    <p>${ group.description }</p>
    
    <ul>
        <c:forEach var="wiki" items="${ wikiList }">
            <li>
                <a href="${ routing.getGroupWikiRead(group.canonical, wiki.canonical) }">
                    <c:out value="${ wiki.title }" />
                </a>
            </li>
        </c:forEach>
    </ul>
</et:front>