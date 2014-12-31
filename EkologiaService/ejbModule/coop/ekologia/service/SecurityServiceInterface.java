package coop.ekologia.service;

import javax.ejb.Local;

import coop.ekologia.DTO.UserDTO;

@Local
public interface SecurityServiceInterface {

	Boolean isResourceAllow(String url, UserDTO user);

}
