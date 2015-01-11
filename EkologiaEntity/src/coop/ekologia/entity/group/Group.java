package coop.ekologia.entity.group;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the group database table.
 */
@Entity
@Table(name="group")
@NamedQueries({
	@NamedQuery(name=Group.FIND_ALL, query="SELECT g FROM Group g"),
	@NamedQuery(name=Group.FIND_BY_CANONICAL, query="SELECT g FROM Group g WHERE g.canonical=:canonical")
})
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Group.findAll";
	public static final String FIND_BY_CANONICAL = "Group.findByCanonical";

	@Id
	private Integer id;

	private String canonical;

	private String name;

	@OneToMany(mappedBy="group")
	private List<UserGroup> userGroups;

	public Group() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCanonical() {
		return this.canonical;
	}

	public void setCanonical(String canonical) {
		this.canonical = canonical;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserGroup> getUserGroups() {
		return this.userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public UserGroup addUserGroup(UserGroup userGroup) {
		getUserGroups().add(userGroup);
		userGroup.setGroup(this);

		return userGroup;
	}

	public UserGroup removeUserGroup(UserGroup userGroup) {
		getUserGroups().remove(userGroup);
		userGroup.setGroup(null);

		return userGroup;
	}

}