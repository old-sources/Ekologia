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
	
	private Collection<UserDTO> users;
	
	private List<UserDTO> usersAdmin;

	public GroupDTO(){
		users = new ArrayList<UserDTO>();
		usersAdmin = new ArrayList<UserDTO>();
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

	public Collection<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserDTO> users) {
		this.users = users;
	}

	public List<UserDTO> getUsersAdmin() {
		return usersAdmin;
	}

	public void setUsersAdmin(List<UserDTO> usersAdmin) {
		this.usersAdmin = usersAdmin;
	}
	
	public UserDTO getFirstAdmin(){
		if (!this.usersAdmin.isEmpty()){
			return this.usersAdmin.get(0);
		}else{
			return null;
		}
	}

}
