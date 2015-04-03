package coop.ekologia.presentation.controller.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.service.group.GroupServiceInterface;
import coop.ekologia.service.user.UserServiceInterface;
import coop.ekologia.service.utils.CanonicalizerServiceInterface;

/**
 * Servlet implementation class GroupUpdateServlet
 */
@WebServlet("/admin/group/groupForm/update/*")
public class GroupUpdateServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	public static final String routing = "/admin/group/groupForm/update/{id}";

	@EJB
	private GroupServiceInterface groupService;
	
	@EJB
	private UserServiceInterface userService;

	@EJB
	private CanonicalizerServiceInterface canonicalService;

	@Inject
	private RoutingCentral router;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/groupForm\\/update\\/(\\S*)")
				.matcher(request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);

			Integer id = Integer.valueOf(idString);
			GroupDTO groupDTO = new GroupDTO();
			groupDTO.setId(id);
			groupDTO = groupService.getGroupById(groupDTO);
			request.setAttribute("group", groupDTO);
			List<UserDTO> users = userService.getAllUser();
			request.setAttribute("users", users);
			List<UserDTO> usersInGroup = (List<UserDTO>) groupDTO.getUsers();
			request.setAttribute("usersInGroup", usersInGroup);
			
			//TODO #WARNING à utiliser lorsque l'on pourra mettre plusieurs admin dans un groupe
			//List<UserDTO> usersAdminInGroup = (List<UserDTO>) groupDTO.getUsersAdmin();
			//request.setAttribute("usersAdminInGroup", usersAdminInGroup);
			

		}

		forwardToJsp("group/groupForm.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/groupForm\\/update\\/(\\S*)")
				.matcher(request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);
			GroupDTO groupDTO = new GroupDTO();
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String icon = request.getParameter("icon");
			Integer userAdminId = Integer.valueOf(request.getParameter("user"));
			List<Integer> listUserInGroupId = new ArrayList<Integer>();
			String[] listeUserInGroup = request.getParameterValues("userInGroup");
			
			for (String userIdString : listeUserInGroup) {
				listUserInGroupId.add(Integer.valueOf(userIdString));
			}
			
			List<UserDTO> listUserInGroupDTO = new ArrayList<UserDTO>();
			for (Integer userInGroupId : listUserInGroupId) {
				UserDTO dto = new UserDTO();
				dto.setId(userInGroupId);
				listUserInGroupDTO.add(userService.getUserById(dto));
			}
			
			UserDTO userAdminDTO = new UserDTO();
			userAdminDTO.setId(userAdminId);
			userAdminDTO = userService.getUserById(userAdminDTO);
			
			groupDTO.setName(name);
			groupDTO.setCanonical(canonicalService.strToUrl(name));
			groupDTO.setDescription(description);
			groupDTO.setIcon(icon);
			groupDTO.setUsers(listUserInGroupDTO);
			
			/* TODO #warning actuellement on ne peut mettre qu'un seul administrateur dans un groupe */
			//groupDTO.getUsersAdmin().add(userDTO);
			List<UserDTO> newListAdmin = new ArrayList<UserDTO>();
			newListAdmin.add(userAdminDTO);
			
			groupDTO.setUsersAdmin(newListAdmin);
			
			Integer id = Integer.valueOf(idString);
			groupDTO.setId(id);
			groupService.updateUserGroup(groupDTO);
			groupDTO = groupService.updateGroup(groupDTO);
			
		}
		response.sendRedirect(router.getAdminGroupList());
	}

}
