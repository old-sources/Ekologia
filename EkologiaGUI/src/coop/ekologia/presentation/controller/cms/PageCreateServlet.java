package coop.ekologia.presentation.controller.cms;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.cms.PageServiceInterface;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/admin/pageForm/create")
public class PageCreateServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PageServiceInterface pageService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageCreateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		forwardToJsp("cms/pageForm.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PageDTO dto = new PageDTO();
		String url = request.getParameter("url");
		dto.setUrl(url);
		String html = request.getParameter("html");
		dto.setHtml(html);
		dto = pageService.insertPage(dto);
		request.setAttribute("page", dto);

		response.sendRedirect(String.format("%s/admin/pageList",
				request.getContextPath()));
	}

}
