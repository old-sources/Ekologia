package coop.ekologia.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "security" database table.
 * 
 */
@Entity
@Table(name="\"security\"")
@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s")
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"url\"")
	private String url;
	
	@Column(name="\"diseable\"")
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