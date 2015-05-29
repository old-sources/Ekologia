package coop.ekologia.service.cms;

import coop.ekologia.DTO.cms.MenuDTO;

import javax.ejb.Local;

@Local
public interface MenuServiceInterface {
    /**
     * Finds menu for given language and role.
     *
     * @param language The language of menus
     * @param role     The role of menu
     * @return The root list
     */
    MenuDTO find(String language, String role);

    /**
     * Updates given menu to database
     *
     * @param menuDTO The list to update
     */
    void update(MenuDTO menuDTO);
}
