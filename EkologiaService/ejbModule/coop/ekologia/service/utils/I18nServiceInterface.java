package coop.ekologia.service.utils;

import javax.ejb.Local;

@Local
public interface I18nServiceInterface {
	/**
	 * Returns the translation of a key.
	 * 
	 * @param language The target language
	 * @param key      The key to use to find translation
	 * @return         The translation or null if it does not exists
	 */
	String find(String language, String key);
}