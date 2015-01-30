<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.main" />

<et:front>
	${testInject.text}
	<div class="row">
		<c:out value="${ page.html }" escapeXml="false" />
	</div>

	<div class="row">
		<div id="fb-root"></div>
		<script>
			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id))
					return;
				js = d.createElement(s);
				js.id = id;
				js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v2.0";
				fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));
		</script>
		
		<div class="fb-like-box"
			data-href="https://www.facebook.com/pages/Ekologia/274611082672069?fref=ts"
			data-colorscheme="light" data-width="500" data-show-faces="false" data-header="false"
			data-stream="true" data-show-border="false"></div>
			
		<div class="fb-like-box"
			data-href="https://www.facebook.com/constituante.ekologia"
			data-colorscheme="light" data-width="500" data-show-faces="false" data-header="false"
			data-stream="true" data-show-border="false"></div>
	</div>
</et:front>