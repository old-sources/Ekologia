package coop.ekologia.service.cms;

import coop.ekologia.DTO.cms.MenuDTO;
import coop.ekologia.entity.cms.Menu;
import coop.ekologia.service.mapper.cms.MenuMapper;
import coop.ekologia.service.utils.ListUtilitiesInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MenuService implements MenuServiceInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    MenuMapper menuMapper;

    @EJB
    ListUtilitiesInterface listUtilities;

    @Override
    public MenuDTO find(String language, String role) {
        TypedQuery<Menu> query = entityManager.createNamedQuery(Menu.FIND, Menu.class);
        query.setParameter("lang", language);
        query.setParameter("role", role);
        List<Menu> menuList = query.getResultList();
        return menuMapper.mapFromEntity(listUtilities.firstOrNull(menuList));
    }

    @Override
    public void update(MenuDTO menuDTO) {
        Menu menu = menuMapper.mapToEntity(menuDTO);
        entityManager.persist(menu);
        entityManager.flush();
    }
}
