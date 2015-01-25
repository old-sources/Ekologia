package coop.ekologia.entity.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the group database table.
 */
@Entity
@Table(name="\"group\"")
@NamedQueries({
	@NamedQuery(name=Group.FIND_ALL, query="SELECT g FROM Group g"),
	@NamedQuery(name=Group.FIND_BY_CANONICAL, query="SELECT g FROM Group g WHERE g.canonical=:canonical")
})
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Group.findAll";
	public static final String FIND_BY_CANONICAL = "Group.findByCanonical";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private String canonical;

	private String name;
	
	private String description;
	
	private String icon;

	@OneToMany(mappedBy="group")
	private List<UserGroup> userGroups;

	public Group() {
		this.userGroups = new ArrayList<UserGroup>();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}