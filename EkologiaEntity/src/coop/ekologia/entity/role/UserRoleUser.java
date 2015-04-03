package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.*;

import coop.ekologia.entity.user.User;

/**
 * Entity implementation class for Entity: UserRoleUser
 *
 */
@Entity
@Table(name="user_role_user")
public class UserRoleUser implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected UserRoleUserPK UserRoleUserPK;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_user_id", referencedColumnName="id", insertable = false, updatable = false)
	private RoleUser roleUser;
	
	public UserRoleUser() {
		super();
	}

	public UserRoleUserPK getUserRoleUserPK() {
		return UserRoleUserPK;
	}

	public void setUserRoleUserPK(UserRoleUserPK userRoleUserPK) {
		UserRoleUserPK = userRoleUserPK;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RoleUser getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}
	
	
   
}
