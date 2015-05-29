package coop.ekologia.DTO.cms;

import java.util.ArrayList;
import java.util.List;

public class MenuApplicationDTO {
    private String name;
    private String route;
    private List<MenuApplicationDTO> children = new ArrayList<MenuApplicationDTO>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<MenuApplicationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuApplicationDTO> children) {
        this.children = children;
    }
}
