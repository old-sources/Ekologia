<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template" %>
<fmt:setLocale value="${ currentLanguage }"/>
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
    <et:breadcrumbs>
        <li><a href="${ routing.getGroup(groupDTO.canonical) }">${ groupDTO.name }</a></li>
        <li><a class="current"><fmt:message key="create.h1"/></a></li>
    </et:breadcrumbs>

    <et:group>
        <div class="row">
            <h1><fmt:message key="create.h1"/></h1>
        </div>

        <c:choose>
            <c:when test="${ parent == null }">
                <c:set var="action" value="${ routing.getGroupWikiCreate(groupCanonical) }"/>
            </c:when>
            <c:otherwise>
                <c:set var="action" value="${ routing.getGroupWikiCreate(groupCanonical, parent) }"/>
            </c:otherwise>
        </c:choose>

        <form class="uk-form" method="post" action="${action}">
            <div class="row panel">
                <div class="row">
                    <div class="small-2 columns">
                        <label for="form-wiki-create-title" class="right inline"><fmt:message
                                key="title.label"/></label>
                    </div>
                    <fmt:message key="title.placeholder" var="message"/>
                    <div class="small-10 columns left inline">
                        <input type="text" name="title" placeholder="${ message }" id="form-wiki-create-title"/>
                    </div>
                </div>

                <div class="row">
                    <div class="small-2 columns">
                        <label for="form-wiki-create-image" class="right inline"><fmt:message
                                key="image.label"/></label>
                    </div>
                    <fmt:message key="image.placeholder" var="message"/>
                    <div class="small-10 columns left inline">
                        <input type="text" name="image" id="form-wiki-create-image"
                               placeholder="${ message }"
                               value="/Ekologia/image/wiki-default.png"/>
                    </div>
                </div>

                <div class="row ">
                    <div class="small-2 columns">
                        <label for="form-wiki-create-content" class="right inline"><fmt:message
                                key="content.label"/></label>
                    </div>
                    <fmt:message key="content.label" var="message"/>
                    <div id="left-label" class="small-10 columns left inline">
                    <textarea name="content" placeholder="${ message }" rows="12"
                              id="form-wiki-create-content"></textarea>
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
    </et:group>
</et:front>