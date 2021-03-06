package org.fazio.utils.map;

import java.util.Collection;
import java.util.Map;

/**
 * @author Michael Fazio
 * @since 11/20/12 5:36 PM
 */
public interface CountingMap<K, V extends Number> extends Map<K, Number> {

	public void put(final K key);
	public void putAll(final Collection<K> keys);
	public void reset();
	public void setAll(final Number amount);
	public void increaseCount(final K key);
	public void increaseCount(final K key, final Number amount);
	public void decreaseCount(final K key);
	public void decreaseCount(final K key, final Number amount);
	public void sortByKeys();
	public void sortByKeys(final boolean lowToHigh);
	public void sortByCounts();
	public void sortByCounts(final boolean lowToHigh);

}
