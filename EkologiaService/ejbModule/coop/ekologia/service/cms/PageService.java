/**
 * 
 */
package coop.ekologia.service.cms;

import java.util.ArrayList;
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

	@Override
	public List<PageDTO> getAllPage() {
		List<Page> pages = entityManager.createNamedQuery("Page.findAll")
				.getResultList();
		List<PageDTO> pagesPB = new ArrayList<PageDTO>();
		for (Page page : pages) {
			pagesPB.add(pageMapper.mapFromEntity(page));
		}

		return pagesPB;
	}

	@Override
	public PageDTO getPageById(PageDTO dto) {
		Page page = entityManager.find(Page.class, dto.getId());
		return pageMapper.mapFromEntity(page);
	}

	@Override
	public PageDTO updatePage(PageDTO dto) {
		Page page= pageMapper.mapToEntity(dto);
		page = entityManager.merge(page);
		return pageMapper.mapFromEntity(page);
	}

	@Override
	public PageDTO insertPage(PageDTO dto) {
		Page page= pageMapper.mapToEntity(dto);
		entityManager.persist(page);
		return pageMapper.mapFromEntity(page);
	}

	@Override
	public void deletePage(PageDTO dto) {
		Page page= pageMapper.mapToEntity(dto);
		page = entityManager.merge(page);
		entityManager.remove(page);
		
	}

}
