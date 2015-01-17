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
		} else if (user instanceof UserOrganization) {
		    UserOrganization usero = (UserOrganization)user;
		    UserOrganizationDTO useroDTO = new UserOrganizationDTO();
		    useroDTO.setName(usero.getName());
		    useroDTO.setActivity(usero.getActivity());
		    useroDTO.setType(usero.getType());
		    retour = useroDTO;
		}else{
			retour = new UserDTO();
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
		User user;
		if (userDTO instanceof UserIndividualDTO) {
            UserIndividual useri = new UserIndividual();
            user  = useri;
        } else if(userDTO instanceof UserOrganizationDTO) {
            UserOrganization usero = new UserOrganization();
            user = usero;
        }else{
        	user = new User();
        }
		
		mapToEntity(userDTO, user);
		
		return user;
	}
	
	@Override
	public User mapToEntity(UserDTO userDTO,User user){
		if (userDTO == null) {
            return null;
        }
		if (userDTO instanceof UserIndividualDTO) {
            UserIndividualDTO useriDTO = (UserIndividualDTO)userDTO;
            UserIndividual useri = (UserIndividual)user;
            useri.setFirstname(useriDTO.getFirstname());
            useri.setLastname(useriDTO.getLastname());
            useri.setBirthday(useriDTO.getBirthday());
        } else if(userDTO instanceof UserOrganizationDTO) {
            UserOrganizationDTO useroDTO = (UserOrganizationDTO)userDTO;
            UserOrganization usero = (UserOrganization)user;
            usero.setName(useroDTO.getName());
            usero.setActivity(useroDTO.getActivity());
            usero.setType(useroDTO.getType());
        }
		
		user.setId(userDTO.getId());
		if (userDTO.getEmail() != null) {
			user.setEmail(userDTO.getEmail());
		}
		if (userDTO.getPassword() != null) {
			user.setPassword(userDTO.getPassword());
		}
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setAddressStreet(userDTO.getAddressStreet());
		user.setAddressZipcode(userDTO.getAddressZipcode());
		user.setAddressCity(userDTO.getAddressCity());
		user.setCountry(userDTO.getCountry());
		user.setAvatar(userDTO.getAvatar());
		user.setDescription(userDTO.getDescription());
		if (userDTO.getRoles() == null) {
			user.setRoles("");
		} else {
			user.setRoles(listUtilities.mkString(userDTO.getRoles(), ","));
		}
		return user;
		
	}

}
