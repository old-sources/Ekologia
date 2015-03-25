package coop.ekologia.service.mapper.group;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.group.Group;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.group.UserGroupPK;
import coop.ekologia.entity.user.User;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.mapper.user.UserMapper;

public class GroupMapper extends Mapper<GroupDTO, Group> {

	@Inject
	private UserMapper userMapper;

	@Override
	public GroupDTO mapFromEntity(Group group) {
	    if (group == null) {
	        return null;
	    }
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getId());
		groupDTO.setCanonical(group.getCanonical());
		groupDTO.setName(group.getName());
		groupDTO.setDescription(group.getDescription());
		groupDTO.setIcon(group.getIcon());
		for (UserGroup userGroup : group.getUserGroups()) {
			groupDTO.getUsers().add(
					userMapper.mapFromEntity(userGroup.getUser()));
			String[] roles;
			if (userGroup.getRoles() == null) {
			    roles = new String[]{};
			} else {
			    roles = userGroup.getRoles().split(",");
			}
			if (Arrays.asList(roles).contains("admin")){
				groupDTO.getUsersAdmin().add(userMapper.mapFromEntity(userGroup.getUser()));
			}
		}
		
		return groupDTO;
	}

	@Override
	public Group mapToEntity(GroupDTO groupDTO) {
		Group group = new Group();
		if (groupDTO.getId() != null) {
			group.setId(groupDTO.getId());
		}
		group.setCanonical(groupDTO.getCanonical());
		group.setName(groupDTO.getName());
		group.setDescription(groupDTO.getCanonical());
		group.setIcon(groupDTO.getIcon());
		
		return group;
	}


	public UserGroup createUserGroup(Group group, User user) {
		UserGroup userGroup = new UserGroup();
		UserGroupPK userGroupPK = new UserGroupPK();
		
		userGroupPK.setGroupId(group.getId());
		userGroupPK.setUserId(user.getId());
		
		userGroup.setUserGroupPK(userGroupPK);
		userGroup.setGroup(group);
		userGroup.setUser(user);
		userGroup.setRoles("admin");
		userGroup.setAccepted(new Date());
		userGroup.setRequested(new Date());
		
		return userGroup;
	}

}
