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
import coop.ekologia.service.utils.StringUtilitiesInterface;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/admin/cms/menu/*")
public class MenuServlet extends EkologiaServlet {
    private static final long serialVersionUID = 8973406351723374292L;

    public static final String routing = "/admin/cms/menu/{role}";

    @EJB
    private MenuServiceInterface menuService;

    @EJB
    private StringUtilitiesInterface stringUtilities;

    @EJB
    private PageServiceInterface pageService;

    @Inject
    private RoutingCentral router;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Matcher m = Pattern.compile("\\w*\\/menu\\/(\\S*)")
                .matcher(request.getRequestURI());
        if (m.find()) {
            String role = m.group(1);

            MenuDTO menu = menuService.find(getCurrentLanguage(request), role);
            if (menu != null) {
                request.setAttribute(CmsConstants.ATTRIBUTE_MENU, menu);
                request.setAttribute(CmsConstants.ATTRIBUTE_CONFIGURATION, getAvailableMenuConfiguration());
                request.setAttribute(CmsConstants.ATTRIBUTE_ROLE, role);
                forwardToJsp("cms/menu", request, response);
            } else {
                forwardToJsp("cms/menuNotFound", request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Matcher m = Pattern.compile("\\w*\\/menu\\/(\\S*)")
                .matcher(request.getRequestURI());
        if (m.find()) {
            String role = m.group(1);

            MenuDTO menu = menuService.find(getCurrentLanguage(request), role);
            if (menu != null) {
                String strJson = request.getParameter("json");
                // TODO: check if data are valid.

                menu.setJson(strJson);
                menuService.update(menu);
                response.sendRedirect(router.getMenuListManagement());
            } else {
                forwardToJsp("cms/menuNotFound", request, response);
            }
        }
    }

    /**
     * Returns the available menu configuration usable for menus.
     */
    private List<MenuConfigurationDTO> getAvailableMenuConfiguration() {
        //TODO: Map is built by code here, should we export this to another class or configuration file?
        return Arrays.asList(
                new MenuConfigurationDTO()
                        .setCode("home"),
                new MenuConfigurationDTO()
                        .setCode("page")
                        .addParameter(
                                new MenuConfigurationParameterDTO()
                                        .setName("canonical")
                                        .addConstraint(new MenuConfigurationParameterConstraint() {
                                            @Override
                                            public String getErrorCode() {
                                                return "cms.menu.page.canonical.unknown";
                                            }

                                            @Override
                                            public boolean verify(String value) {
                                                return pageService.exists(value);
                                            }
                                        })
                        )
        );
    }
}
