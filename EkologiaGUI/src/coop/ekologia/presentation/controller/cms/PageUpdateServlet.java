package coop.ekologia.presentation.controller.cms;

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

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.service.cms.PageServiceInterface;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/admin/pageForm/update/*")
public class PageUpdateServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PageServiceInterface pageService;
	@Inject
	RoutingCentral routingCentral;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/pageForm\\/update\\/(\\S*)")
				.matcher(request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);

			Integer id = Integer.valueOf(idString);
			PageDTO dto = new PageDTO();
			dto.setId(id);
			dto = pageService.getPageById(dto);
			request.setAttribute("page", dto);

		}

		forwardToJsp("cms/pageForm.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/pageForm\\/update\\/(\\S*)")
				.matcher(request.getRequestURI());
		if (m.find()) {
			String idString = m.group(1);
			PageDTO dto = new PageDTO();
			String url = request.getParameter("url");
			dto.setUrl(url);
			;
			String html = request.getParameter("html");
			dto.setHtml(html);
			Integer id = Integer.valueOf(idString);
			dto.setId(id);
			dto = pageService.updatePage(dto);
		}
		response.sendRedirect(routingCentral.getPageList());
	}

}
