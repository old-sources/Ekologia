package i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class I18nService {
    private static final Logger logger = Logger.getLogger(I18nService.class.getName());

    // TODO: how to get language from current request?

    public String translate(String language, String key) {
        if (language == null || key == null) {
            logger.log(Level.WARNING, "The language or the key is null. Operation aborted.");
            return null;
        }
        String module = key.substring(0, key.indexOf('.'));
        String propertyKey = key.substring(key.indexOf('.') + 1);
        Properties properties = new Properties();
        try {
            properties.load(getInputStream(module, language));
            String value = properties.getProperty(key);
            if (value == null || "".equals(value)) {
                properties.load(getInputStream(module, null));
                return properties.getProperty(propertyKey);
            } else {
                return value;
            }
        } catch (IOException e) {
            logger.log(Level.WARNING,
                       "The file for this language does not exists or cannot be accessed. Operation aborted.");
            return null;
        }
    }

    private InputStream getInputStream(String module, String language) {
        String path;
        if (language != null) {
            path = "i18n/" + module + "_" + language + ".properties";
        } else {
            path = "i18n/" + module + ".properties";
        }
        return this.getClass().getClassLoader().getResourceAsStream(path);
    }
}
