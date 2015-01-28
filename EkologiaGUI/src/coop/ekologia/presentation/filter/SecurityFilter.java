package coop.ekologia.presentation.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.controller.cms.PageServlet;
import coop.ekologia.presentation.controller.user.LoginServlet;
import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.security.SecurityServiceInterface;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/*" })
public class SecurityFilter implements Filter {
	@EJB
	SecurityServiceInterface securityService;
	
	@Inject LoginSession loginSession;
	
	@Inject
	GlobalRequestScope globalRequestScope;

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		if (globalRequestScope.getLanguage() != null) {
			String requestURI = globalRequestScope.getInternalUrl();
			// Language detected and we already forwarded the request to concrete servlet.
			UserDTO connectedUser = loginSession.getUser();
			Boolean resourceAllow = true;
			if (!requestURI.contains("login") && connectedUser == null) {
	
				Matcher m = Pattern.compile("\\w*\\/(\\S*)").matcher(requestURI);
				if (m.find()) {
					String resource= m.group(1);
					resourceAllow = securityService.isResourceAllow(resource, connectedUser);
				}
			}
	
			if (!resourceAllow) {
				loginSession.setPreviousUrl(httpServletRequest.getRequestURL().toString());
				httpServletResponse.sendRedirect(LoginServlet.routing(httpServletRequest));
			} else {
				// We use forward instead of chain.doFilter because of change of URL.
				//request.setAttribute("language", "fr");
				request.getRequestDispatcher(requestURI).forward(request, response);
			}
		} else {
			// Language not detected - redirection to home FR
			String home1 = httpServletRequest.getContextPath();
			String home2 = home1 + "/";
			String uri = httpServletRequest.getRequestURI();
			if (home1.equalsIgnoreCase(uri) || home2.equalsIgnoreCase(uri)) {
				// Home, but no language => redirect to home FR
				request.setAttribute("language", "fr");
				httpServletResponse.sendRedirect(PageServlet.routingHome(httpServletRequest));
			} else {
				// Resource or 404 - anyway, we continue
				chain.doFilter(httpServletRequest, httpServletResponse);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
