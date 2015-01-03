package coop.ekologia.service.mapper.cms;

import coop.ekologia.DTO.cms.PageDTO;
import coop.ekologia.entity.cms.Page;
import coop.ekologia.service.mapper.Mapper;

public class PageMapper implements Mapper<PageDTO, Page> {
	@Override
	public PageDTO mapFromEntity(Page page) {
		PageDTO retour = new PageDTO();
		retour.setHtml(page.getHtml());
		return retour;
	}

	@Override
	public Page mapToEntity(PageDTO userPB) {
		Page retour = new Page();
		return retour;
	}

}
