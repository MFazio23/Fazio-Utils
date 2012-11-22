package org.fazio.utils.list;

import java.util.List;

/**
 * @author Michael Fazio
 * @since 11/20/12 9:26 PM
 */
public interface RedundantList<E> extends List<E> {

	public E removeEach(final E value);
	public E removeEach(final int index);

}
