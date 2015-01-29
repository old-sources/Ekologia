package coop.ekologia.presentation.request;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import coop.ekologia.presentation.controller.cms.PageFormServlet;
import coop.ekologia.presentation.controller.cms.PageListServlet;
import coop.ekologia.presentation.controller.user.LoginConnectionServlet;
import coop.ekologia.presentation.controller.user.LoginDeconnectionServlet;
import coop.ekologia.presentation.controller.user.UserFormServlet;
import coop.ekologia.presentation.controller.user.UserListServlet;

@Named("routing")
@RequestScoped
public class RoutingCentral {

	@Inject
	private HttpServletRequest httpServletRequest;

	@Inject
	private ServletUtil servletUtil;

	public String getUserForm() {
		return servletUtil.getUrl(UserFormServlet.routing);
	}

	public String getPageForm() {
		return servletUtil.getUrl(PageFormServlet.routing);
	}

	public String getUserList() {
		return servletUtil.getUrl(UserListServlet.routing);
	}

	public String getPageList() {
		return servletUtil.getUrl(PageListServlet.routing);
	}

	public String getDeconnection() {
		return servletUtil.getUrl(LoginDeconnectionServlet.routing);
	}
	
	public String getConnection() {
		return servletUtil.getUrl(LoginConnectionServlet.routing);
	}

}
