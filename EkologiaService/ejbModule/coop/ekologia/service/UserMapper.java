/**
 * 
 */
package coop.ekologia.service;

import coop.ekologia.DTO.UserPB;
import coop.ekologia.entity.User;

/**
 * @author imie
 *
 */
public class UserMapper {

	/**
	 * 
	 */
	public UserMapper() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPB mapFromEntity(User user){
		UserPB retour = new UserPB();
		retour.setNom(user.getEmail());
		retour.setPassw(user.getPassword());
		return retour;
	}
	
	public User mapToEntity(UserPB userPB){
		User retour = new User();
		retour.setEmail(userPB.getNom());
		retour.setPassword(userPB.getPassw());
		return retour;
	}

}
