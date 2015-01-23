/**
 * 
 */
package coop.ekologia.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.DTO.user.UserOrganizationDTO;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.user.User;
import coop.ekologia.entity.user.UserIndividual;
import coop.ekologia.entity.user.UserOrganization;
import coop.ekologia.service.mapper.user.UserMapper;
import coop.ekologia.service.utils.StringUtilitiesInterface;

/**
 * @author imie
 *
 */
// @Named("instance")
@Stateless
public class UserService implements UserServiceInterface {
	private static final String salt = "$1$Ekologia";

	private static final Logger logger = Logger.getLogger(UserService.class
			.getName());

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private UserMapper userMapper;

	@EJB
	private StringUtilitiesInterface stringUtilities;

	public UserService() {
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
		Query query = entityManager.createNamedQuery(User.FIND_BY_EMAIL);
		query.setParameter("email", userDTO.getEmail());
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			User user = (User) query.getResultList().get(0);
			String cryptedPassword = stringUtilities.crypt(
					userDTO.getPassword(), salt);
			if (stringUtilities.equals(user.getPassword(), cryptedPassword)) {
				return userMapper.mapFromEntity(user);
			} else {
				return null;
			}
		}
	}

	@Override
	public Boolean isIntoGroup(UserDTO userDTO, GroupDTO groupDTO) {
		if (userDTO == null || groupDTO == null) {
			logger.log(Level.INFO, "The userDTO or groupDTO is null.");
			return false;
		}
		Query query = entityManager.createNamedQuery(UserGroup.FIND);
		query.setParameter("userId", userDTO.getId());
		query.setParameter("groupId", groupDTO.getId());
		return query.getSingleResult() != null;
	}

	public UserDTO getUserById(UserDTO dto) {
		User user = entityManager.find(User.class, dto.getId());
		return userMapper.mapFromEntity(user);
	}

	@Override
	public UserDTO updateUser(UserDTO dto) {
		User user = entityManager.find(User.class, dto.getId());
		user = userMapper.mapToEntity(dto,user);
		user.setPassword(stringUtilities.crypt(user.getPassword(), salt));
		user = entityManager.merge(user);
		return userMapper.mapFromEntity(user);
	}

	@Override
	public UserDTO insertUser(UserDTO dto) {
		User user = userMapper.mapToEntity(dto);
		user.setPassword(stringUtilities.crypt(user.getPassword(), salt));
		entityManager.persist(user);
		return userMapper.mapFromEntity(user);
	}

	@Override
	public void deleteUser(UserDTO dto) {

		User user = entityManager.find(User.class,dto.getId());
		user = userMapper.mapToEntity(dto,user);
		// if (user instanceof UserOrganization) {
		// UserOrganization organization = entityManager
		// .merge((UserOrganization) user);
		// organization = entityManager.merge(organization);
		// user=(User)organization;
		// //entityManager.remove(organization);
		// } else if (user instanceof UserIndividual) {
		// UserIndividual individual = entityManager
		// .merge((UserIndividual) user);
		// individual = entityManager.merge(individual);
		// //entityManager.remove(individual);
		// user=(User)individual;
		// }else{
		// user = entityManager.merge(user);
		// //user= entityManager.find(User.class, user.getId());
		// }

		//user.setId(dto.getId());
		//user = entityManager.merge(user);
		
		List<UserGroup> listUserGroup = getListUserGroup(dto.getId());
		if (!listUserGroup.isEmpty()) {
			for (UserGroup userGroup : listUserGroup) {
				entityManager.remove(userGroup);
			}
		}
		entityManager.remove(user);
	}

	@Override
	public boolean existsByEmail(String email) {
		Query query = entityManager.createNamedQuery(User.FIND_BY_EMAIL);
		query.setParameter("email", email);
		return !query.getResultList().isEmpty();
	}
	
	@Override
	public List<UserGroup> getListUserGroup(Integer userId){
		List<UserGroup> listUserGroup = new ArrayList<UserGroup>();
		Query query = entityManager
				.createNamedQuery(UserGroup.FIND_BY_USERID);
		query.setParameter("userId", userId);
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			listUserGroup = query.getResultList();	
		}
		return listUserGroup;
		
	}
}
