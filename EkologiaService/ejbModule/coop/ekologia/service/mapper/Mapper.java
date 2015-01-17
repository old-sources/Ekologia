package coop.ekologia.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.user.User;

public abstract class Mapper<DTO, Entity> {
	public abstract DTO mapFromEntity(Entity entity);
	
	public abstract Entity mapToEntity(DTO entity);
	
	public Collection<DTO> mapFromEntity(Collection<Entity> entities) {
		Collection<DTO> result = new ArrayList<DTO>();
		for (Entity entity: entities) {
			result.add(mapFromEntity(entity));
		}
		return result;
	}
	
	public Collection<Entity> mapToEntity(Collection<DTO> entities) {
		Collection<Entity> result = new ArrayList<Entity>();
		for (DTO entity: entities) {
			result.add(mapToEntity(entity));
		}
		return result;
	}

	public User mapToEntity(UserDTO userDTO, User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
