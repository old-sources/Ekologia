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

	/**
	 * Checks if two {@link String} are equals.
	 * 
	 * <pre>{@code
	 * equals(null, "a") => false
	 * equals("a", null) => false
	 * equals(null, null) => true
	 * equals("a", "a") => true
	 * equals("a", "b") => false
	 * }</pre>
	 * 
	 * @param value1 The first {@code String}
	 * @param value2 The second {@code String}
	 * @return       @{code True} if the two {@code String} are equal
	 */
	boolean equals(String value1, String value2);
	
	/**
	 * Checks if two {@link String} are not equals.
	 * 
	 * <pre>{@code
	 * equals(null, "a") => true
	 * equals("a", null) => true
	 * equals(null, null) => false
	 * equals("a", "a") => false
	 * equals("a", "b") => true
	 * }</pre>
	 * 
	 * @param value1 The first {@code String}
	 * @param value2 The second {@code String}
	 * @return       @{code True} if the two {@code String} are not equal
	 */
	boolean notEquals(String value1, String value2);
}