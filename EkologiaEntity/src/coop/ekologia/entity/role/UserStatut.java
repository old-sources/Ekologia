package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.*;

import coop.ekologia.entity.user.User;

/**
 * Entity implementation class for Entity: UserStatut
 *
 */
@Entity
@Table(name="user_statut")
public class UserStatut implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected UserStatutPK UserStatutPK;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="statut_id", referencedColumnName="id", insertable = false, updatable = false)
	private Statut statut;
	
	public UserStatut() {
		super();
	}

	public UserStatutPK getUserStatutPK() {
		return UserStatutPK;
	}

	public void setUserStatutPK(UserStatutPK userStatutPK) {
		UserStatutPK = userStatutPK;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
   
	
}
