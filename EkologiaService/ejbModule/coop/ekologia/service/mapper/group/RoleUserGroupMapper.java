package coop.ekologia.service.mapper.group;

import java.util.ArrayList;
import java.util.List;

import coop.ekologia.DTO.group.RoleUserGroupDTO;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.role.RoleUserGroup;
import coop.ekologia.entity.role.RoleUserGroupLang;
import coop.ekologia.entity.role.UserGroupRoleUserGroup;
import coop.ekologia.entity.role.UserGroupRoleUserGroupPK;
import coop.ekologia.service.mapper.Mapper;

public class RoleUserGroupMapper extends Mapper<RoleUserGroupDTO, RoleUserGroupLang> {

	@Override
	public RoleUserGroupDTO mapFromEntity(RoleUserGroupLang roleUserGroupLang) {
		RoleUserGroupDTO roleUserGroupDTO = new RoleUserGroupDTO();
		roleUserGroupDTO.setId(roleUserGroupLang.getRoleUserGroup().getId());
		roleUserGroupDTO.setDescription(roleUserGroupLang.getDescription());
		roleUserGroupDTO.setCodeRole(roleUserGroupLang.getRoleUserGroup().getCode());
		return roleUserGroupDTO;
	}

	
	public RoleUserGroup mapToEntityRole(RoleUserGroupDTO roleUserGroupDTO) {
		RoleUserGroup roleUserGroup = new RoleUserGroup();
		roleUserGroup.setId(roleUserGroupDTO.getId());
		roleUserGroup.setCode(roleUserGroupDTO.getCodeRole());
		
		return roleUserGroup;
	}


	@Override
	public RoleUserGroupLang mapToEntity(RoleUserGroupDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserGroupRoleUserGroup> mapToUserGroupRoleUserGroup(UserGroup userGroup, List<RoleUserGroup> listRoleUserGroup) {
		List<UserGroupRoleUserGroup> result = new ArrayList<UserGroupRoleUserGroup>();
		
		for (RoleUserGroup roleUserGroup : listRoleUserGroup) {
			UserGroupRoleUserGroup userGroupRoleUserGroup = new UserGroupRoleUserGroup();
			UserGroupRoleUserGroupPK userGroupRoleUserGroupPK = new UserGroupRoleUserGroupPK();
			
			userGroupRoleUserGroupPK.setUserGroupUserId(userGroup.getGroup().getId());
			userGroupRoleUserGroupPK.setUserGroupUserId(userGroup.getUser().getId());
			
			userGroupRoleUserGroup.setUserGroup(userGroup);
			userGroupRoleUserGroup.setRoleUserGroup(roleUserGroup);
			userGroupRoleUserGroup.setUserGroupRoleUserGroupPK(userGroupRoleUserGroupPK);
			
			result.add(userGroupRoleUserGroup);
		}
		
		return result;
	}

}
