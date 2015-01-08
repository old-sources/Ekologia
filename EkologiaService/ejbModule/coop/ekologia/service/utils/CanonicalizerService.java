package coop.ekologia.service.utils;

import java.text.Normalizer;

import javax.ejb.Stateless;

@Stateless
public class CanonicalizerService implements CanonicalizerServiceInterface {
	@Override
	public String strToUrl(String str) {
		String result = Normalizer.normalize(str, Normalizer.Form.NFD);
		result = result.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		result = result.toLowerCase();
		result = result.replaceAll("[^a-z0-9_-]", "-");
		result = result.replaceAll("-+", "-");
		return result;
	}
}
