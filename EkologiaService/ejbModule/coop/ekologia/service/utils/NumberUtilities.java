package coop.ekologia.service.utils;

import javax.ejb.Stateless;

@Stateless
public class NumberUtilities implements NumberUtilitiesInterface {
	@Override
	public boolean equals(Integer int1, Integer int2) {
		if (int1 == null && int2 == null) {
			return true;
		} else if (int1 == null || int2 == null) {
			return false;
		} else {
			return int1.equals(int2);
		}
	}
}
