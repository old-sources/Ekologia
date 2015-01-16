package coop.ekologia.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	@Override
	public Date stringToDate(String str) {
	    return stringToDate(str, false);
	}
	
	@Override
	public Date stringToDate(String str, boolean fromInput) {
	    SimpleDateFormat format;
	    if (fromInput) {
	        format = new SimpleDateFormat("yyyy-MM-dd");
	    } else {
	        format = new SimpleDateFormat("dd/MM/yyyy");
	    }
	    try {
            return format.parse(str);
        } catch (ParseException e) {
            return null;
        }
	}
	
	@Override
	public String dateToString(Date date) {
	    return dateToString(date, false);
	}
	
	@Override
	public String dateToString(Date date, boolean forInput) {
	    SimpleDateFormat format;
        if (forInput) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            format = new SimpleDateFormat("dd/MM/yyyy");
        }
        return format.format(date);
	}
}
