package coop.ekologia.presentation.controller.cms;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.service.cms.PageServiceInterface;

/**
 * Servlet implementation class Pages
 */
@WebServlet("/admin/pageList")
public class PageListServlet extends EkologiaServlet {
	private static final long serialVersionUID = 1L;
	

	@EJB
	private PageServiceInterface pageService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PageDTO> pageDTOs=  pageService.getAllPage();
		
		request.setAttribute("pages", pageDTOs);
		
		forwardToJsp("cms/pageList.jsp", request, response);

		
	}
}