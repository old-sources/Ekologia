package coop.ekologia.service.mapper.cms;

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.entity.cms.Page;
import coop.ekologia.service.mapper.Mapper;

public class PageMapper extends Mapper<PageDTO, Page> {
	@Override
	public PageDTO mapFromEntity(Page page) {
		PageDTO retour = new PageDTO();
		retour.setHtml(page.getHtml());
		retour.setUrl(page.getUrl());
		retour.setId(page.getId());
		return retour;
	}

	@Override
	public Page mapToEntity(PageDTO pagePB) {
		Page retour = new Page();
		if (pagePB.getId() != null) {
			retour.setId(pagePB.getId());
		}
		retour.setUrl(pagePB.getUrl());
		retour.setHtml(pagePB.getHtml());
		return retour;
	}

}
