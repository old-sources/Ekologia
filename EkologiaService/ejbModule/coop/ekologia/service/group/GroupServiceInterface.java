package coop.ekologia.service.group;

import java.util.List;

import javax.ejb.Local;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.group.RoleUserGroupDTO;
import coop.ekologia.DTO.group.UserGroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.entity.group.Group;
import coop.ekologia.entity.group.UserGroup;
import coop.ekologia.entity.role.RoleUserGroup;
import coop.ekologia.entity.role.RoleUserGroupLang;

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
	 * @return a GroupDTO
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

	/**
	 * Insert a user in the group
	 * 
	 * @param userGroupDTO
	 * @return 
	 */
	public abstract void insertUserGroup(UserGroupDTO userGroupDTO);

	/**
	 * Get a list of UserGroup by a groupId
	 * 
	 * @param group
	 * @return
	 */
	public abstract List<UserGroup> getListUserGroup(Integer groupId);

	/**
	 * Update a UserGroup
	 * 
	 * @param groupDTO
	 * @param userDTO
	 * @return
	 */
	public abstract void updateUserGroup(GroupDTO groupDTO, UserDTO userDTO);
	
	/**
	 * Get the list of roles available in a Group
	 * 
	 * @param codeLangue
	 * @return
	 */
	public abstract List<RoleUserGroupDTO> getAllRoleUserGroup(String codeLangue);

}