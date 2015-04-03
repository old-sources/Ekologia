package coop.ekologia.entity.group;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import coop.ekologia.entity.role.UserGroupRoleUserGroup;
import coop.ekologia.entity.user.User;


/**
 * The persistent class for the user_group database table.
 */
@Entity
@Table(name="user_group")
@NamedQueries({
	@NamedQuery(name=UserGroup.FIND_ALL, query="SELECT u FROM UserGroup u"),
	@NamedQuery(name=UserGroup.FIND, query="SELECT ug FROM UserGroup ug WHERE ug.userGroupPK.userId=:userId AND ug.userGroupPK.groupId=:groupId"),
	@NamedQuery(name=UserGroup.FIND_BY_GROUPID, query="SELECT ug FROM UserGroup ug WHERE ug.userGroupPK.groupId=:groupId"),
	@NamedQuery(name=UserGroup.FIND_BY_USERID, query="SELECT ug FROM UserGroup ug WHERE ug.userGroupPK.userId=:userId")
})
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "UserGroup.findAll";
	public static final String FIND = "UserGroup.find";
	public static final String FIND_BY_GROUPID = "UserGroup.findByGroupId";
	public static final String FIND_BY_USERID = "UserGroup.findByUserId";

	@EmbeddedId
	protected UserGroupPK userGroupPK;
	
	@Temporal(TemporalType.DATE)
	private Date accepted;

	@Temporal(TemporalType.DATE)
	private Date requested;
	
	@Column(name="roles")
	private String roles;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="group_id", referencedColumnName="id", insertable = false, updatable = false)
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	private User user;

	@OneToMany(mappedBy="userGroup")
	private List<UserGroupRoleUserGroup> userGroupRoleUserGroups;
	
	public UserGroup() {
	}


	public Date getAccepted() {
		return this.accepted;
	}

	public void setAccepted(Date accepted) {
		this.accepted = accepted;
	}

	public Date getRequested() {
		return this.requested;
	}

	public void setRequested(Date requested) {
		this.requested = requested;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	public UserGroupPK getUserGroupPK() {
		return userGroupPK;
	}


	public void setUserGroupPK(UserGroupPK userGroupPK) {
		this.userGroupPK = userGroupPK;
	}
	
	
}