<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template" %>
<fmt:setLocale value="${ currentLanguage }"/>
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
    <et:breadcrumbs>
        <li><a href="${ routing.getGroup(groupDTO.canonical) }">${ groupDTO.name }</a></li>
        <li><a class="current">Wiki</a></li>
    </et:breadcrumbs>

    <et:group>
        <div class="content wiki">
            <h2>Liste dew wiki de ${groupDTO.name}</h2>
            </br>
            <table class="table-wiki">
                <thead>
                    <tr>
                        <th width="200">Titre</th>
                            <%--<th width="150">Date de dernière modification</th>--%>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="wiki" items="${ wikiList }">
                        <tr>
                            <td>
                                <a href="${ routing.getGroupWikiRead(groupDTO.canonical, wiki.canonical) }">
                                    <c:out value="${ wiki.title }"/>
                                </a>
                            </td>
                                <%--<td>${ wiki.lastVersion.date.format('d/m/Y') }</td>--%>
                        </tr>
                    </c:forEach>
                    <c:if test="${ wikiList.isEmpty() }">
                        <tr>
                            <td colspan="2">Aucun wiki actuellement</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>

            <div>
                <a href="${routing.getGroupWikiCreate(groupDTO.canonical)}">Créer</a>
            </div>
        </div>
    </et:group>
</et:front>