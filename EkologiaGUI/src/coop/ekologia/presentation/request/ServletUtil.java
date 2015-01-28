package coop.ekologia.presentation.request;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Named("util")
@RequestScoped
public class ServletUtil {

	@Inject
	private HttpServletRequest httpServletRequest;

	public String getText() {
		return "test injection ok";
	}

	/**
	 * Returns an absolute url (depends on all context variables).
	 * 
	 * @param request
	 *            The current request
	 * @param route
	 *            The route to send the user (ex:
	 *            {@code /group/wiki/group-1/wiki-1})
	 * @return The absolute url to given route
	 */
	protected String getUrl(String route) {
		StringBuilder result = new StringBuilder();
		String contextPath = httpServletRequest.getContextPath();

		result.append(contextPath);
		if (!contextPath.matches("(.+)/$")) {
			result.append("/");
		}

		// Adds the current language to route.
		result.append(getCurrentLanguage()).append("/");

		if (route.matches("^/(.*)")) {
			result.append(route.substring(1));
		} else {
			result.append(route);
		}

		return result.toString();
	}

	/**
	 * Returns the current language in terms of client url. (
	 * {@code ekologia.coop/<ctx>/fr} should give {@code fr}).
	 * 
	 * @param request
	 *            The current request
	 * @return The current language
	 */
	protected String getCurrentLanguage() {
		String language = (String) httpServletRequest.getAttribute("language");
		return language;
	}
}
