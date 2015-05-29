package coop.ekologia.DTO.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import coop.ekologia.DTO.user.UserDTO;

public class UserGroupDTO implements Serializable{

	private static final long serialVersionUID = -5578730527617071323L;

	private GroupDTO group;
	
	private UserDTO user;
	
	private Collection<RoleUserGroupDTO> roleUserGroups;

	public UserGroupDTO(){
		roleUserGroups = new ArrayList<RoleUserGroupDTO>();
	}
	
	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Collection<RoleUserGroupDTO> getRoleUserGroups() {
		return roleUserGroups;
	}

	public void setRoleUserGroups(Collection<RoleUserGroupDTO> roleUserGroups) {
		this.roleUserGroups = roleUserGroups;
	}
	
	
}
