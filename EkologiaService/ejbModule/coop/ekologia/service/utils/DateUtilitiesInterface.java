package coop.ekologia.service.utils;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface DateUtilitiesInterface {
	/**
	 * Checks if {@code date1} is before {@code date2}.
	 * <pre>{@code
	 * // For simplification, we show dates as string in format {@code "yyyy-mm-dd"}, but it must be {@link Date} objects.
	 * isBefore("2000-01-01", "2000-01-02") => true
	 * isBefore("2000-01-02", "2000-01-01") => false
	 * isBefore("2000-01-01", "2000-01-01") => false
	 * }</pre>
	 * 
	 * @param date1 The reference date
	 * @param date2 The date to compare
	 * @return      If {@code date1} or {@code date2} is {@code null}, returns {@code false}.
	 *              Else returns {@code true} if {@code date1} is before {@code date2}, otherwise {@code false}
	 */
	boolean isBefore(Date date1, Date date2);

	/**
	 * Checks if {@code date1} is after {@code date2}.
	 * <pre>{@code
	 * // For simplification, we show dates as string in format {@code "yyyy-mm-dd"}, but it must be {@link Date} objects.
	 * isBefore("2000-01-01", "2000-01-02") => false
	 * isBefore("2000-01-02", "2000-01-01") => true
	 * isBefore("2000-01-01", "2000-01-01") => false
	 * }</pre>
	 * 
	 * @param date1 The reference date
	 * @param date2 The date to compare
	 * @return      If {@code date1} or {@code date2} is {@code null}, returns {@code false}.
	 *              Else returns {@code true} if {@code date1} is after {@code date2}, otherwise {@code false}
	 */
	boolean isAfter(Date date1, Date date2);

	/**
	 * Checks if {@code date1} is equal to {@code date2}.
	 * <pre>{@code
	 * // For simplification, we show dates as string in format {@code "yyyy-mm-dd"}, but it must be {@link Date} objects.
	 * isBefore("2000-01-01", "2000-01-02") => false
	 * isBefore("2000-01-02", "2000-01-01") => false
	 * isBefore("2000-01-01", "2000-01-01") => true
	 * }</pre>
	 * 
	 * @param date1 The reference date
	 * @param date2 The date to compare
	 * @return      If {@code date1} or {@code date2} is {@code null}, returns {@code false}.
	 *              Else returns {@code true} if {@code date1} is equal to {@code date2}, otherwise {@code false}
	 */
	boolean isEqual(Date date1, Date date2);

	/**
	 * Checks if {@code date1} is before or equal to {@code date2}.
	 * <pre>{@code
	 * // For simplification, we show dates as string in format {@code "yyyy-mm-dd"}, but it must be {@link Date} objects.
	 * isBefore("2000-01-01", "2000-01-02") => true
	 * isBefore("2000-01-02", "2000-01-01") => false
	 * isBefore("2000-01-01", "2000-01-01") => true
	 * }</pre>
	 * 
	 * @param date1 The reference date
	 * @param date2 The date to compare
	 * @return      If {@code date1} or {@code date2} is {@code null}, returns {@code false}.
	 *              Else returns {@code true} if {@code date1} is before or equal to {@code date2}, otherwise {@code false}
	 */
	boolean isBeforeOrEqual(Date date1, Date date2);

	/**
	 * Checks if {@code date1} is after or equal to {@code date2}.
	 * <pre>{@code
	 * // For simplification, we show dates as string in format {@code "yyyy-mm-dd"}, but it must be {@link Date} objects.
	 * isBefore("2000-01-01", "2000-01-02") => false
	 * isBefore("2000-01-02", "2000-01-01") => true
	 * isBefore("2000-01-01", "2000-01-01") => true
	 * }</pre>
	 * 
	 * @param date1 The reference date
	 * @param date2 The date to compare
	 * @return      If {@code date1} or {@code date2} is {@code null}, returns {@code false}.
	 *              Else returns {@code true} if {@code date1} is after or equal to {@code date2}, otherwise {@code false}.
	 */
	boolean isAfterOrEqual(Date date1, Date date2);

	/**
	 * Transforms a {@link String} into a {@link Date}.
	 * The returned date is on a pretty format.
	 * 
	 * @param str The string to transform
	 * @return    The corresponding date or null if the string is invalid
	 */
    Date stringToDate(String str);

    /**
     * Transforms a {@link String} into a {@link Date}.
     * 
     * @param str       The string to transform
     * @param fromInput If true, then the given string must be on format {@code yyyy-MM-dd}, otherwise on a pretty format
     * @return          The corresponding date or null if the string is invalid
     */
    Date stringToDate(String str, boolean fromInput);

    /**
     * Transforms a {@link Date} into a {@link String}.
     * The returned string is on a pretty format.
     * 
     * @param str The string to transform
     * @return    The corresponding string
     */
    String dateToString(Date date);
    
    /**
     * Transforms a {@link Date} into a {@link String}.
     * 
     * @param str       The string to transform
     * @param fromInput If true, then the resulting string will be on format {@code yyyy-MM-dd}, otherwise on a pretty format
     * @return          The corresponding string
     */
    String dateToString(Date date, boolean forInput);
}