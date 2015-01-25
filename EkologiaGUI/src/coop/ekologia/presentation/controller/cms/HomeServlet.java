package coop.ekologia.presentation.controller.cms;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.cms.PageServiceInterface;

@WebServlet("/home")
public class HomeServlet extends EkologiaServlet {
	private static final long serialVersionUID = -4096633312380583816L;
	
	
	public static final String routing(HttpServletRequest request) {
		return getUrl(request, "/home");
	}
	
	@EJB
	private PageServiceInterface pageService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PageDTO page = pageService.getPageFromUrl("home");
		if (page != null) {
			req.setAttribute("page", page);
			forwardToJsp("cms/home", req, resp);
		}
	}
}
