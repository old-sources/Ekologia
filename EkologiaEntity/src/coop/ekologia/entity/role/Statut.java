package coop.ekologia.entity.role;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Statut
 *
 */
@Entity
@Table(name="statut")
public class Statut implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="code")
	private String code;
	
	@OneToMany(mappedBy="statut")
	private List<StatutLang> statutLangs;
	
	@OneToMany(mappedBy="statut")
	private List<UserStatut> userStatuts;
	
	public Statut() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<StatutLang> getStatutLangs() {
		return statutLangs;
	}

	public void setStatutLangs(List<StatutLang> statutLangs) {
		this.statutLangs = statutLangs;
	}

	public List<UserStatut> getUserStatuts() {
		return userStatuts;
	}

	public void setUserStatuts(List<UserStatut> userStatuts) {
		this.userStatuts = userStatuts;
	}
   
	
}
