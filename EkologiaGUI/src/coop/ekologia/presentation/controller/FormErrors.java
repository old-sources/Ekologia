package coop.ekologia.presentation.controller;

import i18n.I18nService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class FormErrors {
	private Map<String, List<String>> errors = new HashMap<String, List<String>>();
	
	@Inject
	private I18nService i18nService;
	
	// TODO: how to get this value from request?
	private String language = null;
	
	/**
	 * Add an error for the given key.
	 * The key name is free, but you should use the field name as key.
	 * A given key can have several errors.
	 * 
	 * @param key   The key for the error list
	 * @param error The error code, it will be translated with {@link I18nService}
	 */
	public void addError(String key, String error) {
		if (language == null) {
			throw new FormErrorsLanguageException();
		}
		
		if (!errors.containsKey(error)) {
			errors.put(key, new ArrayList<String>());
		}
		String i18nError = i18nService.translate(language, error);
		errors.get(key).add(i18nError);
	}
	
	/**
	 * Returns The map of errors indexed by keys.
	 */
	public Map<String, List<String>> getErrors() {
		return errors;
	}
	
	/**
	 * Checks if there is any error.
	 * @return {@code true} if there is at least one error, {@code false} otherwise
	 */
	public boolean isEmpty() {
		for (String key: errors.keySet()) {
			if (!errors.get(key).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	private static class FormErrorsLanguageException extends RuntimeException {
		private static final long serialVersionUID = -3308435223873709896L;
		
		public FormErrorsLanguageException() {
			super("The language must be set before using FormErrors.addError");
		}
	}
}
