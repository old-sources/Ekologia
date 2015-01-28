package coop.ekologia.presentation.controller.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.presentation.request.ServletUtil;
import coop.ekologia.service.user.UserServiceInterface;

/**
 * Servlet implementation class User
 */
@WebServlet(UserFormServlet.routing)
public class UserFormServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
	public static final String routing= "/admin/userForm/*";
	
	// @Inject @Named("session")
	@EJB
	UserServiceInterface userService;
	
//	@Inject
//	GlobalRequestScope globalRequestScope;
//	
//	@Inject
//	ServletUtil servletUtil;
//	
//	
//	public static final String getRouting() {
//		return getUrl(globalRequestScope.getHttpRequest(), "/admin/userForm/*");
//	}

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

			Integer id = Integer.valueOf(idString);
			UserDTO dto = new UserDTO();
			dto.setId(id);
			dto = userService.getUserById(dto);
			request.setAttribute("user", dto);

			forwardToJsp("user/userForm.jsp", request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
