package org.fazio.utils.pair;

/**
 * @author Michael Fazio
 * @since 1.0.2
 */
public class MutablePair<L, R> {

	private L left;
	private R right;

	public MutablePair() { }

	public MutablePair(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public void setLeft(final L left) {
		this.left = left;
	}

	public R getRight() {
		return right;

	}

	public void setRight(final R right) {
		this.right = right;
	}

	public void setValues(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	public boolean contains(final Object value) {
		return this.left.equals(value) || this.right.equals(value);
	}

	@Override
	public String toString() {
		return "MutablePair{" +
			"left=" + left +
			", right=" + right +
			'}';
	}
}
