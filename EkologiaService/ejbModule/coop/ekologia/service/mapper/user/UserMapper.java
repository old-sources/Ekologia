package coop.ekologia.service.mapper.user;

import javax.inject.Inject;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.user.User;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.utils.ListUtilitiesInterface;

public class UserMapper implements Mapper<UserDTO, User> {
	@Inject
	private ListUtilitiesInterface listUtilities;
	
	@Override
	public UserDTO mapFromEntity(User user){
		UserDTO retour = new UserDTO();
		retour.setId(user.getId());
		retour.setEmail(user.getEmail());
		retour.setPassword(user.getPassword());
		retour.setPhoneNumber(user.getPhoneNumber());
		retour.setAddressStreet(user.getAddressStreet());
		retour.setAddressZipcode(user.getAddressZipcode());
		retour.setAddressCity(user.getAddressCity());
		retour.setCountry(user.getCountry());
		retour.setAvatar(user.getAvatar());
		retour.setDescription(user.getDescription());
		retour.setRoles(listUtilities.split(user.getRoles(), ","));
		return retour;
	}

	@Override
	public User mapToEntity(UserDTO userDTO){
		User retour = new User();
		retour.setId(userDTO.getId());
		retour.setEmail(userDTO.getEmail());
		retour.setPassword(userDTO.getPassword());
		retour.setPhoneNumber(userDTO.getPhoneNumber());
		retour.setAddressStreet(userDTO.getAddressStreet());
		retour.setAddressZipcode(userDTO.getAddressZipcode());
		retour.setAddressCity(userDTO.getAddressCity());
		retour.setCountry(userDTO.getCountry());
		retour.setAvatar(userDTO.getAvatar());
		retour.setDescription(userDTO.getDescription());
		retour.setRoles(listUtilities.mkString(userDTO.getRoles(), ","));
		return retour;
	}

}
