/**
 * 
 */
package coop.ekologia.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import coop.ekologia.DTO.UserDTO;
import coop.ekologia.entity.Security;

/**
 * @author imie
 *
 */
// @Named("instance")
@Stateless
public class SecurityService implements SecurityServiceInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private UserServiceInterface userService;

	/**
	 * 
	 */
	public SecurityService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean isResourceAllow(String url, UserDTO user) {
		List<Security> securities = entityManager
				.createQuery(
						String.format(
								"select s from Security s where s.url='%s' and (s.diseable is null or s.diseable=false)",
								url)).getResultList();
		// simple securtiy : a record means that this url needs authentification
		Boolean retour = true;

		if (!securities.isEmpty()) {
			System.out.println("resource security");
			UserDTO userSecured = user==null?null:userService.getSecuredUser(user);
			if (userSecured == null) {
				retour = false;
			}
			// retour = pageMapper.mapFromEntity(pages.get(0));
		}
		return retour;
	}

}
