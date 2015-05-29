/**
 * 
 */
package coop.ekologia.service.security;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.security.Security;
import coop.ekologia.service.mapper.user.UserMapper;
import coop.ekologia.service.user.UserServiceInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	@Inject
	UserMapper userMapper;

	/**
	 * 
	 */
	public SecurityService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean isResourceAllow(String url, UserDTO user) {
		// List<Security> securities = entityManager
		// .createQuery(
		// String.format(
		// "select s from Security s where s.url='%s' and (s.diseable is null or s.diseable=false)",
		// url)).getResultList();
		// simple securtiy : a record means that this url needs authentification
		List<Security> securities = entityManager.createNamedQuery(
				Security.FIND_ALL).getResultList();
		Boolean retour = true;

		if (!securities.isEmpty()) {

			Boolean securedUrl = false;
			for (Security security : securities) {
				if (security.getUrl() == url) {
					securedUrl = true;
				} else if (security.getUrl().contains("*")) {
					String pattern = security.getUrl().replaceAll("\\*", "\\\\S*");
					pattern = pattern.replaceAll("\\/", "\\\\\\/");
					Matcher m = Pattern.compile(pattern).matcher(url);
					if (m.matches()) {
						securedUrl = true;
					}
				}
				if (securedUrl) {
					break;
				}
			}

			System.out.println("resource security");

			if (securedUrl) {
				retour = false;
				if (user != null && user.getAdmin()) {
					retour = true;
				}
			}
			// retour = pageMapper.mapFromEntity(pages.get(0));
		}
		return retour;
	}

}
