package coop.ekologia.service.group.wiki;

import javax.ejb.Local;

import coop.ekologia.DTO.group.GroupDTO;
import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.DTO.wiki.WikiDTO;

@Local
public interface WikiServiceInterface {
	/**
	 * Returns a Wiki in terms of its canonical and its language
	 * 
	 * @param language  The wiki language
	 * @param canonical The wiki canonical
	 * @return          The wiki with these language and canonical or {@code null} if it does not exists.
	 */
	WikiDTO findByCanonical(String language, String canonical);

	/**
	 * Checks if a wiki with these language and canonical exists.
	 * 
	 * @param language  The wiki language
	 * @param canonical The wiki canonical
	 * @return          {@code true} if the wiki exists, otherwise {@code false}
	 */
	boolean exists(String language, String canonical);

	/**
	 * Checks if the user can read the given wiki
	 * 
	 * @param user The user requiring the access
	 * @param wiki The wiki to check
	 * @return     {@code true} if the user can read the wiki, otherwise {@code false}
	 */
	boolean canRead(UserDTO user, WikiDTO wiki);
	
	/**
	 * Checks if the user can read a wiki into of the given group.
	 * Unlike {@link #canRead(UserDTO, WikiDTO)}, this method checks for the global access of the group wiki,
	 * not for a particular wiki.
	 * 
	 * @param userDTO  The user accessing the  wiki
	 * @param groupDTO The group where the wiki is
	 * @return         {@code true} if the user can read the wiki, otherwise {@code false}
	 */
	boolean canRead(UserDTO userDTO, GroupDTO groupDTO);

	/**
	 * Checks if the user can modify the given wiki
	 * 
	 * @param user The user requiring the access
	 * @param wiki The wiki to check
	 * @return     {@code true} if the user can modify the wiki, otherwise {@code false}
	 */
	boolean canWrite(UserDTO user, WikiDTO wiki);

	/**
	 * Checks if the user can modify a wiki into the given group.
	 * Unlike {@link #canWrite(UserDTO, WikiDTO)}, this method checks for the global modification of the group wiki,
	 * not for a particular wiki.
	 * 
	 * @param userDTO  The user requiring the access
	 * @param groupDTO The group where the wiki is
	 * @return     {@code true} if the user can modify the wiki, otherwise {@code false}
	 */
	boolean canWrite(UserDTO userDTO, GroupDTO groupDTO);

	/**
	 * Create a new wiki
	 * 
	 * @param wikiDTO The wiki to create
	 * @return        The created wikiDTO (with generated fields)
	 */
	WikiDTO create(WikiDTO wikiDTO);

	/**
	 * Update an existing wiki
	 * 
	 * @param wikiDTO The wiki to update
	 * @return        The updated wikiDTO (with generated fields)
	 */
	WikiDTO update(WikiDTO wikiDTO);

	/**
	 * Delete an existing wiki
	 * 
	 * @param wikiDTO The wiki to delete
	 */
	void delete(WikiDTO wikiDTO);

}