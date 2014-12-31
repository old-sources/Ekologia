/**
 * 
 */
package coop.ekologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import coop.ekologia.DTO.UserDTO;
import coop.ekologia.entity.User;

/**
 * @author imie
 *
 */
// @Named("instance")
@Stateless
public class UserService implements UserServiceInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	UserMapper userPBMapper;

	/**
	 * 
	 */
	public UserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UserDTO> getAllUser() {

		List<User> users = entityManager.createNamedQuery("User.findAll")
				.getResultList();
		List<UserDTO> usersPB = new ArrayList<UserDTO>();
		for (User user : users) {
			usersPB.add(userPBMapper.mapFromEntity(user));
		}

		return usersPB;
	}

	@Override
	public UserDTO getSecuredUser(UserDTO userPB) {
		List<User> users = entityManager
				.createQuery(
						String.format(
								"select u from User u where u.email='%s' and u.password='%s'",
								userPB.geteMail(), userPB.getPassw()))
				.getResultList();
		UserDTO retour = null;
		if (!users.isEmpty()) {
			retour = userPBMapper.mapFromEntity(users.get(0));
		}
		return retour;
	}

}
