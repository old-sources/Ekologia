package coop.ekologia.presentation.controller.group.wiki;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.wiki.WikiDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.GroupWikiConstants;
import coop.ekologia.presentation.controller.FormErrors;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.group.wiki.WikiServiceInterface;

@WebServlet("/group/wiki/create/*")
public class WikiCreateServlet extends EkologiaServlet {
    private static final long serialVersionUID = 6023406354033374292L;

    public static final String routing(HttpServletRequest request, String groupCanonical) {
        return routing(request, groupCanonical, null);
    }

    public static final String routing(HttpServletRequest request, String groupCanonical, String parentCanonical) {
        if (parentCanonical == null) {
            return getUrl(request, "/group/wiki/create/" + groupCanonical);
        } else {
            return getUrl(request, String.format("/group/wiki/create/%s?%s=%s", groupCanonical,
                                                 GroupWikiConstants.PARAMETER_PARENT, parentCanonical));
        }
    }
//
//    @EJB
//    private WikiServiceInterface wikiService;
//
//    @EJB
//    private ConstraintsServiceInterface constraintsService;

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

        String parentWikiCanonical = request.getParameter(GroupWikiConstants.PARAMETER_PARENT);
        String title = request.getParameter(GroupWikiConstants.PARAMETER_TITLE);
        String content = request.getParameter(GroupWikiConstants.PARAMETER_CONTENT);
        String image = request.getParameter(GroupWikiConstants.PARAMETER_IMAGE);
//
//        if (constraintsService.isEmpty(title)) {
//            formErrors.addError(GroupWikiConstants.PARAMETER_TITLE, "group_wiki.title.empty");
//        }
//        if (constraintsService.isEmpty(content)) {
//            formErrors.addError(GroupWikiConstants.PARAMETER_CONTENT, "group_wiki.content.empty");
//        }
//        if (constraintsService.isNotEmpty(image) && constraintsService.isNotUrl(image)) {
//            formErrors.addError(GroupWikiConstants.PARAMETER_IMAGE, "group_wiki.image.noturl");
//        }
//
//        if (formErrors.isEmpty()) {
//            WikiDTO wikiDTO = new WikiDTO();
//            wikiDTO.setTitle(title);
//            wikiDTO.setLanguage(getCurrentLanguage(request));
//            // TODO: For the moment, all wiki are always editable, parameterize it in the future
//            wikiDTO.setEditable(true);
//            // TODO: For the moment, all wiki are always visible, parameterize it in the future
//            wikiDTO.setVisible(true);
//
//            WikiversionDTO version = new WikiversionDTO();
//            version.setContent(content);
//            version.setImage(image);
//            version.setUser(loginSession.getUser());
//            // TODO: For the moment, all versions are always active, parameterize it in the future
//            version.setActive(true);
//
//            wikiDTO.setCurrentVersion(version);
//            version.setWiki(wikiDTO);
//            if (constraintsService.isNotEmpty(parentWikiCanonical)) {
//                WikiDTO parentWikiDTO = wikiService.findByCanonical(getCurrentLanguage(request), parentWikiCanonical);
//                wikiDTO.setParent(parentWikiDTO);
//            }
//
//            wikiDTO.setGroup((GroupDTO) request.getAttribute(GroupWikiConstants.ATTRIBUTE_GROUPDTO));
//
//            wikiDTO = wikiService.create(wikiDTO);
//
//            response.sendRedirect(WikiReadServlet.routing(request, wikiDTO.getGroup().getCanonical(),
//                                                          wikiDTO.getCanonical()));
//        } else {
//            request.setAttribute(GroupWikiConstants.ATTRIBUTE_ERRORS, formErrors.getErrors());
//            forwardToJsp(request, response);
//        }
    }

    private void forwardToJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String groupCanonical = (String) request.getAttribute(GroupWikiConstants.ATTRIBUTE_GROUP_CANONICAL);
        String parent = (String) request.getAttribute(GroupWikiConstants.PARAMETER_PARENT);
        request.setAttribute(GroupWikiConstants.ATTRIBUTE_FORM_ROUTE, routing(request, groupCanonical, parent));
        forwardToJsp("/group/wiki/create.jsp", request, response);
    }
}
