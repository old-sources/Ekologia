package coop.ekologia.service.utils;

import javax.ejb.Local;
import java.util.Collection;
import java.util.List;

@Local
public interface ListUtilitiesInterface {

	public abstract String mkString(Collection<?> collection, String separator);

	public abstract String mkString(Collection<?> collection, String prefix, String separator,
			String suffix);

	public abstract List<String> split(String str, String separator);

	<A> boolean isEmpty(List<A> list);

	<A> boolean isNotEmpty(List<A> list);

	<A> A firstOrNull(List<A> list);

	<A> List<A> asList(Collection<A> col);
}