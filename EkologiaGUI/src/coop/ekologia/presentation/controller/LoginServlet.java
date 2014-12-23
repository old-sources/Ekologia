package coop.ekologia.presentation.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.UserPB;
import coop.ekologia.service.UserServiceInterface;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final String WEB_INF_LOGIN_JSP = "WEB-INF/login.jsp";

	private static final long serialVersionUID = 1L;

	// @Inject @Named("session")
	@EJB(beanName = "instance")
	private UserServiceInterface userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(WEB_INF_LOGIN_JSP).forward(request, response);
	}

	/**
	 * Ce post traite le formulaire de login, vérifie le passw par rapport aux
	 * users en session et stocke ce user en session si passw ok
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("deconnection") != null) {
			request.getSession().removeAttribute("connectedUser");
			response.sendRedirect("login.html");
		} else {

			String nomParameter = request.getParameter("login");
			String passwParameter = request.getParameter("passw");
			UserPB loginUser = new UserPB();
			loginUser.setNom(nomParameter);
			loginUser.setPassw(passwParameter);
			loginUser = userService.getSecuredUser(loginUser);
			if (loginUser != null) {
				request.getSession().setAttribute("connectedUser", loginUser);
			} else {
				response.sendRedirect(WEB_INF_LOGIN_JSP);
			}
		}

	}

}
