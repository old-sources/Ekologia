package coop.ekologia.presentation;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.presentation.session.LoginSession;

public abstract class EkologiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LoginSession loginSession;

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
		request.setAttribute("currentLanguage", getCurrentLanguage(request));
		
		RequestDispatcher result = request.getRequestDispatcher(getJsp(name));
		result.forward(request, response);
	}
    
	@Deprecated
	/**
	 * @deprecated since 13/01/2015 - use forwardToJsp instead.
	 */
    protected static void render(String jspName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher(getJsp(jspName)).forward(request, response);
    }
    
	/**
	 * Return an absolute url (depends on all context variables).
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
    	
    	if (route.matches("^/(.*)")) {
    		result.append(route.substring(1));
    	} else {
    		result.append(route);
    	}
    	
    	return result.toString();
    }
    
    /**
     * Returns the current language in terms of client url. ({@code fr.ekologia.coop} should give {@code fr}).
     * 
     * @param request The current request
     * @return        The current language
     */
    protected String getCurrentLanguage(HttpServletRequest request) {
    	String serverName = request.getServerName();
    	
    	String language = "fr";
    	//TODO code commented cause by bug at URL http://localhost:8080/EkologiaGUI/
    	//TODO this code is for url as http://fr.ekologia.coop/xxx
    	//String language = serverName.substring(0, serverName.indexOf('.'));
    	return language;
    }
}
