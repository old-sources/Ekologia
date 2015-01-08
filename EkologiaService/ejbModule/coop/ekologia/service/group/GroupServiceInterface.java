package coop.ekologia.service.group;

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
	GroupDTO findByCanonical(String canonical);
}