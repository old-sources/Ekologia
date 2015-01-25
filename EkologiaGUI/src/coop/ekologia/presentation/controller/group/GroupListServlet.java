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
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.group.GroupServiceInterface;

/**
 * Servlet implementation class GroupListServlet
 */
@WebServlet("/admin/group/groupList")
public class GroupListServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
    
    public static final String routing(HttpServletRequest request) {
        return getUrl(request, "/admin/group/groupList");
    }
       
	@EJB
	private GroupServiceInterface groupService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GroupDTO> groupDTOs=  groupService.findGroupAll();
		
		request.setAttribute("groups", groupDTOs);
		
		forwardToJsp("group/groupList.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
