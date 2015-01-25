package coop.ekologia.service.group;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.group.Group;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.user.User;
import coop.ekologia.service.mapper.group.GroupMapper;
import coop.ekologia.service.mapper.user.UserMapper;

@Stateless
public class GroupService implements GroupServiceInterface {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private GroupMapper groupMapper;

	@Inject
	private UserMapper userMapper;

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
				.createNamedQuery("SELECT g FROM Group g WHERE g.name = :name");
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
	public void updateUserGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		User user = userMapper.mapToEntity(groupDTO.getFirstAdmin());
		em.merge(user);
		UserGroup userGroup = groupMapper.createUserGroup(group, user);		
		em.merge(userGroup);		

	}

	@Override
	public GroupDTO insertGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		em.persist(group);
		return groupMapper.mapFromEntity(group);
	}

	@Override
	public void insertUserGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		User user = userMapper.mapToEntity(groupDTO.getFirstAdmin());

		em.merge(user);

		UserGroup userGroup = groupMapper.createUserGroup(group, user);

		em.persist(userGroup);

	}

	@Override
	public void deleteGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		group = em.merge(group);
		List<UserGroup> listUserGroup = getListUserGroup(groupDTO.getId());
		if (!listUserGroup.isEmpty()) {
			for (UserGroup userGroup : listUserGroup) {
				em.remove(userGroup);
			}
		}
		em.remove(group);
	}

	@Override
	public GroupDTO getGroupById(GroupDTO dto) {
		Group group = em.find(Group.class, dto.getId());
		return groupMapper.mapFromEntity(group);
	}

	@Override
	public List<UserGroup> getListUserGroup(Integer groupId){
		List<UserGroup> listUserGroup = new ArrayList<UserGroup>();
		Query query = em
				.createNamedQuery(UserGroup.FIND_BY_GROUPID);
		query.setParameter("groupId", groupId);
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			listUserGroup = query.getResultList();	
		}
		return listUserGroup;
		
	}

}
