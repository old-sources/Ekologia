package coop.ekologia.entity.user;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the user_group database table.
 */
@Entity
@Table(name="user_group")
@IdClass(UserGroupPK.class)
@NamedQueries({
	@NamedQuery(name=UserGroup.FIND_ALL, query="SELECT u FROM UserGroup u"),
	@NamedQuery(name=UserGroup.FIND, query="SELECT ug FROM UserGroup ug WHERE ug.userId=:userId AND ug.groupId=:groupId")
})
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "UserGroup.findAll";
	public static final String FIND = "UserGroup.find";

	@Id
	@Column(name="user_id")
	private Integer userId;
	
	@Id
	@Column(name="group_id")
	private Integer groupId;

	@Temporal(TemporalType.DATE)
	private Date accepted;

	@Temporal(TemporalType.DATE)
	private Date requested;
	
	@Column(name="roles")
	private String roles;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="group_id", referencedColumnName="id")
	private Group group;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
	private User user;

	public UserGroup() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
}