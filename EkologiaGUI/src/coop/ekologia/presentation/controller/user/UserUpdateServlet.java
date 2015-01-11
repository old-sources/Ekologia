package coop.ekologia.presentation.controller.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.service.user.UserServiceInterface;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/admin/userForm/update/*")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserServiceInterface userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/userForm\\/update\\/(\\S*)").matcher(
				request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);
			UserDTO dto = new UserDTO();
			String eMail = request.getParameter("email");
			dto.setEmail(eMail);
			String password = request.getParameter("password");
			dto.setPassword(password);
			Integer id = Integer.valueOf(idString);
			dto.setId(id);
			dto = userService.updateUser(dto);
			request.setAttribute("user", dto);
		}


		response.sendRedirect(String.format("%s/admin/userList",
				request.getContextPath()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
