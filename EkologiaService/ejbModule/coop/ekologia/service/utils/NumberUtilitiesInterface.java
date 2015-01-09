package coop.ekologia.service.utils;

import javax.ejb.Local;

@Local
public interface NumberUtilitiesInterface {
	/**
	 * Checks if two {@link Integer} are equals.
	 * 
	 * <pre>{@code
	 * equals(null, 1) => false
	 * equals(1, null) => false
	 * equals(null, null) => true
	 * equals(1, 1) => true
	 * equals(1, 2) => false
	 * }</pre>
	 * 
	 * @param int1 The first {@code Integer}
	 * @param int2 The second {@code Integer}
	 * @return     True if the two {@code Integer} are equal
	 */
	boolean equals(Integer int1, Integer int2);
}