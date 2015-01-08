package coop.ekologia.service.utils;

import java.util.Date;

import javax.ejb.Stateless;

@Stateless
public class DateUtilities implements DateUtilitiesInterface {
	@Override
	public boolean isBefore(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		} else {
			return date1.compareTo(date2) < 0;
		}
	}
	
	@Override
	public boolean isAfter(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return date1.compareTo(date2) > 0;
	}
	
	@Override
	public boolean isEqual(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return date1.compareTo(date2) == 0;
	}
	
	@Override
	public boolean isBeforeOrEqual(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return date1.compareTo(date2) <= 0;
	}
	
	@Override
	public boolean isAfterOrEqual(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return date1.compareTo(date2) >= 0;
	}
}
