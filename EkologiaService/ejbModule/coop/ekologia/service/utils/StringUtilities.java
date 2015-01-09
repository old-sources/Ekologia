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
}
