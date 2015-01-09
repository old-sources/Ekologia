package coop.ekologia.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.ejb.Stateless;

@Stateless
public class I18nService implements I18nServiceInterface {
	@Override
	public String find(String language, String key) {
		return key; // TODO: change with properties files
		// TODO: cache for translations?
		// TODO: how to split translation files and improve optimization?
		// TODO: how to access properties files?
		/*InputStream inputStream  = this.getClass().getClassLoader().getResourceAsStream("/WEB-INF/i18n/" + language + ".properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			return properties.getProperty(key);
		} catch (IOException e) {
			return null;
		}*/
	}
}
