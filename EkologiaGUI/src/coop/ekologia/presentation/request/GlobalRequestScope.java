package coop.ekologia.presentation.request;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class GlobalRequestScope {
	List<String> ALLOWED_LANGUAGES = Arrays.asList("fr", "en");

	@Inject
	HttpServletRequest request;

	private String language;
	
	private String internalUrl;

	public GlobalRequestScope() {
	}
	

	/**
	 * Returns the language from the request url.
	 * 
	 * {@code ekologia.coop/<ctx>/fr} should give {@code fr}
	 * 
	 * @return The language
	 */
	public String getLanguage() {
		if (language == null) {
			language = extractLanguageFromURL();
			request.setAttribute("language", language);
		}
		return language;
	}
	
	/**
	 * Returns the request URL without context path nor language.
	 * 
	 * @return The internal URL
	 */
	public String getInternalUrl() {
		if (internalUrl == null) {
			String ctxPath = request.getContextPath();
			String lang = getLanguage();
			int startIndex = 0;
			if (lang == null) {
				startIndex = ctxPath.length() + 1;
			} else {
				startIndex = ctxPath.length() + lang.length() + 1;
			}
			internalUrl = request.getRequestURI().substring(startIndex);
		}
		return internalUrl;
	}
	
	/**
	 * Checks if the request is contained into a module defined by its prefix path.
	 * 
	 * @param modulePath The module path.
	 * @return {@code true} if the request is contained in module, otherwise {@code false}.
	 */
	public boolean isModule(String modulePath) {
	    StringBuilder path = new StringBuilder("\\w*\\")
	        .append(request.getContextPath())
	        .append("\\/")
	        .append(getLanguage());
	    if (modulePath.charAt(0) != '/') {
	        path.append('/');
	    }
	    path.append(modulePath);
	    path.append("(.+)");
	    
	    Matcher m = Pattern.compile(path.toString()).matcher(request.getRequestURI());
	    return m.find();
	}

    /**
     * Checks if the request is not contained into a module defined by its prefix path.
     * 
     * @param modulePath The module path.
     * @return {@code true} if the request is not contained in module, otherwise {@code false}.
     */
	public boolean isNotModule(String modulePath) {
	    return !isModule(modulePath);
	}
	
	public String extractModuleURI() {
	    StringBuilder path = new StringBuilder("\\w*\\")
        .append(request.getContextPath())
        .append("\\/")
        .append(getLanguage())
	    .append("(/(.+))");
    
	    Matcher m = Pattern.compile(path.toString()).matcher(request.getRequestURI());
	    if (m.find()) {
	        return m.group(1);
	    } else {
	        return null;
	    }
	}

	private String extractLanguageFromURL() {
		String languageURL = null;
		Matcher m = Pattern.compile(
				String.format("\\w*\\%s\\/([a-z]{2})\\/(\\S*)",
						request.getContextPath())).matcher(
				request.getRequestURI());
		if (m.find()) {
			String lang = m.group(1);
			if (ALLOWED_LANGUAGES.contains(lang)) {
				languageURL = lang;
			}
		}
		return languageURL;
	}
}
