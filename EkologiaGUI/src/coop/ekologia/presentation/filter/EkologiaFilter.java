package coop.ekologia.presentation.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.presentation.controller.user.LoginConnectionServlet;
import coop.ekologia.presentation.controller.user.LoginServlet;
import coop.ekologia.presentation.request.ServletUtil;
import coop.ekologia.presentation.session.LoginSession;

public abstract class EkologiaFilter implements Filter {
    @Inject
    protected LoginSession loginSession;
    
    @Inject
    protected ServletUtil servletUtil;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doHttpFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    /**
     * Does the filter (replaces {@link #doFilter(ServletRequest, ServletResponse, FilterChain)} of {@link Filter}).
     * 
     * @param request  The request as {@link HttpServletRequest}
     * @param response The response as {@link HttpServletResponse}
     * @param chain    The filter chain
     */
    protected abstract void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException;

    /**
     * Sends a forbidden to the client.
     * 
     * @param request  The current request
     * @param response The current response
     * @param chain    The chain filter
     */
    protected void forbidden(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (loginSession.getUser() == null) {
            loginSession.setPreviousUrl(request.getRequestURI());
            response.sendRedirect(servletUtil.getUrl(LoginConnectionServlet.routing));
        } else {
            // TODO: pretty print
            response.sendError(403);
        }
    }

    /**
     * Sends a not found to the client.
     * 
     * @param request  The current request
     * @param response The current response
     * @param chain    The chain filter
     */
    protected void notFound(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO: pretty print
        response.sendError(404);
    }

    /**
     * Sends a bad request to the client.
     * 
     * @param request  The current request
     * @param response The current response
     * @param chain    The chain filter
     */
    protected void badRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO: pretty print
        response.sendError(400);
    }
    
    /**
     * Sends a success to the client.
     * It means to continue the request to right servlet.
     * 
     * @param request  The current request
     * @param response The current response
     * @param chain    The chain filter
     */
    protected void success(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
