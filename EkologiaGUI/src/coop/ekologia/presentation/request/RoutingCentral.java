package coop.ekologia.presentation.request;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import coop.ekologia.presentation.controller.user.UserFormServlet;

@Named("routing")
@RequestScoped
public class RoutingCentral {

	@Inject
	private HttpServletRequest httpServletRequest;
	
	@Inject
	private ServletUtil servletUtil;
	
	public String getRouteUserForm(){
		return servletUtil.getUrl(UserFormServlet.routing);
	}
}
