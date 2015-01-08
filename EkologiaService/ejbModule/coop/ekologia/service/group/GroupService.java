package coop.ekologia.service.group;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.entity.group.Group;
import coop.ekologia.service.mapper.group.GroupMapper;

@Stateless
public class GroupService implements GroupServiceInterface {
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private GroupMapper groupMapper;
	
	
	@Override
	public GroupDTO findByCanonical(String canonical) {
		Query query = em.createNamedQuery(Group.FIND_BY_CANONICAL);
        query.setParameter("canonical", canonical);
        if (query.getResultList().isEmpty()) {
        	return null;
        } else {
        	Group result = (Group)query.getSingleResult();
            return groupMapper.mapFromEntity(result);
        }
	}
}
