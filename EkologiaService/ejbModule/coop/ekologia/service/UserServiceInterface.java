/**
 * 
 */
package coop.ekologia.service;

import java.util.List;

import javax.ejb.Local;

import coop.ekologia.DTO.UserPB;

/**
 * @author imie
 *
 */
@Local
public interface UserServiceInterface {

	public List<UserPB> getAllUser();

	UserPB getSecuredUser(UserPB userPB);

}
