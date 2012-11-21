package org.fazio.utils.map;

import java.util.HashMap;

/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/20/12 5:35 PM
 */
public class CountingHashMap<K, V extends Number>
	extends HashMap<K, Number>
	implements CountingMap<K, V> {

	@Override
	public void put(final K key) {
		this.put(key, 0);
	}

	@Override
	public void reset() {
		this.setAll(0);
	}

	@Override
	public void setAll(final Number amount) {
		for(K key : this.keySet()) {
			this.put(key, amount);
		}
	}

	@Override
	public void increaseCount(final K key) {
		this.increaseCount(key, 1);
	}

	@Override
	public void increaseCount(final K key, final Number amount) {
		this.put(key, this.get(key).longValue() + amount.longValue());
	}

	@Override
	public void decreaseCount(final K key) {
		this.increaseCount(key, -1);
	}

	@Override
	public void decreaseCount(final K key, final Number amount) {
		this.increaseCount(key, -amount.longValue());
	}
}
