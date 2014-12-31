package coop.ekologia.presentation.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.PageDTO;
import coop.ekologia.service.PageServiceInterface;

/**
 * Servlet implementation class Pages
 */
@WebServlet("/page/*")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private PageServiceInterface pageService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/page\\/(\\S*)").matcher(
				request.getRequestURI());
		if (m.find()) {
			String page = m.group(1);
			PageDTO pageDTO = pageService.getPageFromUrl(page);
			if (pageDTO != null) {
				request.getSession().setAttribute("pageDTO", pageDTO);
				request.getRequestDispatcher(String.format("/WEB-INF/polymorph.jsp"))
						.forward(request, response);
			}
		}

	}

}
