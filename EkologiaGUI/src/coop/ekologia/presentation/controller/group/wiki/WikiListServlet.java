package coop.ekologia.presentation.controller.group.wiki;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.group.wiki.WikiDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.GroupWikiConstants;
import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.group.GroupServiceInterface;
import coop.ekologia.service.group.wiki.WikiServiceInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

@WebServlet("/group/wiki/list/*")
public class WikiListServlet extends EkologiaServlet {
    private static final long serialVersionUID = 5346421818656887507L;

    public static final String routing = "/group/wiki/list/{groupCanonical}";

    @EJB
    private WikiServiceInterface wikiService;

    @EJB
    private GroupServiceInterface groupService;

    @EJB
    private StringUtilitiesInterface stringUtilities;

    @Inject
    private LoginSession loginSession;

    @Inject
    private RoutingCentral router;

    @Inject
    private GlobalRequestScope globalRequestScope;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDTO groupDTO = (GroupDTO)request.getAttribute(GroupWikiConstants.ATTRIBUTE_GROUP_DTO);
        List<WikiDTO> wikiDTOList = wikiService.findRootsByGroup(globalRequestScope.getLanguage(), groupDTO.getId());
        request.setAttribute(GroupWikiConstants.ATTRIBUTE_WIKI_LIST, wikiDTOList);
        forwardToJsp("/group/wiki/list", request, response);
    }
}
