/**
 * 
 */
package coop.ekologia.service;

import coop.ekologia.DTO.UserDTO;
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
	
	public UserDTO mapFromEntity(User user){
		UserDTO retour = new UserDTO();
		retour.setPrenom(user.getFirstName());
		retour.setNom(user.getLastName());
		retour.setPassw(user.getPassword());
		return retour;
	}
	
	public User mapToEntity(UserDTO userDTO){
		User retour = new User();
		retour.setEmail(userDTO.geteMail());
		retour.setFirstName(userDTO.getPrenom());
		retour.setLastName(userDTO.getNom());
		retour.setPassword(userDTO.getPassw());
		return retour;
	}

}
