package coop.ekologia.presentation.controller.user;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.session.LoginSession;

/**
 * Servlet implementation class LoginConnectionServlet
 */
@WebServlet(LoginDeconnectionServlet.routing)
public class LoginDeconnectionServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
	public static final String routing = "/login/deconnection";

	
	public static final String routing(HttpServletRequest request) {
		return getUrl(request, routing);
	}
       
	@Inject
	LoginSession loginSession;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDeconnectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		loginSession.setUser(null);
		response.sendRedirect(request.getHeader("referer"));
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
