package coop.ekologia.presentation.controller.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.user.UserServiceInterface;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/admin/userForm/create")
public class UserCreateServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserServiceInterface userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCreateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		forwardToJsp("user/userForm.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		String eMail = request.getParameter("email");
		dto.setEmail(eMail);
		String password = request.getParameter("password");
		dto.setPassword(password);
		dto = userService.insertUser(dto);
		request.setAttribute("user", dto);

		response.sendRedirect(String.format("%s/admin/userList",
				request.getContextPath()));
	}

}
