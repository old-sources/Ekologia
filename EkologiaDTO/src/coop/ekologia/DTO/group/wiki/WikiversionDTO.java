package coop.ekologia.DTO.group.wiki;

import java.io.Serializable;
import java.util.Date;

import coop.ekologia.DTO.user.UserDTO;

public class WikiversionDTO implements Serializable {
	private static final long serialVersionUID = 4362888819078308311L;

	private Integer id;
	private Date date;
	private String content;
	private Boolean active;
	private String image;
	private WikiDTO wiki;
	private UserDTO user;
	private String htmlContent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getActive() {
		return active;
	}

	public boolean isActive() {
		return Boolean.TRUE.equals(active);
	}

	public boolean isNotActive() {
		return !isActive();
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public WikiDTO getWiki() {
		return wiki;
	}

	public void setWiki(WikiDTO wiki) {
		this.wiki = wiki;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
