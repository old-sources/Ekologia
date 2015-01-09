package coop.ekologia.presentation.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import coop.ekologia.DTO.user.UserDTO;

@SessionScoped
public class LoginSession implements Serializable {
	private static final long serialVersionUID = -856784284887480722L;

	private UserDTO user;
	
	private String previousUrl;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getPreviousUrl() {
		return previousUrl;
	}

	public void setPreviousUrl(String previous) {
		this.previousUrl = previous;
	}
}
