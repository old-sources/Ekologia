package coop.ekologia.presentation.controller.group;

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

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.service.group.GroupServiceInterface;

/**
 * Servlet implementation class GroupDeleteServlet
 */
@WebServlet("/admin/group/groupForm/delete/*")
public class GroupDeleteServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	public static final String routing = "/admin/group/groupForm/delete/{id}";
    
	@EJB
	private GroupServiceInterface groupService;

	@Inject
	private RoutingCentral router;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/groupForm\\/delete\\/(\\S*)").matcher(
				request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);
			GroupDTO dto = new GroupDTO();
			Integer id = Integer.valueOf(idString);
			dto.setId(id);
			groupService.deleteGroup(dto);
			request.setAttribute("group", dto);
		}


		response.sendRedirect(router.getAdminGroupList());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
