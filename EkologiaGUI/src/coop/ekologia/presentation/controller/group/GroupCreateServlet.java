package coop.ekologia.presentation.controller.group;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.group.GroupServiceInterface;
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
		forwardToJsp("group/groupForm.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDTO dto = new GroupDTO();
		String name = request.getParameter("name");
		dto.setName(name);
		String html = request.getParameter("html");
		dto.setCanonical(canonicalService.strToUrl(name));
		dto = groupService.insertGroup(dto);
		request.setAttribute("group", dto);

		response.sendRedirect(String.format("%s/group/groupList",
				request.getContextPath()));
	}

}
