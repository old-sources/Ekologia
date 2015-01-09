package coop.ekologia.entity.group.wiki;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import coop.ekologia.entity.user.User;


@Entity
public class Wikiversion implements Serializable {
	private static final long serialVersionUID = 2304373846021004259L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Basic
    @Column(name = "date", nullable = false, insertable = true, updatable = true)
    private Date date;

    @Basic
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 2147483647)
    private String content;

    @Basic
    @Column(name = "active", nullable = false, insertable = true, updatable = true)
    private Boolean active;

    @Basic
    @Column(name = "image", nullable = false, insertable = true, updatable = true, length = 1000)
    private String image;

    @ManyToOne
    @JoinColumn(name = "wiki_id", referencedColumnName = "id", nullable = false)
    private Wiki wikiByWikiId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
        return Boolean.TRUE.equals(getActive());
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

    public Wiki getWikiByWikiId() {
        return wikiByWikiId;
    }

    public void setWikiByWikiId(Wiki wikiByWikiId) {
        this.wikiByWikiId = wikiByWikiId;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wikiversion that = (Wikiversion) o;

        if (active != that.active) return false;
        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
