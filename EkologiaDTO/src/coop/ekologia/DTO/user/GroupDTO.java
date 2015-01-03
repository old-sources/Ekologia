package coop.ekologia.DTO.user;

import java.io.Serializable;

public class GroupDTO implements Serializable {
	private static final long serialVersionUID = -8009502614341289051L;

	private Integer id;

	private String canonical;

	private String name;

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
}
