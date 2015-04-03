package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: StatutLang
 *
 */
@Entity
@Table(name="statut_lang")
public class StatutLang implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="langue")
	private String langue;
	
	@ManyToOne
	@JoinColumn(name="statut_id")
	private Statut statut;
	
	public StatutLang() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
   
}
