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
	
	protected void forwardToJsp(String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("connectedUser", loginSession.getUser());
		RequestDispatcher result = request.getRequestDispatcher(getJsp(name));
		result.forward(request, response);
	}
    
    protected static void render(String jspName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher(getJsp(jspName)).forward(request, response);
    }
    
    protected static String getUrl(HttpServletRequest request, String route) {
    	StringBuilder result = new StringBuilder();
    	String contextPath = request.getContextPath();
    	
    	result.append(contextPath);
    	if (!contextPath.matches("(.+)/$")) {
    		result.append("/");
    	}
    	
    	if (route.matches("^/(.+)")) {
    		result.append(route.substring(1));
    	} else {
    		result.append(route);
    	}
    	
    	return result.toString();
    }
}
