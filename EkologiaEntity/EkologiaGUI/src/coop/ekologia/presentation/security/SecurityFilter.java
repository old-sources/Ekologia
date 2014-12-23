package coop.ekologia.presentation.security;

import java.io.IOException;

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

import coop.ekologia.DTO.UserPB;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/*" })
public class SecurityFilter implements Filter {

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
		System.out.println(httpServletRequest.getRequestURI());
		UserPB connectedUser = (UserPB) httpServletRequest.getSession()
				.getAttribute("connectedUser");
		if (!httpServletRequest.getRequestURI().contains("login")
				&& connectedUser == null) {
			httpServletRequest.getSession().setAttribute("urlRequired",
					httpServletRequest.getRequestURL().toString());
			httpServletResponse.sendRedirect("login");
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

		// le chain.doFilter à déclenché le post de login et donc rempli la
		// session
		if (connectedUser == null
				&& (UserPB) httpServletRequest.getSession().getAttribute(
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
