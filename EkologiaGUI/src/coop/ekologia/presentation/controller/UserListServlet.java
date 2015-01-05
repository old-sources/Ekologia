package coop.ekologia.presentation.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.UserDTO;
import coop.ekologia.service.UserServiceInterface;

/**
 * Servlet implementation class User
 */
@WebServlet("/admin/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// @Inject @Named("session")
	@EJB
	UserServiceInterface userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// List<UserData> users = (List<UserData>) request.getSession()
		// .getAttribute("users");
		List<UserDTO> users = userService.getAllUser();

		request.setAttribute("filteredUsers", users);
//		request.getRequestDispatcher(String.format("/WEB-INF/userList.jsp",request.getContextPath())).forward(request,
//				response);
		request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<UserDTO> users = userService.getAllUser();

		String factionParameter = request.getParameter("faction");

		request.setAttribute("filteredUsers", users);

		request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request,
				response);
	}

}
