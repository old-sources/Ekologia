<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main" />
<et:front>
	<div class="row">
		<div class="small-12 large-6 columns">
			<c:out value="${ page.html }" escapeXml="false" />
		</div>

		<div class="small-12 large-6 columns">
			<div id="fb-root"></div>
			<script>
				(function(d, s, id) {
					var js, fjs = d.getElementsByTagName(s)[0];
					if (d.getElementById(id))
						return;
					js = d.createElement(s);
					js.id = id;
					js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v2.3";
					fjs.parentNode.insertBefore(js, fjs);
				}(document, 'script', 'facebook-jssdk'));
			</script>
			<div class="fb-page"
				data-href="https://www.facebook.com/constituante.ekologia"
				data-hide-cover="false" data-show-facepile="false"
				data-show-posts="true">
				<div class="fb-xfbml-parse-ignore">
					<blockquote cite="https://www.facebook.com/constituante.ekologia">
						<a href="https://www.facebook.com/constituante.ekologia">Ekologia</a>
					</blockquote>
				</div>
			</div>
		</div>
	</div>

</et:front>