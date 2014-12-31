package coop.ekologia.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "pages" database table.
 * 
 */
@Entity
@Table(name="\"page\"")
@NamedQuery(name="Page.findAll", query="SELECT p FROM Page p")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"css\"")
	private String css;

	@Column(name="\"html\"")
	private String html;

	@Column(name="\"javascript\"")
	private String javascript;

	@Column(name="\"url\"")
	private String url;

	
	public Page() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCss() {
		return this.css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getJavascript() {
		return this.javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	

}