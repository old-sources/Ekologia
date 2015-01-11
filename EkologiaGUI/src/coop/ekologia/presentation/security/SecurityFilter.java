package coop.ekologia.presentation.security;

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
import coop.ekologia.presentation.controller.user.LoginServlet;
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

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		UserDTO connectedUser = loginSession.getUser();
		Boolean resourceAllow = true;
		if (!httpServletRequest.getRequestURI().contains("login") && connectedUser == null) {

			Matcher m = Pattern.compile(String.format("\\w*\\%s\\/(\\S*)", httpServletRequest.getContextPath())).matcher(
					httpServletRequest.getRequestURI());
			if (m.find()) {
				String resource= m.group(1);
				resourceAllow = securityService.isResourceAllow(resource, connectedUser);
			}
		}

		if (!resourceAllow) {
			loginSession.setPreviousUrl(httpServletRequest.getRequestURL().toString());
			httpServletResponse.sendRedirect(LoginServlet.routing(httpServletRequest));
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

		// le chain.doFilter à déclenché le post de login et donc rempli la
		// session
		if (connectedUser == null
				&& loginSession.getUser()!=null) {
			String urlRequired = loginSession.getPreviousUrl();
			httpServletResponse.sendRedirect(urlRequired);
			loginSession.setPreviousUrl(null);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
