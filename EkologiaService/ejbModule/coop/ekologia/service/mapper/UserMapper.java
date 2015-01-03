package coop.ekologia.service.mapper;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.user.User;

public class UserMapper implements Mapper<UserDTO, User> {
	@Override
	public UserDTO mapFromEntity(User user){
		UserDTO retour = new UserDTO();
		//retour.setPrenom(user.getFirstName());
		//retour.setNom(user.getLastName());
		retour.setPassw(user.getPassword());
		return retour;
	}

	@Override
	public User mapToEntity(UserDTO userDTO){
		User retour = new User();
		retour.setEmail(userDTO.geteMail());
		//retour.setFirstName(userDTO.getPrenom());
		//retour.setLastName(userDTO.getNom());
		retour.setPassword(userDTO.getPassw());
		return retour;
	}

}
