package coop.ekologia.service.group;

import java.util.ArrayList;
import java.util.List;

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
	public GroupDTO findGroupByCanonical(String canonical) {
		Query query = em.createNamedQuery(Group.FIND_BY_CANONICAL);
		query.setParameter("canonical", canonical);
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			Group result = (Group) query.getSingleResult();
			return groupMapper.mapFromEntity(result);
		}
	}

	@Override
	public List<GroupDTO> findGroupAll() {
		Query query = em.createNamedQuery(Group.FIND_ALL);
		List<GroupDTO> result = new ArrayList<GroupDTO>();
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			List<Group> resultList = query.getResultList();
			for (Group group : resultList) {
				result.add(groupMapper.mapFromEntity(group));
			}
			return result;
		}
	}

	@Override
	public GroupDTO findGroupByName(String name) {
		Query query = em
				.createNamedQuery("SELECT g FROM Group g WHERE g.name=:name");
		query.setParameter("name", name);
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			Group result = (Group) query.getSingleResult();
			return groupMapper.mapFromEntity(result);
		}
	}

	@Override
	public GroupDTO updateGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		group = em.merge(group);
		return groupMapper.mapFromEntity(group);
	}

	@Override
	public GroupDTO insertGroup(GroupDTO groupDTO) {
		Integer maxId = 0;
		if (findGroupAll() != null && !findGroupAll().isEmpty()) {
			List<GroupDTO> groupList = findGroupAll();
			for (GroupDTO dto : groupList) {
				if (dto.getId() > maxId) {
					maxId = dto.getId();
				}
			}
		}
		Group group = groupMapper.mapToEntityInsert(groupDTO, maxId);
		em.persist(group);
		return groupMapper.mapFromEntity(group);
	}

	@Override
	public void deleteGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		group = em.merge(group);
		em.remove(group);
	}

	@Override
	public GroupDTO getGroupById(GroupDTO dto) {
		Group group = em.find(Group.class, dto.getId());
		return groupMapper.mapFromEntity(group);
	}

}
