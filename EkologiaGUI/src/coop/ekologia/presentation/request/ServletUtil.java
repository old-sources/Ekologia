package coop.ekologia.presentation.request;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("util")
@RequestScoped
public class ServletUtil {

    @Inject
    private HttpServletRequest httpServletRequest;

    @Inject
    private GlobalRequestScope globalRequestScope;

    /**
     * Returns an absolute url (depends on all context variables).
     * 
     * Parameters will be added into route in terms of route variables defined by {varName} and request attribute (?param=value).
     * 
     * <pre>{@code example: (info: {key: value} represents a map).
     *     getUrl("/{a}/{b}", {a: 11, b: 22}) => "/ctx/lang/11/22"
     *     getUrl("/{a}/{b}", {a: 11, b: 22, c: 33}) => "/ctx/lang/11/22?c=33"
     *     getUrl("/path/to/module/{a}/{b}", {a: 11, b: 22, c: 33}) => "/ctx/lang/path/to/module/11/22?c=33"
     *     getUrl("/path/to/module", {a: 11, b: 22, c: 33}) => "/ctx/lang/path/to/module?a=11&b=22&c=33"
     * }</pre>
     * 
     * @param request
     *            The current request
     * @param route
     *            The route to send the user (ex: {@code /group/wiki/group-1/wiki-1})
     * @param parameters
     *            The parameters of the route.
     * @return The absolute url to given route
     */
    public String getUrl(String route, Map<String, ? extends Object> parameters) {
        StringBuilder result = new StringBuilder();
        String contextPath = httpServletRequest.getContextPath();

        result.append(contextPath);
        if (!contextPath.matches("(.+)/$")) {
            result.append("/");
        }

        // Adds the current language to route.
        result.append(globalRequestScope.getLanguage()).append("/");

        if (route.matches("^/(.*)")) {
            result.append(route.substring(1));
        } else {
            result.append(route);
        }

        return replaceParameters(result.toString(), parameters);
    }
    
    /**
     * Returns an absolute url (depends on all context variables).
     * 
     * @param request
     *            The current request
     * @param route
     *            The route to send the user (ex: {@code /group/wiki/group-1/wiki-1})
     * @return The absolute url to given route
     * 
     * @see #getUrl(String, Map)
     */
    public String getUrl(String route) {
        return getUrl(route, null);
    }

    /**
     * Adapts the url to replace parameters.
     * 
     * <pre>{@code example: (info: {key: value} represents a map).
     *     getUrl("/{a}/{b}", {a: 11, b: 22}) => "/11/22"
     *     getUrl("/{a}/{b}", {a: 11, b: 22, c: 33}) => "/11/22?c=33"
     *     getUrl("/path/to/module/{a}/{b}", {a: 11, b: 22, c: 33}) => "/path/to/module/11/22?c=33"
     *     getUrl("/path/to/module", {a: 11, b: 22, c: 33}) => "/path/to/module?a=11&b=22&c=33"
     * }</pre>
     * 
     * @param url The url to change.
     * @param parameters The parameters to use
     * 
     * @return The url with parameters.
     */
    private String replaceParameters(String url, Map<String, ? extends Object> parameters) {
        // Replacement of parameters
        if (parameters != null && parameters.size() > 0) {
            boolean hasQuestionMark = url.contains("?");
            for (String key: parameters.keySet()) {
                if (url.contains("{" + key + "}")) {
                    url = url.replace("{" + key + "}", parameters.get(key).toString());
                } else {
                    if (!hasQuestionMark) {
                        url += '?';
                        hasQuestionMark = true;
                    } else {
                        url += '&';
                    }
                    url += key + '=' + parameters.get(key).toString();
                }
            }
        }

        return url;
    }

}
