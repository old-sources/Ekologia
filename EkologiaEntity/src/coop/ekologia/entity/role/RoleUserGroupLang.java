package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.*;

import coop.ekologia.entity.group.UserGroup;

/**
 * Entity implementation class for Entity: RoleUserGroupLang
 *
 */
@Entity
@Table(name="role_user_group_lang")
@NamedQueries({
	@NamedQuery(name=RoleUserGroupLang.FIND_BY_LANGUE, query="SELECT r FROM RoleUserGroupLang r WHERE r.langue = :langue")
})
public class RoleUserGroupLang implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_LANGUE = "RoleUserGroupLang.findByLangue";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="langue")
	private String langue;
	
	@ManyToOne
	@JoinColumn(name="role_user_group_id")
	private RoleUserGroup roleUserGroup;
	
	public RoleUserGroupLang() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public RoleUserGroup getRoleUserGroup() {
		return roleUserGroup;
	}

	public void setRoleUserGroup(RoleUserGroup roleUserGroup) {
		this.roleUserGroup = roleUserGroup;
	}
	
	
   
}
