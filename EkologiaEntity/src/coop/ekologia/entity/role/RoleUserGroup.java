package coop.ekologia.entity.role;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RoleUserGroup
 *
 */
@Entity
@Table(name="role_user_group")
public class RoleUserGroup implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="code")
	private String code;
	
	@OneToMany(mappedBy="roleUserGroup")
	private List<RoleUserGroupLang> roleUserGroupLangs;

	@OneToMany(mappedBy="roleUserGroup")
	private List<UserGroupRoleUserGroup> userGroupRoleUserGroups;
	
	public RoleUserGroup() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<RoleUserGroupLang> getRoleUserGroupLangs() {
		return roleUserGroupLangs;
	}

	public void setRoleUserGroupLangs(List<RoleUserGroupLang> roleUserGroupLangs) {
		this.roleUserGroupLangs = roleUserGroupLangs;
	}

	public List<UserGroupRoleUserGroup> getUserGroupRoleUserGroups() {
		return userGroupRoleUserGroups;
	}

	public void setUserGroupRoleUserGroups(
			List<UserGroupRoleUserGroup> userGroupRoleUserGroups) {
		this.userGroupRoleUserGroups = userGroupRoleUserGroups;
	}
   
	
}
