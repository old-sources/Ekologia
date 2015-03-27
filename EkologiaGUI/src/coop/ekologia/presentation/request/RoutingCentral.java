package coop.ekologia.presentation.request;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import coop.ekologia.presentation.constants.GroupWikiConstants;
import coop.ekologia.presentation.controller.cms.HomeServlet;
import coop.ekologia.presentation.controller.cms.PageFormServlet;
import coop.ekologia.presentation.controller.cms.PageListServlet;
import coop.ekologia.presentation.controller.group.GroupPresentationServlet;
import coop.ekologia.presentation.controller.group.wiki.WikiCreateServlet;
import coop.ekologia.presentation.controller.group.wiki.WikiReadServlet;
import coop.ekologia.presentation.controller.group.wiki.WikiUpdateServlet;
import coop.ekologia.presentation.controller.user.LoginConnectionServlet;
import coop.ekologia.presentation.controller.user.LoginDeconnectionServlet;
import coop.ekologia.presentation.controller.user.UserFormServlet;
import coop.ekologia.presentation.controller.user.UserListServlet;

@Named("routing")
@RequestScoped
public class RoutingCentral {
    @Inject
    private HttpServletRequest httpServletRequest;

    @Inject
    private ServletUtil servletUtil;

    public String getUserForm() {
        return servletUtil.getUrl(UserFormServlet.routing);
    }

    public String getPageForm() {
        return servletUtil.getUrl(PageFormServlet.routing);
    }

    public String getUserList() {
        return servletUtil.getUrl(UserListServlet.routing);
    }

    public String getPageList() {
        return servletUtil.getUrl(PageListServlet.routing);
    }

    public String getDeconnection() {
        return servletUtil.getUrl(LoginDeconnectionServlet.routing);
    }

    public String getConnection() {
        return servletUtil.getUrl(LoginConnectionServlet.routing);
    }
    
    public String getGroup(String canonical) {
    	return servletUtil.getUrl(GroupPresentationServlet.routing, map("groupCanonical", canonical));
    }

    public String getGroupWikiCreate(String groupCanonical, String parent) {
        Map<String, String> parameters = map("groupCanonical", groupCanonical);
        if (parent != null) {
            parameters.put(GroupWikiConstants.PARAMETER_PARENT, parent);
        }
        return servletUtil.getUrl(WikiCreateServlet.routing, parameters);
    }

    public String getGroupWikiCreate(String groupCanonical) {
        return getGroupWikiCreate(groupCanonical, null);
    }

    public String getGroupWikiRead(String groupCanonical, String wikiCanonical) {
        return servletUtil.getUrl(WikiReadServlet.routing,
                                  map("groupCanonical", groupCanonical, "wikiCanonical", wikiCanonical));
    }

    public String getGroupWikiUpdate(String groupCanonical, String wikiCanonical) {
        return servletUtil.getUrl(WikiUpdateServlet.routing,
                                  map("groupCanonical", groupCanonical, "wikiCanonical", wikiCanonical));
    }
    
    public String getHomepage() {
        return servletUtil.getUrl(HomeServlet.routing);
    }

    /**
     * Creates a map in terms of given alternated keys and values.
     * Be careful to not duplicate a key, otherwise, the last value will be kept.
     * 
     * <pre>
     * {@code examples:
     *    map("a", "b") => {a: "b"}
     *    map("a", "b", "c", "d") => {a: "b", c: "d"}
     *    map("a", "b", "c", "d", "a", "f") => {a: "f", c: "d"}
     *    map("a", "b", "c", "d", "e") => throws exception, there is not a event number of parameters.
     * }
     * </pre>
     * 
     * @param strings
     *            : The list of keys/values
     * @return A map with given keys/values
     * 
     * @throws IllegalArgumentException
     *             When there is not a even number of parameters.
     */
    private Map<String, String> map(String... strings) throws IllegalArgumentException {
        if (strings.length % 2 != 0) {
            throw new IllegalArgumentException("There is an odd number of parameters.");
        }

        Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < strings.length; i += 2) {
            result.put(strings[i], strings[i + 1]);
        }
        return result;
    }
}
