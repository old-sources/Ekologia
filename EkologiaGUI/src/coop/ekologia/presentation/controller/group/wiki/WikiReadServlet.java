package coop.ekologia.presentation.controller.group.wiki;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.wiki.WikiDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.GroupWikiConstants;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.group.GroupServiceInterface;
import coop.ekologia.service.group.wiki.WikiServiceInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

@WebServlet("/group/wiki/read/*")
public class WikiReadServlet extends EkologiaServlet {
	private static final long serialVersionUID = 5346421818656887507L;
	
	public static final String routing = "/group/wiki/read/{groupCanonical}/{wikiCanonical}";

	@Deprecated
	public static final String routing(HttpServletRequest request, String groupCanonical, String wikiCanonical) {
		return getUrl(request, "/group/wiki/read/" + groupCanonical + "/" + wikiCanonical);
	}
	
	@EJB
	private WikiServiceInterface wikiService;
	
	@EJB
	private GroupServiceInterface groupService;
	
	@EJB
	private StringUtilitiesInterface stringUtilities;
	
	@Inject
	private LoginSession loginSession;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    WikiDTO wikiDTO = (WikiDTO)request.getAttribute(GroupWikiConstants.ATTRIBUTE_WIKI);
	    String groupCanonical = (String)request.getAttribute(GroupWikiConstants.ATTRIBUTE_GROUP_CANONICAL);
	    String realGroupCanonical = wikiDTO.getGroup().getCanonical();
		if (stringUtilities.notEquals(groupCanonical, realGroupCanonical)) {
			// The group is not the valid one, so we redirect the user to the valid group.
			// It avoids 404 because of the canonical is unique.
			response.sendRedirect(routing(request, realGroupCanonical, wikiDTO.getCanonical()));
		} else {
			forwardToJsp("/group/wiki/read", request, response);
		}
	}
}
