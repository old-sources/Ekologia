package coop.ekologia.presentation.controller.group;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.group.wiki.WikiDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.GroupConstants;
import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.service.group.GroupServiceInterface;
import coop.ekologia.service.group.wiki.WikiServiceInterface;

/**
 * Presentation page of a group.
 */
@WebServlet("/group/presentation/*")
public class GroupPresentationServlet extends EkologiaServlet {
    private static final long serialVersionUID = 1L;

    public static final String routing = "/group/presentation/{groupCanonical}";

    @Inject
    private GlobalRequestScope globalRequestScope;

    @EJB
    private GroupServiceInterface groupService;

    @EJB
    private WikiServiceInterface wikiService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Matcher m = Pattern.compile(String.format("\\w*\\%s\\/group\\/presentation\\/(\\S+)", request.getContextPath())).matcher(request.getRequestURI());
        if (m.find()) {
            String groupCanonical = m.group(1);
            GroupDTO groupDTO = groupService.findGroupByCanonical(groupCanonical);
            if (groupDTO != null) {
                List<WikiDTO> wikiDTOList = wikiService.findRootsByGroup(globalRequestScope.getLanguage(), groupDTO.getId());
                
                request.setAttribute(GroupConstants.ATTRIBUTE_GROUP, groupDTO);
                request.setAttribute(GroupConstants.ATTRIBUTE_WIKILIST, wikiDTOList);
                forwardToJsp("group/groupPresentation.jsp", request, response);
            } else {
                forwardToJsp("group/notFound.jsp", request, response);
            }
        } else {
            forwardToJsp("group/notFound.jsp", request, response);
        }
    }
}
