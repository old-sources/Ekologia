package coop.ekologia.presentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.presentation.session.MenuApplication;

public abstract class EkologiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LoginSession loginSession;
	
	@Inject
	protected GlobalRequestScope globalRequestScope;

	@Inject
	protected MenuApplication menuApplication;

	/**
	 * Returns the absolute path to the jsp.
	 * 
	 * @param name The jsp path (beginning from {@code /WebContent/WEB-INF/jsp}, ex: {@code /group/wiki/read.jsp})
	 * @return     The absolute path of the jsp
	 */
	protected static String getJsp(String name) {
        StringBuilder result = new StringBuilder();
        result.append("/WEB-INF/jsp");
        if (name.charAt(0) != '/') {
            result.append("/");
        }
        result.append(name);
        if (!".jsp".equals(result.substring(result.length() - 4))) {
            result.append(".jsp");
        }
        return result.toString();
    }
	
	/**
	 * Forwards the current request to jsp.
	 * This methods is used to add global parameters before sending to jsp.
	 * 
	 * @param name     The jsp name
	 * @param request  The current request
	 * @param response The current response
	 * @see #getJsp(String)
	 */
	protected void forwardToJsp(String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("connectedUser", loginSession.getUser());
		request.setAttribute("currentLanguage", globalRequestScope.getLanguage());
		request.setAttribute("globalMenus", menuApplication.findByLanguage(globalRequestScope.getLanguage()));
		request.setAttribute("applicationRoles", getRoles());
		
		RequestDispatcher result = request.getRequestDispatcher(getJsp(name));
		result.forward(request, response);
	}

	/**
	 * This method returns the whole list of roles usable in current application.
	 */
	private Map<String, String> getRoles() {
		// TODO: take roles from database as soon as the development is done.
		Map<String, String> result = new HashMap<String, String>();
		result.put("all", "Tous");
		result.put("admin", "Admin");
		return result;
	}
    
	@Deprecated
	/**
	 * @deprecated since 13/01/2015 - use forwardToJsp instead.
	 */
    protected static void render(String jspName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher(getJsp(jspName)).forward(request, response);
    }
    
	/**
	 * Returns an absolute url (depends on all context variables).
	 * 
	 * @param request The current request
	 * @param route   The route to send the user (ex: {@code /group/wiki/group-1/wiki-1})
	 * @return        The absolute url to given route
	 */
    protected static String getUrl(HttpServletRequest request, String route) {
    	StringBuilder result = new StringBuilder();
    	String contextPath = request.getContextPath();
    	
    	result.append(contextPath);
    	if (!contextPath.matches("(.+)/$")) {
    		result.append("/");
    	}
    	
    	// Adds the current language to route.
    	result.append(getCurrentLanguage(request)).append("/");
    	
    	if (route.matches("^/(.*)")) {
    		result.append(route.substring(1));
    	} else {
    		result.append(route);
    	}
    	
    	return result.toString();
    }
    
    /**
     * Returns the current language in terms of client url. ({@code ekologia.coop/<ctx>/fr} should give {@code fr}).
     * 
     * @param request The current request
     * @return        The current language
     */
    protected static String getCurrentLanguage(HttpServletRequest request) {
    	String language = (String)request.getAttribute("language");
		return language;
    }
}
