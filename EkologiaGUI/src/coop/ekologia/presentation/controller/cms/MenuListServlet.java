package coop.ekologia.presentation.controller.cms;

import coop.ekologia.DTO.cms.MenuConfigurationDTO;
import coop.ekologia.DTO.cms.MenuConfigurationParameterDTO;
import coop.ekologia.DTO.cms.MenuConfigurationParameterDTO.MenuConfigurationParameterConstraint;
import coop.ekologia.DTO.cms.MenuDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.CmsConstants;
import coop.ekologia.presentation.controller.FormErrors;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.service.cms.MenuServiceInterface;
import coop.ekologia.service.cms.PageServiceInterface;
import coop.ekologia.service.user.UserServiceInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/admin/cms/menus")
public class MenuListServlet extends EkologiaServlet {
    private static final long serialVersionUID = 8973401976843374292L;

    public static final String routing = "/admin/cms/menus";

    @EJB
    private UserServiceInterface userService;

    @EJB
    private StringUtilitiesInterface stringUtilities;

    @EJB
    private PageServiceInterface pageService;

    @Inject
    private RoutingCentral router;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(CmsConstants.ATTRIBUTE_ROLES, getRoles());
        forwardToJsp("cms/menus", request, response);
    }

    /**
     * This method returns the whole list of roles usable in current application.
     */
    private Map<String, String> getRoles() {
        // TODO: take roles from database as soon as the development is done.
        Map<String, String> result = new HashMap<String, String>();
        result.put("all", "Tous");
        result.put("admin", "Admin");
        return result;
    }
}
