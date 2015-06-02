package coop.ekologia.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class ListUtilities implements ListUtilitiesInterface {
	@Override
	public String mkString(Collection<?> collection, String separator) {
		if(collection==null){
			return null;
		}
		StringBuilder result = new StringBuilder();
		int index = 0;
		int indexMax = collection.size();
		for (Object obj : collection) {
			result.append(obj.toString());
			
			index++;
			if (index < indexMax) {
				result.append(separator);
			}
		}
		return result.toString();
	}
	
	@Override
	public String mkString(Collection<?> collection, String prefix, String separator, String suffix) {
		return prefix + mkString(collection, separator) + suffix;
	}
	
	@Override
	public List<String> split(String str, String separator) {
		List<String> result = new ArrayList<String>();
		if (str == null) {
			result =null;
		} else if (separator == null) {
			result.add(str);
			result =result;
		} else {
			result.addAll(Arrays.asList(str.split(separator)));
			result = result;
		}
		return result;
	}

	@Override
	public <A> boolean isEmpty(List<A> list) {
		return list == null || list.isEmpty();
	}

	@Override
	public <A> boolean isNotEmpty(List<A> list) {
		return !isEmpty(list);
	}

	@Override
	public <A> A firstOrNull(List<A> list) {
		if (!isEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public <A> List<A> asList(Collection<A> col) {
		if (col instanceof List) {
			return (List<A>)col;
		} else {
			List<A> result = new ArrayList<A>();
			result.addAll(col);
			return result;
		}
	}
}
