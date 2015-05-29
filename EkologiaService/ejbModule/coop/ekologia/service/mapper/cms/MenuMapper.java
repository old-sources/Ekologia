package coop.ekologia.service.mapper.cms;

import coop.ekologia.DTO.cms.MenuDTO;
import coop.ekologia.entity.cms.Menu;
import coop.ekologia.service.mapper.Mapper;
import org.json.JSONObject;

public class MenuMapper extends Mapper<MenuDTO, Menu> {
    @Override
    public MenuDTO mapFromEntity(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setLang(menu.getLang());
        menuDTO.setRole(menu.getRole());
        menuDTO.setJson(menu.getJson());
        return menuDTO;
    }

    @Override
    public Menu mapToEntity(MenuDTO menuDTO) {
        if (menuDTO == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(menuDTO.getId());
        menu.setLang(menuDTO.getLang());
        menu.setRole(menuDTO.getRole());
        menu.setJson(menuDTO.getJson());
        return menu;
    }
}
