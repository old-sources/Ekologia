<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
	<h1><c:out value="${ wiki.title }" /></h1>
    
    <ul class="small-block-grid-2 medium-block-grid-3 large-block-grid-4">
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
	
	<img src="${ wiki.currentVersion.image }" alt="${ wiki.title }" />
	
	<p>
		<c:out value="${ wiki.currentVersion.htmlContent }" escapeXml="false" />
	</p>
</et:front>