<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template" %>
<fmt:setLocale value="${ currentLanguage }"/>
<fmt:setBundle basename="i18n.cms"/>

<et:front>
    <et:breadcrumbs>
        <li><a class="current">
            <fmt:message key="menus.title"/>
        </a></li>
    </et:breadcrumbs>

    <div class="content">
        <h2><fmt:message key="menus.title"/></h2>
        <ul>
            <c:forEach var="role" items="${ roles }">
                <li><a href="${ routing.getMenuManagement(role.key) }">${ role.value }</a></li>
            </c:forEach>
        </ul>
    </div>
</et:front>