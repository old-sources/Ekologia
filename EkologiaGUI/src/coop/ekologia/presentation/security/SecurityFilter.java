package coop.ekologia.presentation.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
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

import coop.ekologia.DTO.PageDTO;
import coop.ekologia.DTO.UserDTO;
import coop.ekologia.entity.Page;
import coop.ekologia.service.PageService;
import coop.ekologia.service.PageServiceInterface;
import coop.ekologia.service.SecurityServiceInterface;
import coop.ekologia.service.UserService;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/*" })
public class SecurityFilter implements Filter {

	@EJB
	SecurityServiceInterface securityService;

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// System.out.println(httpServletRequest.getRequestURI());
		UserDTO connectedUser = (UserDTO) httpServletRequest.getSession()
				.getAttribute("connectedUser");
		Boolean resourceAllow = true;
		if (!httpServletRequest.getRequestURI().contains("login")
				&& connectedUser == null) {

			Matcher m = Pattern.compile(String.format("\\w*\\%s\\/(\\S*)", httpServletRequest.getContextPath())).matcher(
					httpServletRequest.getRequestURI());
			if (m.find()) {
				String resource= m.group(1);
				resourceAllow = securityService.isResourceAllow(resource, connectedUser);
			}
		}

		if (!resourceAllow) {
			httpServletRequest.getSession().setAttribute("urlRequired",
					httpServletRequest.getRequestURL().toString());
			httpServletResponse.sendRedirect(httpServletRequest
					.getContextPath().concat("/login"));
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

		// le chain.doFilter à déclenché le post de login et donc rempli la
		// session
		if (connectedUser == null
				&& (UserDTO) httpServletRequest.getSession().getAttribute(
						"connectedUser") != null) {
			String urlRequired = (String) httpServletRequest.getSession()
					.getAttribute("urlRequired");
			httpServletResponse.sendRedirect(urlRequired);
			httpServletRequest.getSession().removeAttribute(urlRequired);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
