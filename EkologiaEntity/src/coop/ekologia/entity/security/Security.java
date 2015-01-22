package coop.ekologia.entity.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "security" database table.
 * 
 */
@Entity
@Table(name="security")
@NamedQuery(name=Security.FIND_ALL, query="SELECT s FROM Security s")
public class Security implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL= "Security.findAll";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="url")
	private String url;
	
	@Column(name="diseable")
	private Boolean diseable;

	public Security() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getDiseable() {
		return diseable;
	}

	public void setDiseable(Boolean diseable) {
		this.diseable = diseable;
	}

	
	
}