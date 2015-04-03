package coop.ekologia.DTO.group;

import java.io.Serializable;
import java.util.ArrayList;

import coop.ekologia.DTO.user.UserDTO;

public class RoleUserGroupDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422444488310069204L;

	private Integer id;
	
	private String description;
	
	private String codeRole;

	public RoleUserGroupDTO(){
		
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

	public String getCodeRole() {
		return codeRole;
	}

	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	
	
}
