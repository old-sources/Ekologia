/**
 * 
 */
package coop.ekologia.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import coop.ekologia.DTO.user.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.user.User;
import coop.ekologia.entity.user.UserGroup;
import coop.ekologia.service.mapper.UserMapper;

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
	UserMapper userMapper;

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
			usersPB.add(userMapper.mapFromEntity(user));
		}

		return usersPB;
	}

	@Override
	public UserDTO getSecuredUser(UserDTO userDTO) {
		List<User> users = entityManager
				.createQuery(
						String.format(
								"select u from User u where u.email='%s' and u.password='%s'",
								userDTO.getEmail(), userDTO.getPassword()))
				.getResultList();
		UserDTO retour = null;
		if (!users.isEmpty()) {
			retour = userMapper.mapFromEntity(users.get(0));
		}
		return retour;
	}

	public boolean isIntoGroup(UserDTO userDTO, GroupDTO groupDTO) {
		Query query = entityManager.createNamedQuery(UserGroup.FIND);
        query.setParameter("userId", userDTO.getId());
        query.setParameter("groupId", groupDTO.getId());
        return query.getSingleResult() != null;
	}
}
