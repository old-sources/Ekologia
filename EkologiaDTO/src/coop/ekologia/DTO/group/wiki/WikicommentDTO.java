package coop.ekologia.DTO.group.wiki;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import coop.ekologia.DTO.user.UserDTO;

public class WikicommentDTO implements Serializable {
	private static final long serialVersionUID = 8670773372421233753L;

	private int id;
	private String title;
	private String content;
	private Date date;
	private WikiDTO wiki;
	private WikicommentDTO parent;
	private Collection<WikicommentDTO> children;
	private UserDTO user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public WikiDTO getWiki() {
		return wiki;
	}

	public void setWiki(WikiDTO wiki) {
		this.wiki = wiki;
	}

	public WikicommentDTO getParent() {
		return parent;
	}

	public void setParent(WikicommentDTO parent) {
		this.parent = parent;
	}

	public Collection<WikicommentDTO> getChildren() {
		return children;
	}

	public void setChildren(Collection<WikicommentDTO> children) {
		this.children = children;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
