<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template" %>
<fmt:setLocale value="${ currentLanguage }"/>
<fmt:setBundle basename="i18n.groupwiki"/>

<et:front>
    <et:breadcrumbs>
        <li><a class="current">${ groupDTO.name }</a></li>
    </et:breadcrumbs>

    <et:group>
        <div class="group row">
            <div class="columns small-12">
                <h2>${ groupDTO.name }</h2>

                <div id="row">
                    <div class="small-3 columns">
                        <img class="logo" src="${ groupDTO.icon }" alt="${ groupDTO.name }"/>
                            <%--<a class="button tiny expand radius margin-btn-5" href="#">Suivre</a>--%>
                            <%--<a class="button tiny expand radius" href="#">Rejoindre</a>--%>
                    </div>
                    <div class="small-9 columns">
                        <p>${ groupDTO.description }</p>
                        <h4>Liste des wiki</h4>
                        <ul class="small-block-grid-4">
                            <c:forEach var="wiki" items="${ wikiList }">
                                <li>
                                    <a href="${ routing.getGroupWikiRead(groupDTO.canonical, wiki.canonical) }">
                                        <img src="${ wiki.currentVersion.image }" alt="${wiki.title}"/>
                                        <c:out value="${ wiki.title }"/>
                                    </a>
                                </li>
                            </c:forEach>
                            <c:if test="${ wikiList.isEmpty() }">
                                <li>
                                    Aucun wiki actuellement
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                    <%--<div class="row">
                        <h3 class="small-12 columns">Wiki Group</h3>
                        <ul class="small-block-grid-5">
                            <li>
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                Wiki les papillons
                            </li>
                            <li>
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                Wiki les abeilles
                            </li>
                            <li>
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                Wiki les scarabés
                            </li>
                            <li>
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                Wiki les arraignés
                            </li>
                            <li>
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                Wiki les mouches
                            </li>
                        </ul>
                    </div>--%>
                    <%--<div class="row">
                        <h3 class="small-12 columns">Groupes liés</h3>
                        <ul class="small-block-grid-5 vertical-align-bottom">
                            <li data-uk-tooltip="{pos:'top-left'}" title="Sauve ta baleine!">
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                <ul class="button-group radius">
                                    <li><a href="#" class="button very-tiny">X</a></li>
                                    <li><a href="#" class="button very-tiny">+</a></li>
                                    <li><a href="#" class="button very-tiny">M</a></li>
                                </ul>
                            </li>
                            <li data-uk-tooltip="{pos:'top-left'}" title="Une chance pour deux">
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                <ul class="button-group radius">
                                    <li><a href="#" class="button very-tiny">X</a></li>
                                    <li><a href="#" class="button very-tiny">+</a></li>
                                    <li><a href="#" class="button very-tiny">M</a></li>
                                </ul>
                            </li>
                            <li data-uk-tooltip="{pos:'top-left'}" title="Trois camions dans un train">
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                <ul class="button-group radius">
                                    <li><a href="#" class="button very-tiny">X</a></li>
                                    <li><a href="#" class="button very-tiny">+</a></li>
                                    <li><a href="#" class="button very-tiny">M</a></li>
                                </ul>
                            </li>
                            <li data-uk-tooltip="{pos:'top-left'}" title="Elevage citoyoecolonomique">
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                <ul class="button-group radius">
                                    <li><a href="#" class="button very-tiny">X</a></li>
                                    <li><a href="#" class="button very-tiny">+</a></li>
                                    <li><a href="#" class="button very-tiny">M</a></li>
                                </ul>
                            </li>
                            <li data-uk-tooltip="{pos:'top-left'}" title="Eco-responsable, c'est moi,c'est toi!">
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                <ul class="button-group radius">
                                    <li><a href="#" class="button very-tiny">X</a></li>
                                    <li><a href="#" class="button very-tiny">+</a></li>
                                    <li><a href="#" class="button very-tiny">M</a></li>
                                </ul>
                            </li>
                            <li data-uk-tooltip="{pos:'top-left'}" title=" Un sida nommé humanité">
                                <a href="#"><img src="D:\Photo\littlefinger.jpg"/></a>
                                <ul class="button-group radius">
                                    <li><a href="#" class="button very-tiny">X</a></li>
                                    <li><a href="#" class="button very-tiny">+</a></li>
                                    <li><a href="#" class="button very-tiny">M</a></li>
                                </ul>
                            </li>
                        </ul>

                    </div>--%>
            </div>
        </div>
    </et:group>
</et:front>