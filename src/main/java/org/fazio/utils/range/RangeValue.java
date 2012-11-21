package org.fazio.utils.range;

/**
 * @author Michael Fazio
 */
public class RangeValue extends Range {
	
	private final Object value;

	public RangeValue(final Object value) {
		this(100.0, value);
	}
	
	public RangeValue(final double rangeSize, final Object value) {
		super(rangeSize);
		this.value = value;
	}

	public RangeValue(final double start, final double end, final Object value) {
		super(start, end);
		this.value = value;
	}

	@Override
	public Object getRangeValue(final double value) {
		return this.getValue();
	}
	
	public Object getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("Range Value: Value = ")
		.append(this.value)
		.append(", Size = ")
		.append(super.rangeSize)
		.append("[")
		.append(super.start)
		.append(" -> ")
		.append(super.end)
		.append("]")
		.toString();
	}
}
