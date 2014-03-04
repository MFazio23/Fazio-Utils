package org.fazio.utils.pair;

/**
 * @author Michael Fazio
 * @since 11/21/12 7:46 PM
 */
public class Pair<L, R> {

	private final L left;
	private final R right;

	public Pair(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	public boolean contains(final Object value) {
		return this.left.equals(value) || this.right.equals(value);
	}

	@Override
	public String toString() {
		return "Pair{" +
			"left=" + left +
			", right=" + right +
			'}';
	}
}
