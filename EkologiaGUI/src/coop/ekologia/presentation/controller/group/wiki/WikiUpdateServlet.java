package coop.ekologia.presentation.controller.group.wiki;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.wiki.WikiDTO;
import coop.ekologia.DTO.group.wiki.WikiversionDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.GroupWikiConstants;
import coop.ekologia.presentation.controller.FormErrors;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.group.wiki.WikiServiceInterface;
import coop.ekologia.service.utils.ConstraintsServiceInterface;

@WebServlet("/group/wiki/update/*")
public class WikiUpdateServlet extends EkologiaServlet {
    private static final long serialVersionUID = 6023406354033374292L;

    public static final String routing(HttpServletRequest request, String groupCanonical, String wikiCanonical) {
        return getUrl(request, "/group/wiki/update/" + groupCanonical + "/" + wikiCanonical);
    }

    @EJB
    private WikiServiceInterface wikiService;

    @EJB
    private ConstraintsServiceInterface constraintsService;

    @Inject
    private LoginSession loginSession;

    @Inject
    private FormErrors formErrors;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        formErrors.setLanguage(getCurrentLanguage(request));

        String content = request.getParameter(GroupWikiConstants.PARAMETER_CONTENT);
        String image = request.getParameter(GroupWikiConstants.PARAMETER_IMAGE);

        if (constraintsService.isEmpty(content)) {
            formErrors.addError(GroupWikiConstants.PARAMETER_CONTENT, "group_wiki.content.empty");
        }
        if (constraintsService.isNotEmpty(image) && constraintsService.isNotUrl(image)) {
            formErrors.addError(GroupWikiConstants.PARAMETER_IMAGE, "group_wiki.image.noturl");
        }

        if (formErrors.isEmpty()) {
            WikiDTO wikiDTO = (WikiDTO)request.getAttribute(GroupWikiConstants.ATTRIBUTE_WIKI);
            
            WikiversionDTO version = new WikiversionDTO();
            version.setContent(content);
            version.setImage(image);
            version.setUser(loginSession.getUser());
            // TODO: For the moment, all versions are always active, parameterize it in the future
            version.setActive(true);

            wikiDTO.setCurrentVersion(version);
            version.setWiki(wikiDTO);

            wikiDTO = wikiService.update(wikiDTO);

            response.sendRedirect(WikiReadServlet.routing(request, wikiDTO.getGroup().getCanonical(),
                                                          wikiDTO.getCanonical()));
        } else {
            request.setAttribute(GroupWikiConstants.ATTRIBUTE_ERRORS, formErrors.getErrors());
            forwardToJsp(request, response);
        }
    }

    private void forwardToJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String groupCanonical = (String) request.getAttribute(GroupWikiConstants.ATTRIBUTE_GROUP_CANONICAL);
        String wikiCanonical = (String) request.getAttribute(GroupWikiConstants.ATTRIBUTE_WIKI_CANONICAL);
        request.setAttribute(GroupWikiConstants.ATTRIBUTE_FORM_ROUTE, routing(request, groupCanonical, wikiCanonical));
        forwardToJsp("/group/wiki/update.jsp", request, response);
    }
}
