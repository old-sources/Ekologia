package coop.ekologia.presentation.request;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We use an {@link ApplicationScoped} class because all routes are available for all users.
 */
@ApplicationScoped
public class RoutingRegistration {
    private Map<String, RoutingCallable> routes = new HashMap<String, RoutingCallable>();

    @Inject
    private RoutingCentral router;

    public RoutingRegistration() {
        routes.put("home", new RoutingCallable() {
            @Override
            public String getUrl(Map<String, String> parameter) {
                return router.getHomepage();
            }
        });
        routes.put("page", new RoutingCallable() {
            @Override
            public String getUrl(Map<String, String> parameter) {
                return router.getPage(parameter.get("canonical"));
            }

            @Override
            public List<String> getRequiredParameters() {
                return Arrays.asList("canonical");
            }
        });
        routes.put("userList", new RoutingCallable() {
            @Override
            public String getUrl(Map<String, String> parameter) {
                return router.getUserList();
            }
        });
        routes.put("pageList", new RoutingCallable() {
            @Override
            public String getUrl(Map<String, String> parameter) {
                return router.getPageList();
            }
        });
        routes.put("adminGroupList", new RoutingCallable() {
            @Override
            public String getUrl(Map<String, String> parameter) {
                return router.getAdminGroupList();
            }
        });
        routes.put("menuListManagement", new RoutingCallable() {
            @Override
            public String getUrl(Map<String, String> parameter) {
                return router.getMenuListManagement();
            }
        });
    }

    public String getUrlByRouteAndParameters(String route, Map<String, String> parameters) {
        if (routes.containsKey(route)) {
            RoutingCallable routing = routes.get(route);
            for (String requiredParameter: routing.getRequiredParameters()) {
                if (!parameters.containsKey(requiredParameter)) {
                    throw new RuntimeException("The given route " + route + " requires parameter " + requiredParameter);
                }
            }

            for (String parameter: parameters.keySet()) {
                if (!routing.getRequiredParameters().contains(parameter) && !routing.getOptionalParameters().contains(parameter)) {
                    throw new RuntimeException("The given route " + route + " does not accept parameter " + parameter);
                }
            }

            return routing.getUrl(parameters);
        } else {
            throw new RuntimeException("The given route " + route + " does not exist");
        }
    }

    public abstract class RoutingCallable {
        public List<String> getRequiredParameters() {
            return Arrays.asList();
        }

        public List<String> getOptionalParameters() {
            return Arrays.asList();
        }

        public abstract String getUrl(Map<String, String> parameter);
    }
}
