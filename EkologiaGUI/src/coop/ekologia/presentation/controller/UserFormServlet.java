package coop.ekologia.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.PageDTO;
import coop.ekologia.DTO.UserDTO;
import coop.ekologia.service.UserServiceInterface;

/**
 * Servlet implementation class User
 */
@WebServlet("/admin/userForm/*")
public class UserFormServlet extends HttpServlet {
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
			request.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(
					request, response);

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
			String nom = request.getParameter("nom");
			dto.setNom(nom);
			String prenom = request.getParameter("prenom");
			dto.setPrenom(prenom);
			String eMail = request.getParameter("email");
			dto.seteMail(eMail);
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
			request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(
					request, response);

		}

	}

}
