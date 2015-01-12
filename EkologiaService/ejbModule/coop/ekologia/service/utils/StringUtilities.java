package coop.ekologia.service.utils;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.apache.commons.codec.digest.Crypt;
import org.markdown4j.Markdown4jProcessor;

import coop.ekologia.service.group.wiki.WikiService;

@Stateless
public class StringUtilities implements StringUtilitiesInterface {
    private static final Logger logger = Logger.getLogger(WikiService.class.getName());
    
    
    private static final String charsForSalt = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
	@Override
	public String nvl(String... values) {
		for (String value: values) {
			if (value != null) {
				return value;
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(String value1, String value2) {
		if (value1 == null && value2 == null) {
			return true;
		} else if (value1 == null || value2 == null) {
			return false;
		} else {
			return value1.equals(value2);
		}
	}

	@Override
	public boolean notEquals(String value1, String value2) {
		return !equals(value1, value2);
	}
	
	@Override
	public String htmlFromUserText(String userText) {
	    Markdown4jProcessor markdown4jProcessor = new Markdown4jProcessor();
        // Add rules if needed.
        try {
            return markdown4jProcessor.process(userText);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An exception happened when transforming user text to HTML.", e);
            return "";
        }
	}
	
	@Override
	public boolean isEqualOne(String search, String... acceptedValues) {
	    for (String acceptedValue: acceptedValues) {
	        if (equals(search, acceptedValue)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	@Override
    public String generateCryptSalt() {
	    StringBuilder saltString = new StringBuilder("$6$");
        for (int i = 1; i <= 10; i++) {
            saltString.append(charsForSalt.charAt(new Random().nextInt(charsForSalt.length())));
        }
        return saltString.toString();
    }

    @Override
	public String crypt(String value, String salt) {
	    return Crypt.crypt(value, salt);
	}
}
