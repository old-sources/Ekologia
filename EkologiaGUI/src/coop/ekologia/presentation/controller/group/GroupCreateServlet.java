package coop.ekologia.presentation.controller.group;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.group.GroupServiceInterface;
import coop.ekologia.service.user.UserServiceInterface;
import coop.ekologia.service.utils.CanonicalizerServiceInterface;

/**
 * Servlet implementation class GroupCreateServlet
 */
@WebServlet("/group/groupForm/create")
public class GroupCreateServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private GroupServiceInterface groupService;
	
	@EJB
	private UserServiceInterface userService;
	
	@EJB
	private CanonicalizerServiceInterface canonicalService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDTO> users = userService.getAllUser();
		request.setAttribute("users", users);
		forwardToJsp("group/groupForm.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDTO groupDTO = new GroupDTO();
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String icon = request.getParameter("icon");
		Integer userId = Integer.valueOf(request.getParameter("user"));
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userId);
		userDTO = userService.getUserById(userDTO);
		
		groupDTO.setName(name);
		groupDTO.setCanonical(canonicalService.strToUrl(name));
		groupDTO.setDescription(description);
		groupDTO.setIcon(icon);
		groupDTO.getUsers().add(userDTO);
		groupDTO.getUsersAdmin().add(userDTO);
		
		groupDTO = groupService.insertGroup(groupDTO);
		
		groupDTO.getUsers().add(userDTO);
		groupDTO.getUsersAdmin().add(userDTO);
		
		groupService.insertUserGroup(groupDTO);
		
		request.setAttribute("group", groupDTO);

		response.sendRedirect(String.format("%s/group/groupList",
				request.getContextPath()));
	}

}
