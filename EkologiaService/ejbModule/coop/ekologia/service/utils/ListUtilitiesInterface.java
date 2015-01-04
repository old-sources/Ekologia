package coop.ekologia.service.utils;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ListUtilitiesInterface {

	public abstract String mkString(Collection<?> collection, String separator);

	public abstract String mkString(Collection<?> collection, String prefix, String separator,
			String suffix);

	public abstract List<String> split(String str, String separator);

}