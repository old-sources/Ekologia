package coop.ekologia.presentation.controller.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.controller.cms.HomeServlet;
import coop.ekologia.presentation.controller.cms.PageServlet;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.user.UserServiceInterface;

/**
 * Login page used to log in the user.
 */
@WebServlet("/login")
public class LoginServlet extends EkologiaServlet {
	public static final String WEB_INF_LOGIN_JSP = "/user/login.jsp";

	private static final long serialVersionUID = 1L;

	public static final String routing(HttpServletRequest request) {
		return getUrl(request, "/login");
	}

	@EJB
	private UserServiceInterface userService;

	@Inject
	private LoginSession loginSession;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		forwardToJsp(WEB_INF_LOGIN_JSP, request, response);
	}

	/**
	 * Ce post traite le formulaire de login, vérifie le passw par rapport aux
	 * users en session et stocke ce user en session si passw ok
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String loginParameter = request.getParameter("login");
		String passwParameter = request.getParameter("passw");
		UserDTO loginUser = new UserDTO();
		loginUser.setEmail(loginParameter);
		loginUser.setPassword(passwParameter);
		loginUser = userService.getSecuredUser(loginUser);
		if (loginUser != null) {
			// Problem when we are in filter with the addition of language management.
			// So we moved redirection here.
			loginSession.setUser(loginUser);
			if (loginSession.getPreviousUrl() != null) {
                response.sendRedirect(loginSession.getPreviousUrl());
                loginSession.setPreviousUrl(null);
            } else {
                response.sendRedirect(HomeServlet.routing(request));
            }
		} else {
			forwardToJsp(WEB_INF_LOGIN_JSP, request, response);
		}

	}

}
