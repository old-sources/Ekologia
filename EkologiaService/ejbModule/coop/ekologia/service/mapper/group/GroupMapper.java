package coop.ekologia.service.mapper.group;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.entity.group.Group;
import coop.ekologia.service.mapper.Mapper;

public class GroupMapper extends Mapper<GroupDTO, Group>{
	@Override
	public GroupDTO mapFromEntity(Group group) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getId());
		groupDTO.setCanonical(group.getCanonical());
		groupDTO.setName(group.getName());
		return groupDTO;
	}

	@Override
	public Group mapToEntity(GroupDTO groupDTO) {
		Group group = new Group();
		if (groupDTO.getId() != null) {
			group.setId(groupDTO.getId());
		}
		group.setCanonical(groupDTO.getCanonical());
		group.setName(groupDTO.getName());
		return group;
	}

	public Group mapToEntityInsert(GroupDTO groupDTO, Integer maxId) {
		Group group = new Group();
		group.setId(++maxId);
		group.setCanonical(groupDTO.getCanonical());
		group.setName(groupDTO.getName());
		return group;
	}

}
