package coop.ekologia.service.security;

import javax.ejb.Local;

import coop.ekologia.DTO.user.UserDTO;

@Local
public interface SecurityServiceInterface {

	Boolean isResourceAllow(String url, UserDTO user);

}
