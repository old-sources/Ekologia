package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserGroupRoleUserGroupPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="user_group_user_id")
	private Integer userGroupUserId;

	@Column(name="user_group_group_id")
	private Integer userGroupGroupId;
	
	@Column(name="role_user_group_id")
	private Integer roleUserGroupId;

	public Integer getUserGroupUserId() {
		return userGroupUserId;
	}

	public void setUserGroupUserId(Integer userGroupUserId) {
		this.userGroupUserId = userGroupUserId;
	}

	public Integer getUserGroupGroupId() {
		return userGroupGroupId;
	}

	public void setUserGroupGroupId(Integer userGroupGroupId) {
		this.userGroupGroupId = userGroupGroupId;
	}

	public Integer getRoleUserGroupId() {
		return roleUserGroupId;
	}

	public void setRoleUserGroupId(Integer roleUserGroupId) {
		this.roleUserGroupId = roleUserGroupId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserGroupRoleUserGroupPK)) {
			return false;
		}
		UserGroupRoleUserGroupPK castOther = (UserGroupRoleUserGroupPK)other;
		return 
			this.userGroupUserId.equals(castOther.userGroupUserId)
			&& this.userGroupGroupId.equals(castOther.userGroupGroupId)
			&& this.roleUserGroupId.equals(castOther.roleUserGroupId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userGroupUserId.hashCode();
		hash = hash * prime + this.userGroupGroupId.hashCode();
		hash = hash * prime + this.roleUserGroupId.hashCode();

		return hash;
	}
}
