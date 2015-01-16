package coop.ekologia.service.utils;

import javax.ejb.Local;

@Local
public interface ConstraintsServiceInterface {
	boolean isEmpty(String value);

	boolean isNotEmpty(String value);

	boolean isInteger(String value);

	boolean isNotInteger(String value);

	boolean isDouble(String value);

	boolean isNotDouble(String value);

	boolean isDate(String value);

	boolean isNotDate(String value);

	boolean isDatetime(String value);

	boolean isNotDatetime(String value);
	
	boolean isUrl(String value);
	
	boolean isNotUrl(String value);
	
	boolean isEmail(String value);
	
	boolean isNotEmail(String value);
	
	boolean isSecuredPassword(String value);
	
	boolean isNotSecuredPassword(String value);
}