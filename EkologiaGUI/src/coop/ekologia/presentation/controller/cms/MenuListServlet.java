package coop.ekologia.presentation.controller.cms;

import coop.ekologia.DTO.role.RoleUserDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.CmsConstants;
import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.service.cms.PageServiceInterface;
import coop.ekologia.service.role.RoleServiceInterface;
import coop.ekologia.service.user.UserServiceInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @EJB
    private RoleServiceInterface roleService;

    @Inject
    private RoutingCentral router;

    @Inject
    protected GlobalRequestScope globalRequestScope;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(CmsConstants.ATTRIBUTE_ROLES, getRoles());
        forwardToJsp("cms/menus", request, response);
    }

    /**
     * This method returns the whole list of roles usable in current application.
     */
    private Map<String, String> getRoles() {
        List<RoleUserDTO> roleUsers = roleService.findAll(globalRequestScope.getLanguage());
        Map<String, String> roles = new HashMap<String, String>();
        for (RoleUserDTO roleUserDTO: roleUsers) {
            roles.put(roleUserDTO.getCode(), roleUserDTO.getDescription());
        }
        return Collections.unmodifiableMap(roles);
    }
}
