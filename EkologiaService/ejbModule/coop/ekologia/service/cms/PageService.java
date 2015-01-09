/**
 * 
 */
package coop.ekologia.service.cms;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.entity.cms.Page;
import coop.ekologia.service.mapper.cms.PageMapper;

/**
 * @author imie
 *
 */
// @Named("instance")
@Stateless
public class PageService implements PageServiceInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	PageMapper pageMapper;

	/**
	 * 
	 */
	public PageService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageDTO getPageFromUrl(String url) {
		List<Page> pages = entityManager.createQuery(
				String.format("select p from Page p where p.url='%s'", url))
				.getResultList();
		PageDTO retour = null;
		if (!pages.isEmpty()) {
			retour = pageMapper.mapFromEntity(pages.get(0));
		}
		return retour;
	}

}
