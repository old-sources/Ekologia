package coop.ekologia.DTO.group.wiki;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coop.ekologia.DTO.group.GroupDTO;

public class WikiDTO implements Serializable {
	private static final long serialVersionUID = -8285532460016938915L;
	
	private Integer id;
	private String title;
	private String language;
	private String canonical;
	private Boolean editable;
	private Boolean visible;

	private Collection<WikiversionDTO> versions;
	private WikiversionDTO currentVersion;
	private Collection<WikicommentDTO> comments;
	private GroupDTO group;
	private Collection<WikiDTO> children;
	private WikiDTO parent;
	
	public WikiDTO() {
		versions = new ArrayList<WikiversionDTO>();
		comments = new ArrayList<WikicommentDTO>();
		children = new ArrayList<WikiDTO>();
	}

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
		return Boolean.TRUE.equals(editable);
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getVisible() {
		return visible;
	}

	public boolean isVisible() {
		return Boolean.TRUE.equals(visible);
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Collection<WikiversionDTO> getVersions() {
		return versions;
	}

	public void setVersions(Collection<WikiversionDTO> versions) {
		this.versions = versions;
	}

	public WikiversionDTO getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(WikiversionDTO currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Collection<WikicommentDTO> getComments() {
		return comments;
	}

	public void setComments(Collection<WikicommentDTO> comments) {
		this.comments = comments;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public Collection<WikiDTO> getChildren() {
		return children;
	}

	public void setChildren(Collection<WikiDTO> children) {
		this.children = children;
	}

	public WikiDTO getParent() {
		return parent;
	}

	public void setParent(WikiDTO parent) {
		this.parent = parent;
	}
	
	public List<WikiDTO> getParents(boolean topToDown) {
	    List<WikiDTO> result = new ArrayList<WikiDTO>();
	    for (WikiDTO parent = getParent() ; parent != null ; parent = parent.getParent()) {
	        result.add(parent);
	    }
	    if (topToDown) {
	        List<WikiDTO> orderedResult = new ArrayList<WikiDTO>();
	        for (int i = result.size() - 1 ; i >= 0 ; i--) {
	            orderedResult.add(result.get(i));
	        }
	        result = orderedResult;
	    }
	    return result;
	}
	
	public List<WikiDTO> getParents() {
	    return getParents(false);
	}
}
