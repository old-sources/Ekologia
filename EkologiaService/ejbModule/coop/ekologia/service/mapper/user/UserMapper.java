package coop.ekologia.service.mapper.user;

import java.util.ArrayList;

import javax.inject.Inject;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.DTO.user.UserIndividualDTO;
import coop.ekologia.DTO.user.UserOrganizationDTO;
import coop.ekologia.entity.user.User;
import coop.ekologia.entity.user.UserIndividual;
import coop.ekologia.entity.user.UserOrganization;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.utils.ListUtilitiesInterface;

public class UserMapper extends Mapper<UserDTO, User> {
	@Inject
	private ListUtilitiesInterface listUtilities;
	
	@Override
	public UserDTO mapFromEntity(User user){
	    if (user == null) {
            return null;
        }
		UserDTO retour;
		if (user instanceof UserIndividual) {
		    UserIndividual useri = (UserIndividual)user;
		    UserIndividualDTO useriDTO = new UserIndividualDTO();
		    useriDTO.setFirstname(useri.getFirstname());
		    useriDTO.setLastname(useri.getLastname());
		    useriDTO.setBirthday(useri.getBirthday());
		    retour  = useriDTO;
		} else {
		    UserOrganization usero = (UserOrganization)user;
		    UserOrganizationDTO useroDTO = new UserOrganizationDTO();
		    useroDTO.setName(usero.getName());
		    useroDTO.setActivity(usero.getActivity());
		    useroDTO.setType(usero.getType());
		    retour = useroDTO;
		}
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
		if (user.getRoles() == null) {
		    retour.setRoles(new ArrayList<String>());
		} else {
		    retour.setRoles(listUtilities.split(user.getRoles(), ","));
		}
		return retour;
	}

	@Override
	public User mapToEntity(UserDTO userDTO){
        if (userDTO == null) {
            return null;
        }
		User retour;
		if (userDTO instanceof UserIndividualDTO) {
            UserIndividualDTO useriDTO = (UserIndividualDTO)userDTO;
            UserIndividual useri = new UserIndividual();
            useri.setFirstname(useriDTO.getFirstname());
            useri.setLastname(useriDTO.getLastname());
            useri.setBirthday(useriDTO.getBirthday());
            retour  = useri;
        } else {
            UserOrganizationDTO useroDTO = (UserOrganizationDTO)userDTO;
            UserOrganization usero = new UserOrganization();
            usero.setName(useroDTO.getName());
            usero.setActivity(useroDTO.getActivity());
            usero.setType(useroDTO.getType());
            retour = usero;
        }
		
		retour.setId(userDTO.getId());
		if (userDTO.getEmail() != null) {
			retour.setEmail(userDTO.getEmail());
		}
		if (userDTO.getPassword() != null) {
			retour.setPassword(userDTO.getPassword());
		}
		retour.setPhoneNumber(userDTO.getPhoneNumber());
		retour.setAddressStreet(userDTO.getAddressStreet());
		retour.setAddressZipcode(userDTO.getAddressZipcode());
		retour.setAddressCity(userDTO.getAddressCity());
		retour.setCountry(userDTO.getCountry());
		retour.setAvatar(userDTO.getAvatar());
		retour.setDescription(userDTO.getDescription());
		if (userDTO.getRoles() == null) {
		    retour.setRoles("");
		} else {
		    retour.setRoles(listUtilities.mkString(userDTO.getRoles(), ","));
		}
		return retour;
	}

}
