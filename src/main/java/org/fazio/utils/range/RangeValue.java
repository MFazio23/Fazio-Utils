package org.fazio.utils.range;

/**
 * @author Michael Fazio
 */
public class RangeValue<V> extends Range<V> {
	
	private final V value;

	public RangeValue(final RangeValue<V> startingValue) {
		super(startingValue.getStart(), startingValue.getEnd());
		this.value = startingValue.getValue();
	}

	public RangeValue(final V value) {
		super();
		this.value = value;
	}
	
	public RangeValue(final V value, final double rangeSize) {
		super(rangeSize);
		this.value = value;
	}

	public RangeValue(final V value, final double start, final double end) {
		super(start, end);
		this.value = value;
	}

	@Override
	public V getRangeValue(final double value) {
		return this.getValue();
	}
	
	public V getValue() {
		return this.value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;

		final RangeValue<V> that = (RangeValue) o;

		return this.value != null ? this.value.equals(that.value) : that.value == null;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("Range Value: Value = ")
		.append(this.value != null ? this.value.toString() : "null")
		.append(", Size = ")
		.append(super.getRangeSize())
		.append("[")
		.append(super.start)
		.append(" -> ")
		.append(super.end)
		.append("]")
		.toString();
	}
}
