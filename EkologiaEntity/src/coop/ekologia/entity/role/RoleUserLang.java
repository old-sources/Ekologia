package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RoleUserLang
 *
 */
@Entity
@Table(name="role_user_lang")
public class RoleUserLang implements Serializable {

	
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
	@JoinColumn(name="role_user_id")
	private RoleUser roleUser;
	
	public RoleUserLang() {
		super();
	}
   
}
