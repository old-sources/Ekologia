package coop.ekologia.service.role;

import coop.ekologia.DTO.role.RoleUserDTO;
import coop.ekologia.entity.role.RoleUser;
import coop.ekologia.service.mapper.role.RoleUserMapper;
import coop.ekologia.service.utils.ListUtilitiesInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RoleService implements RoleServiceInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    RoleUserMapper roleUserMapper;

    @EJB
    ListUtilitiesInterface listUtilities;

    @Override
    public List<RoleUserDTO> findAll(String lang) {
        TypedQuery<RoleUser> query = entityManager.createNamedQuery(RoleUser.FIND_ALL, RoleUser.class);
        query.setParameter("lang", lang);
        List<RoleUser> roleList = query.getResultList();
        return listUtilities.asList(roleUserMapper.mapFromEntity(roleList));
    }
}
