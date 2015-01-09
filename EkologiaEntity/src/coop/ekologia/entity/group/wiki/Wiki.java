package coop.ekologia.entity.group.wiki;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import coop.ekologia.entity.group.Group;


@Entity
@NamedQueries({
        @NamedQuery(name = Wiki.FIND_ALL, query = "SELECT w FROM Wiki w"),
        @NamedQuery(name = Wiki.FIND, query = "SELECT w FROM Wiki w WHERE w.id=:id"),
        @NamedQuery(name = Wiki.FIND_BY_CANONICAL, query = "SELECT w FROM Wiki w WHERE w.canonical=:canonical AND w.language = :language")
})
public class Wiki implements Serializable {
	private static final long serialVersionUID = -7112732908534991909L;
	
	public static final String FIND_ALL = "findAll";
    public static final String FIND = "find";
    public static final String FIND_BY_CANONICAL = "findByCanonical";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 255)
    private String title;

    @Basic
    @Column(name = "language", nullable = false, insertable = true, updatable = true, length = 10)
    private String language;

    @Basic
    @Column(name = "canonical", nullable = false, insertable = true, updatable = true, length = 255)
    private String canonical;

    @Basic
    @Column(name = "editable", nullable = false, insertable = true, updatable = true)
    private Boolean editable;

    @Basic
    @Column(name = "visible", nullable = false, insertable = true, updatable = true)
    private Boolean visible;

    @OneToMany(mappedBy = "wikiByWikiId")
    private Collection<Wikicomment> wikicommentsById;

    @OneToMany(mappedBy = "wikiByWikiId")
    private Collection<Wikiversion> wikiversionsById;
    
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;
    
    @OneToMany(mappedBy = "parent")
    private Collection<Wiki> children;
    
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Wiki parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCanonical() {
        return canonical;
    }

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    public Boolean getEditable() {
        return editable;
    }

    public boolean isEditable() {
        return Boolean.TRUE.equals(getEditable());
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getVisible() {
        return visible;
    }

    public boolean isVisible() {
        return Boolean.TRUE.equals(getVisible());
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Collection<Wikicomment> getWikicommentsById() {
        return wikicommentsById;
    }

    public void setWikicommentsById(Collection<Wikicomment> wikicommentsById) {
        this.wikicommentsById = wikicommentsById;
    }

    public Collection<Wikiversion> getWikiversionsById() {
        return wikiversionsById;
    }

    public void setWikiversionsById(Collection<Wikiversion> wikiversionsById) {
        this.wikiversionsById = wikiversionsById;
    }

    public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Collection<Wiki> getChildren() {
		return children;
	}

	public void setChildren(Collection<Wiki> children) {
		this.children = children;
	}

	public Wiki getParent() {
		return parent;
	}

	public void setParent(Wiki parent) {
		this.parent = parent;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wiki wiki = (Wiki) o;

        if (editable != wiki.editable) return false;
        if (id != wiki.id) return false;
        if (visible != wiki.visible) return false;
        if (canonical != null ? !canonical.equals(wiki.canonical) : wiki.canonical != null) return false;
        if (language != null ? !language.equals(wiki.language) : wiki.language != null) return false;
        if (title != null ? !title.equals(wiki.title) : wiki.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (canonical != null ? canonical.hashCode() : 0);
        result = 31 * result + (editable ? 1 : 0);
        result = 31 * result + (visible ? 1 : 0);
        return result;
    }
}
