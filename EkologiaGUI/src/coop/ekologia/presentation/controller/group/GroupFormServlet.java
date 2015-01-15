package coop.ekologia.presentation.controller.group;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class GroupFormServlet
 */
@WebServlet("/group/groupForm/*")
public class GroupFormServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private GroupServiceInterface groupService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/groupForm\\/(\\S*)").matcher(
				request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);

			Integer id = Integer.valueOf(idString);
			GroupDTO dto = new GroupDTO();
			dto.setId(id);
			dto = groupService.getGroupById(dto);
			request.setAttribute("group", dto);

			forwardToJsp("group/groupForm.jsp", request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
