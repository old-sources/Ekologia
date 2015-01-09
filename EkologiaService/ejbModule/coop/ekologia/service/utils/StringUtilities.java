package coop.ekologia.service.utils;

import javax.ejb.Stateless;

@Stateless
public class StringUtilities implements StringUtilitiesInterface {
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
}
