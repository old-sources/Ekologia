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
	
	public void addError(String key, String error) {
		if (language == null) {
			throw new FormErrorsLanguageException();
		}
		
		if (!errors.containsKey(error)) {
			errors.put(key, new ArrayList<String>());
		}
		String i18nError = i18nService.translate(language, key);
		errors.get(key).add(i18nError);
	}
	
	public Map<String, List<String>> getErrors() {
		return errors;
	}
	
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
