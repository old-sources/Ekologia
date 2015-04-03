package coop.ekologia.service.group;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.group.RoleUserGroupDTO;
import coop.ekologia.DTO.group.UserGroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.group.Group;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.role.RoleUserGroup;
import coop.ekologia.entity.role.RoleUserGroupLang;
import coop.ekologia.entity.role.UserGroupRoleUserGroup;
import coop.ekologia.entity.role.UserGroupRoleUserGroupPK;
import coop.ekologia.entity.user.User;
import coop.ekologia.service.mapper.group.GroupMapper;
import coop.ekologia.service.mapper.group.RoleUserGroupMapper;
import coop.ekologia.service.mapper.user.UserMapper;

@Stateless
public class GroupService implements GroupServiceInterface {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private GroupMapper groupMapper;

	@Inject
	private UserMapper userMapper;
	
	@Inject
	private RoleUserGroupMapper roleUserGroupMapper;

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
	public void updateUserGroup(GroupDTO groupDTO, UserDTO userDTO) {
		/*Group group = groupMapper.mapToEntity(groupDTO);
		User user = userMapper.mapToEntity(groupDTO.getFirstAdmin());
		em.merge(user);
		UserGroup userGroup = groupMapper.createUserGroup(group, user);		
		em.merge(userGroup);	*/	

	}

	@Override
	public GroupDTO insertGroup(GroupDTO groupDTO) {
		Group group = groupMapper.mapToEntity(groupDTO);
		em.persist(group);
		return groupMapper.mapFromEntity(group);
	}

	//Méthode qui persist le UserGroup
	@Override
	public void insertUserGroup(UserGroupDTO userGroupDTO) {
		Group group = groupMapper.mapToEntity(userGroupDTO.getGroup());
		User user = userMapper.mapToEntity(userGroupDTO.getUser());
		List<RoleUserGroup> listRoleUserGroup = new ArrayList<RoleUserGroup>();
		
		for (RoleUserGroupDTO roleUserGroupDTO : userGroupDTO.getRoleUserGroups()) {
			RoleUserGroup roleUserGroup = roleUserGroupMapper.mapToEntityRole(roleUserGroupDTO);
			listRoleUserGroup.add(roleUserGroup);
		}
		
		em.merge(user);
		em.merge(group);
		em.merge(listRoleUserGroup);
		UserGroup userGroup = groupMapper.createUserGroup(group, user);

		em.persist(userGroup);
		
		List<UserGroupRoleUserGroup> listUserGroupRoleUserGroup = roleUserGroupMapper.mapToUserGroupRoleUserGroup(userGroup,listRoleUserGroup);
		
		for (UserGroupRoleUserGroup userGroupRoleUserGroup : listUserGroupRoleUserGroup) {
			em.persist(userGroupRoleUserGroup);
		}

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

	@Override
	public List<RoleUserGroupDTO> getAllRoleUserGroup(String codeLangue) {
		List<RoleUserGroupLang> listRoleUserGroupLang = new ArrayList<RoleUserGroupLang>();
		List<RoleUserGroupDTO> listRoleUserGroupDTO = new ArrayList<RoleUserGroupDTO>();
		Query query = em.createNamedQuery(RoleUserGroupLang.FIND_BY_LANGUE);
		query.setParameter("langue", codeLangue);
		
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			listRoleUserGroupLang = query.getResultList();	
		}
		
		for (RoleUserGroupLang roleUserGroupLang : listRoleUserGroupLang) {
			listRoleUserGroupDTO.add(roleUserGroupMapper.mapFromEntity(roleUserGroupLang));
		}
		
		return listRoleUserGroupDTO;
	}

}
