package coop.ekologia.service.utils;

import javax.ejb.Local;

@Local
public interface StringUtilitiesInterface {
	/**
	 * Returns the first value which is not null.
	 * <pre>{@code
	 * nvl(null, "a")             => "a"
	 * nvl(null, null, null, "a") => "a"
	 * nvl("a", "b", "c", "d")    => "a"
	 * nvl(null, "b", "c", "d")   => "b"
	 * }</pre>
	 * 
	 * @param values
	 * @return
	 */
	public abstract String nvl(String... values);

}