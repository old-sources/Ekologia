package coop.ekologia.service.mapper.role;

import coop.ekologia.DTO.role.RoleUserDTO;
import coop.ekologia.entity.role.RoleUser;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.utils.ListUtilitiesInterface;

import javax.ejb.EJB;

public class RoleUserMapper extends Mapper<RoleUserDTO, RoleUser> {
    @EJB
    ListUtilitiesInterface listUtilities;

    @Override
    public RoleUserDTO mapFromEntity(RoleUser roleUser) {
        RoleUserDTO roleUserDTO = new RoleUserDTO();
        roleUserDTO.setId(roleUser.getId());
        roleUserDTO.setCode(roleUser.getCode());
        if (listUtilities.isNotEmpty(roleUser.getRoleUserLangs())) {
            roleUserDTO.setDescription(roleUser.getRoleUserLangs().get(0).getDescription());
        }
        return roleUserDTO;
    }

    @Override
    public RoleUser mapToEntity(RoleUserDTO entity) {
        RoleUser roleUser = new RoleUser();
        roleUser.setId(entity.getId());
        roleUser.setCode(entity.getCode());
        // TODO: languages?
        return roleUser;
    }
}
