package coop.ekologia.presentation.session;

import coop.ekologia.DTO.cms.MenuApplicationDTO;
import coop.ekologia.DTO.cms.MenuDTO;
import coop.ekologia.presentation.request.GlobalRequestScope;
import coop.ekologia.presentation.request.RoutingRegistration;
import coop.ekologia.service.cms.MenuServiceInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

/**
 * Manages the cache for all menu in terms of roles.
 * Because menu is common for all users, we use an {@link ApplicationScoped} class permitting to cache it for all users.
 * The cache invalidation must be performed manually when updated.
 * This class will never check database when a menu is already cached.
 */
@ApplicationScoped
public class MenuApplication {
    private final Map<String, Map<String, List<MenuApplicationDTO>>> cache = new HashMap<String, Map<String, List<MenuApplicationDTO>>>();
    private final Object sync = new Object();

    @EJB
    private MenuServiceInterface menuService;

    @Inject
    private RoutingRegistration router;

    @Inject
    private GlobalRequestScope globalRequestScope;

    public List<MenuApplicationDTO> find(String language, String role) {
        synchronized (sync) {
            cache.putIfAbsent(language, new HashMap<String, List<MenuApplicationDTO>>());
            Map<String, List<MenuApplicationDTO>> cacheLanguage = cache.get(language);
            if (!cacheLanguage.containsKey(role)) {
                MenuDTO menuDTO = menuService.find(language, role);
                if (menuDTO != null) {
                    JSONArray menu = new JSONArray(menuDTO.getJson());
                    List<MenuApplicationDTO> menuApplicationDTOList = new ArrayList<MenuApplicationDTO>();
                    for (int i = 0, c = menu.length(); i < c; i++) {
                        menuApplicationDTOList.add(processOne(menu.getJSONObject(i)));
                    }
                    cacheLanguage.put(role, Collections.unmodifiableList(menuApplicationDTOList));
                } else {
                    cacheLanguage.put(role, Collections.unmodifiableList(new ArrayList<MenuApplicationDTO>()));
                }
            }
            return cacheLanguage.get(role);
        }
    }

    public Map<String, List<MenuApplicationDTO>> findByLanguage(String language) {
        synchronized (sync) {
            Map<String, List<MenuApplicationDTO>> result = new HashMap<String, List<MenuApplicationDTO>>();
            for (String role: getRoles()) {
                result.put(role, find(language, role));
            }
            return result;
        }
    }

    /**
     * This method returns the whole list of roles usable in current application.
     */
    private List<String> getRoles() {
        // TODO: take roles from database as soon as the development is done.
        return Arrays.asList("all", "admin");
    }

    public void invalidate(String language, String role) {
        synchronized (sync) {
            if (cache.containsKey(language)) {
                cache.get(language).remove(role);
            }
        }
    }

    private MenuApplicationDTO processOne(JSONObject menuItem) {
        MenuApplicationDTO result = new MenuApplicationDTO();

        result.setName(menuItem.getString("name"));

        String route = menuItem.getString("route");
        Map<String, String> parameters = new HashMap<String, String>();
        if (menuItem.has("parameters")) {
            JSONObject parametersJson = menuItem.getJSONObject("parameters");
            for (String key: parametersJson.keySet()) {
                parameters.put(key, parametersJson.getString(key));
            }
        }
        result.setRoute(router.getUrlByRouteAndParameters(route, parameters));

        JSONArray childrenJson = menuItem.getJSONArray("children");
        for (int i = 0, c = childrenJson.length() ; i < c ; i++) {
            result.getChildren().add(processOne(childrenJson.getJSONObject(i)));
        }
        return result;
    }
}
