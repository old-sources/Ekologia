package coop.ekologia.service;

import coop.ekologia.DTO.PageDTO;
import coop.ekologia.entity.Page;

public class PageMapper {

	public PageDTO mapFromEntity(Page page) {
		PageDTO retour = new PageDTO();
		retour.setHtml(page.getHtml());
		return retour;
	}

	public Page mapToEntity(PageDTO userPB) {
		Page retour = new Page();
		return retour;
	}

}
