package coop.ekologia.presentation.controller.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.DTO.user.UserIndividualDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.user.UserServiceInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/admin/userForm/create")
public class UserCreateServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserServiceInterface userService;

    @EJB
    private StringUtilitiesInterface stringUtilities;

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
	    // For now, we just insert UserIndividualDTO, but to improve in the future.
		UserDTO dto = new UserIndividualDTO();
		String eMail = request.getParameter("email");
		dto.setEmail(eMail);
		
		String password = request.getParameter("password");
        //moved to Service Layer
		//String salt = stringUtilities.generateCryptSalt();
        //dto.setPassword(stringUtilities.crypt(password, salt));
        //dto.setSalt(salt);
		dto.setPassword(password);
		
		String role = request.getParameter("role");
		dto.addRole(role);	
		
		dto = userService.insertUser(dto);

		response.sendRedirect(String.format("%s/admin/userList",
				request.getContextPath()));
	}

}
