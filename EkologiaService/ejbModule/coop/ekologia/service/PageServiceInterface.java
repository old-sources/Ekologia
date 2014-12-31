package coop.ekologia.service;

import javax.ejb.Local;

import coop.ekologia.DTO.PageDTO;

@Local
public interface PageServiceInterface {

	PageDTO getPageFromUrl(String url);

}
