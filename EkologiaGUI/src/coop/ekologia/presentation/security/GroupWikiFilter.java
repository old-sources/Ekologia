package coop.ekologia.presentation.security;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.group.wiki.WikiDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.presentation.constants.GroupWikiConstants;
import coop.ekologia.service.group.GroupServiceInterface;
import coop.ekologia.service.group.wiki.WikiServiceInterface;
import coop.ekologia.service.utils.ConstraintsServiceInterface;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/group/wiki/*" })
public class GroupWikiFilter extends EkologiaFilter {
    private static final Logger logger = Logger.getLogger(GroupWikiFilter.class.getName());

    @EJB
    private WikiServiceInterface wikiService;

    @EJB
    private GroupServiceInterface groupService;

    @EJB
    private ConstraintsServiceInterface constraintsService;

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Matcher m = Pattern.compile(String.format("\\w*\\%s\\/group\\/wiki\\/(read|create|update|delete)\\/([^\\/]*)(\\/(\\S*))?",
                                                  request.getContextPath())).matcher(request.getRequestURI());
        if (m.find()) {
            String action = m.group(1);
            String groupCanonical = m.group(2);
            String wikiCanonical = m.group(4);
            request.setAttribute(GroupWikiConstants.ATTRIBUTE_GROUP_CANONICAL, groupCanonical);
            request.setAttribute(GroupWikiConstants.ATTRIBUTE_WIKI_CANONICAL, wikiCanonical);

            UserDTO userDTO = loginSession.getUser();

            GroupDTO groupDTO = groupService.findByCanonical(groupCanonical);
            request.setAttribute(GroupWikiConstants.ATTRIBUTE_GROUPDTO, groupDTO);
            if (groupDTO == null) {
                notFound(request, response, chain);
                return;
            }

            WikiDTO wikiDTO = null;
            if (wikiCanonical != null) {
                wikiDTO = wikiService.findByCanonical(getCurrentLanguage(request), wikiCanonical);
                request.setAttribute(GroupWikiConstants.ATTRIBUTE_WIKI, wikiDTO);
            }

            if ("read".equals(action)) {
                if (wikiDTO == null) {
                    notFound(request, response, chain);
                    return;
                }
            } else if ("create".equals(action)) {
                if (userDTO == null || !wikiService.canWrite(userDTO, groupDTO)) {
                    forbidden(request, response, chain);
                    return;
                }

                if (groupDTO == null) {
                    notFound(request, response, chain);
                    return;
                }

                String parentWikiCanonical = request.getParameter(GroupWikiConstants.PARAMETER_PARENT);
                request.setAttribute(GroupWikiConstants.ATTRIBUTE_PARENT, parentWikiCanonical);
                if (!constraintsService.isEmpty(parentWikiCanonical)) {
                    WikiDTO parentWikiDTO = wikiService.findByCanonical(getCurrentLanguage(request),
                                                                        parentWikiCanonical);
                    if (parentWikiDTO == null) {
                        notFound(request, response, chain);
                        return;
                    }

                    if (!wikiService.canWrite(userDTO, parentWikiDTO)) {
                        forbidden(request, response, chain);
                        return;
                    }
                }
            } else if ("update".equals(action)) {
                if (userDTO == null) {
                    forbidden(request, response, chain);
                    return;
                }
                if (wikiDTO == null) {
                    notFound(request, response, chain);
                    return;
                }
            } else if ("delete".equals(action)) {
                if (userDTO == null) {
                    forbidden(request, response, chain);
                    return;
                }
                if (wikiDTO == null) {
                    notFound(request, response, chain);
                    return;
                }
            } else {
                logger.warning("Unexpected action find when filter wiki group urls.");
                notFound(request, response, chain);
            }

            success(request, response, chain);
        }
    }
}
