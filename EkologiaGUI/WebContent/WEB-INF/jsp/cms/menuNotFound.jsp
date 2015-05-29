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
            <fmt:message key="menu.title"/>
        </a></li>
    </et:breadcrumbs>

    <div class="content">
        <h2>Not found</h2>
        <p>The menu was not found</p>
    </div>
</et:front>