package i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class I18nService {
    private static final Logger logger = Logger.getLogger(I18nService.class.getName());

    // TODO: how to get language from current request?

    /**
     * Returns the translation of the given key in the given language.
     * If the translation does not exists in the given language, returns the default translation.
     * If the key is not found in both languages, return the key as {@code ???key???}.
     * 
     * @param language The current language
     * @param key      The key of the translation, prefixed by your module name (ex: `module.my.key`)
     * @return         The translation
     */
    public String translate(String language, String key) {
        if (language == null || key == null) {
            logger.log(Level.WARNING, "The language or the key is null. Operation aborted.");
            return null;
        }
        String module = key.substring(0, key.indexOf('.'));
        String propertyKey = key.substring(key.indexOf('.') + 1);
        Properties properties = new Properties();
        try {
            InputStream stream = getInputStream(module, language);
            if (stream != null) {
                properties.load(stream);
                String value = properties.getProperty(key);
                if (value == null || "".equals(value)) {
                    stream = getInputStream(module, null);
                    if (stream != null) {
                        properties.load(stream);
                        value = properties.getProperty(propertyKey);
                        if (value == null) {
                            return "???" + key + "???";
                        }
                        return value;
                    } else {
                        return "???" + key + "???";
                    }
                } else {
                    return value;
                }
            } else {
                return "???" + key + "???";
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
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path);
        if (stream == null && language != null) {
            return getInputStream(module, null);
        }
        return stream;
    }
}
