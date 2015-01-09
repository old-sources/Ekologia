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
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.user.UserServiceInterface;

/**
 * Servlet implementation class User
 */
@WebServlet("/admin/userForm/*")
public class UserFormServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	// @Inject @Named("session")
	@EJB
	UserServiceInterface userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/userForm\\/(\\S*)").matcher(
				request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);
			if ("create".compareTo(idString) != 0) {

				Integer id = Integer.valueOf(idString);
				UserDTO dto = new UserDTO();
				dto.setId(id);
				dto = userService.getUserById(dto);
				request.setAttribute("user", dto);
			}
			forwardToJsp("user/userForm.jsp", request, response);
//			request.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(
//					request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/userForm\\/(\\S*)").matcher(
				request.getRequestURI());
		if (m.find()) {
			UserDTO dto = new UserDTO();
			String eMail = request.getParameter("email");
			dto.setEmail(eMail);
			String password = request.getParameter("password");
			dto.setPassword(password);
			String idString = m.group(1);
			if ("create".compareTo(idString) != 0) {
				Integer id = Integer.valueOf(idString);
				dto.setId(id);
				dto = userService.updateUser(dto);
				request.setAttribute("user", dto);
			}else{
				dto = userService.insertUser(dto);
				request.setAttribute("user", dto);				
			}
			response.sendRedirect(String.format("%s/admin/userList",request.getContextPath()));
		//	forwardToJsp("user/userList.jsp", request, response);
			
//			request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(
//					request, response);

		}

	}

}
