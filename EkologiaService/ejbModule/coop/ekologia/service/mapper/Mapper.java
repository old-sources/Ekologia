package coop.ekologia.service.mapper;

public interface Mapper<DTO, Entity> {
	DTO mapFromEntity(Entity entity);
	
	Entity mapToEntity(DTO entity);
}
