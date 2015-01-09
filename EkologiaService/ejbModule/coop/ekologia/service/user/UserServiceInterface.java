/**
 * 
 */
package coop.ekologia.service.user;

import java.util.List;

import javax.ejb.Local;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;

/**
 * @author imie
 *
 */
@Local
public interface UserServiceInterface {

	public List<UserDTO> getAllUser();

	UserDTO getSecuredUser(UserDTO userPB);

	boolean isIntoGroup(UserDTO userDTO, GroupDTO groupDTO);
	public UserDTO getUserById(UserDTO dto);

	public UserDTO updateUser(UserDTO dto);

	public UserDTO insertUser(UserDTO dto);

}
