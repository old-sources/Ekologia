package coop.ekologia.service.cms;

import javax.ejb.Local;

import coop.ekologia.DTO.cms.PageDTO;

@Local
public interface PageServiceInterface {

	PageDTO getPageFromUrl(String url);

}
