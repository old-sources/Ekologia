package coop.ekologia.service.utils;

import javax.ejb.Local;

@Local
public interface CanonicalizerServiceInterface {
	/**
	 * Transforms a string into another string with only following chars:
	 * {@code -, _, a-z, 0-9}.
	 * 
	 * @param str The string to transform
	 * @return    The transformed string
	 */
	public abstract String strToUrl(String str);

}