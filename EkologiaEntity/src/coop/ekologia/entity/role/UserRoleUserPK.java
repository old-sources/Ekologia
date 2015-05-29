package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleUserPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="role_user_id")
	private Integer roleUserId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleUserId() {
		return roleUserId;
	}

	public void setRoleUserId(Integer roleUserId) {
		this.roleUserId = roleUserId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRoleUserPK)) {
			return false;
		}
		UserRoleUserPK castOther = (UserRoleUserPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.roleUserId.equals(castOther.roleUserId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.roleUserId.hashCode();
		
		return hash;
	}
	
}
