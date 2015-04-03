package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.*;

import coop.ekologia.entity.group.Group;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.group.UserGroupPK;
import coop.ekologia.entity.user.User;

/**
 * Entity implementation class for Entity: UserGroupRoleUserGroup
 *
 */
@Entity
@Table(name="user_group_role_user_group")
@NamedQueries({
	@NamedQuery(name=UserGroupRoleUserGroup.FIND_BY_GROUPID, query="SELECT ugr FROM UserGroupRoleUserGroup ugr WHERE ugr.UserGroupRoleUserGroupPK.userGroupGroupId=:groupId"),
})
public class UserGroupRoleUserGroup implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_GROUPID = "UserGroupRoleUserGroup.findByGroupId";

	@EmbeddedId
	protected UserGroupRoleUserGroupPK UserGroupRoleUserGroupPK;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
	        @JoinColumn(name="group_id", referencedColumnName="group_id", insertable = false, updatable = false),
	        @JoinColumn(name="user_id", referencedColumnName="user_id", insertable = false, updatable = false)
	})
	private UserGroup userGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_user_group_id", referencedColumnName="id", insertable = false, updatable = false)
	private RoleUserGroup roleUserGroup;
	
	public UserGroupRoleUserGroup() {
		super();
	}

	public UserGroupRoleUserGroupPK getUserGroupRoleUserGroupPK() {
		return UserGroupRoleUserGroupPK;
	}

	public void setUserGroupRoleUserGroupPK(
			UserGroupRoleUserGroupPK userGroupRoleUserGroupPK) {
		UserGroupRoleUserGroupPK = userGroupRoleUserGroupPK;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public RoleUserGroup getRoleUserGroup() {
		return roleUserGroup;
	}

	public void setRoleUserGroup(RoleUserGroup roleUserGroup) {
		this.roleUserGroup = roleUserGroup;
	}
	
	
   
}
