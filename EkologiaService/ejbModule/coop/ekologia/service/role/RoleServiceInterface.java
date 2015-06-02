package coop.ekologia.service.role;

import coop.ekologia.DTO.role.RoleUserDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RoleServiceInterface {
    List<RoleUserDTO> findAll(String lang);
}
