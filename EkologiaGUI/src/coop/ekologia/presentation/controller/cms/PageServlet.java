package coop.ekologia.presentation.controller.cms;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class Pages
 */
@WebServlet("/page/*")
public class PageServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String routing(HttpServletRequest request, String canonical) {
		return getUrl(request, "/page/" + canonical);
	}

	@EJB
	private PageServiceInterface pageService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Matcher m = Pattern.compile("\\w*\\/page\\/(\\S*)").matcher(request.getRequestURI());
		if (m.find()) {
			String page = m.group(1);
			PageDTO pageDTO = pageService.getPageFromUrl(page);
			if (pageDTO != null) {
				request.getSession().setAttribute("pageDTO", pageDTO);
				forwardToJsp("/cms/polymorph.jsp", request, response);
			}
		}

	}

}
