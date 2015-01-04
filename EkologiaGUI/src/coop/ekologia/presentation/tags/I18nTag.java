package coop.ekologia.presentation.tags;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import coop.ekologia.service.utils.I18nServiceInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

public class I18nTag extends SimpleTagSupport {
	// Export to a configuration file?
	private static final String DEFAULT_LANGUAGE = "en";
	
	@EJB
	private I18nServiceInterface i18nService;
	
	private String key;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspContext jspContext = getJspContext();
		String language = getLanguage(jspContext);
		String value = i18nService.find(language, key);
		if (value == null) {
			value = i18nService.find(DEFAULT_LANGUAGE, key);
		}
		if (value != null) {
			// We display the value only when it is not null to avoid NullPointerException
			jspContext.getOut().print(value);
		}
	}

	private String getLanguage(JspContext jspContext) {
		String language;
		if (jspContext instanceof PageContext) {
			Object currentLanguageObj = ((PageContext) jspContext).getRequest().getAttribute("currentLanguage");
			if (currentLanguageObj == null) {
				language = DEFAULT_LANGUAGE;
			} else {
				language = currentLanguageObj.toString();
			}
		} else {
			language = DEFAULT_LANGUAGE;
		}
		return language;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
