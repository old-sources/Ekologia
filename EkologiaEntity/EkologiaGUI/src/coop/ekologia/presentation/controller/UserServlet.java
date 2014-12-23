package coop.ekologia.presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.FactionPB;
import coop.ekologia.DTO.UserPB;
import coop.ekologia.service.UserServiceInterface;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//@Inject @Named("session")
	@EJB(beanName="instance")
	UserServiceInterface userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		List<UserData> users = (List<UserData>) request.getSession()
//				.getAttribute("users");
		List<UserPB> users = userService.getAllUser();
		
		request.setAttribute("filteredUsers", users);
		request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		List<UserData> users = (List<UserData>) request.getSession()
//				.getAttribute("users");
		List<UserPB> users=userService.getAllUser();
		List<UserPB> filteredUsers = new ArrayList<UserPB>();
		
		String factionParameter = request.getParameter("faction");
		
		FactionPB faction =null;
		
		if(factionParameter!=null){
			faction = FactionPB.valueOf(factionParameter);
			//request.setAttribute("factionParameter", factionParameter);
		}
		
		for (UserPB userData : users) {
			if(faction==null || userData.getFaction() != null && userData.getFaction().equals(faction)){
				filteredUsers.add(userData);
			}
		}
		
		request.setAttribute("filteredUsers", filteredUsers);
		
		request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
	}

}
