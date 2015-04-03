package coop.ekologia.DTO.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coop.ekologia.DTO.user.UserDTO;

public class GroupDTO implements Serializable {
	private static final long serialVersionUID = -8009502614341289051L;

	private Integer id;

	private String canonical;

	private String name;
	
	private String description;
	
	private String icon;
	
	private Collection<UserGroupDTO> userGroups;

	public Collection<UserGroupDTO> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Collection<UserGroupDTO> userGroups) {
		this.userGroups = userGroups;
	}

	public GroupDTO(){
		userGroups = new ArrayList<UserGroupDTO>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCanonical() {
		return canonical;
	}

	public void setCanonical(String canonical) {
		this.canonical = canonical;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
