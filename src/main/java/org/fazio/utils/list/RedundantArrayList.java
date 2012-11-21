package org.fazio.utils.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/20/12 9:27 PM
 */
public class RedundantArrayList<E> extends ArrayList<E> implements RedundantList<E> {

	public E removeEach(final E value) {
		final List<Integer> indList = new ArrayList<Integer>();
		for(int x=0;x<this.size();x++) {
			if(this.get(x).equals(value)) {
				indList.add(0, x);
			}
		}

		for(Integer ind : indList) {
			this.remove(ind.intValue());
		}

		return value;
	}

	public E removeEach(final int index) {
		return this.removeEach(this.get(index));
	}



}
