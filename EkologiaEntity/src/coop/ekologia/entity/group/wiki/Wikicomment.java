package coop.ekologia.entity.group.wiki;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import coop.ekologia.entity.user.User;


@Entity
public class Wikicomment implements Serializable {
	private static final long serialVersionUID = -5825691436739733354L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 255)
    private String title;

    @Basic
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 2147483647)
    private String content;

    @Basic
    @Column(name = "date", nullable = false, insertable = true, updatable = true)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "wiki_id", referencedColumnName = "id")
    private Wiki wikiByWikiId;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Wikicomment wikicommentByParentId;

    @OneToMany(mappedBy = "wikicommentByParentId")
    private Collection<Wikicomment> wikicommentsById;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public Wiki getWikiByWikiId() {
        return wikiByWikiId;
    }

    public void setWikiByWikiId(Wiki wikiByWikiId) {
        this.wikiByWikiId = wikiByWikiId;
    }

    public Wikicomment getWikicommentByParentId() {
        return wikicommentByParentId;
    }

    public void setWikicommentByParentId(Wikicomment wikicommentByParentId) {
        this.wikicommentByParentId = wikicommentByParentId;
    }

    public Collection<Wikicomment> getWikicommentsById() {
        return wikicommentsById;
    }

    public void setWikicommentsById(Collection<Wikicomment> wikicommentsById) {
        this.wikicommentsById = wikicommentsById;
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

        Wikicomment that = (Wikicomment) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}