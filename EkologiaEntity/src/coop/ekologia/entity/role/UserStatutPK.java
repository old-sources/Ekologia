package coop.ekologia.entity.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserStatutPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="statut_id")
	private Integer statutId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatutId() {
		return statutId;
	}

	public void setStatutId(Integer statutId) {
		this.statutId = statutId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserStatutPK)) {
			return false;
		}
		UserStatutPK castOther = (UserStatutPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.statutId.equals(castOther.statutId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.statutId.hashCode();
		
		return hash;
	}
}
