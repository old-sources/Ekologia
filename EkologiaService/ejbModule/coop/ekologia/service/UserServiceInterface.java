/**
 * 
 */
package coop.ekologia.service;

import java.util.List;

import javax.ejb.Local;

import coop.ekologia.DTO.UserDTO;

/**
 * @author imie
 *
 */
@Local
public interface UserServiceInterface {

	public List<UserDTO> getAllUser();

	UserDTO getSecuredUser(UserDTO userPB);

	public UserDTO getUserById(UserDTO dto);

	public UserDTO updateUser(UserDTO dto);

	public UserDTO insertUser(UserDTO dto);

}
