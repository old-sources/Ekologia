package coop.ekologia.service.group;

import java.util.List;

import javax.ejb.Local;

import coop.ekologia.DTO.group.GroupDTO;

@Local
public interface GroupServiceInterface {
	/**
	 * Finds a group in terms of its canonical.
	 * 
	 * @param canonical The canonical of the group
	 * @return          The group with this canonical or null if any exists
	 */
	public abstract GroupDTO findGroupByCanonical(String canonical);
	
	/**
	 * Finds all groups.
	 * 
	 * @return          The Groups List
	 */
	public abstract List<GroupDTO> findGroupAll();

	/**
	 * Finds a group by its name.
	 * 
	 * @param canonical The group name
	 * @return          The group with this name or null if any exists
	 */
	public abstract GroupDTO findGroupByName(String name);
	
	/**
	 * Update a group
	 * 
	 * @param groupDTO the group to update
	 * @return
	 */
	public abstract GroupDTO updateGroup(GroupDTO groupDTO);

	/**
	 * Insert a new group
	 * 
	 * @param groupDTO the group to insert
	 * @return
	 */
	public abstract GroupDTO insertGroup(GroupDTO groupDTO);

	/**
	 * Delete a group
	 * 
	 * @param groupDTO the group to delete
	 * @return
	 */
	public abstract void deleteGroup(GroupDTO groupDTO);

	/**
	 * Finds a group by its id.
	 * 
	 * @param canonical The group id
	 * @return          The group with this id or null if any exists
	 */
	public abstract GroupDTO getGroupById(GroupDTO dto);
}