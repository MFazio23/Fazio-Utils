package org.fazio.utils.range;

/**
 * @author Michael Fazio
 */
public abstract class Range {

	public static final double DEFAULT_RANGE_START = 0;
	public static final double DEFAULT_RANGE_END = 100;

	protected double start;
	protected double end;
	protected double rangeSize;

	public Range() {
		this(DEFAULT_RANGE_START, DEFAULT_RANGE_END);
	}

	public Range(final double rangeSize) {
		this(DEFAULT_RANGE_START, rangeSize);
	}

	public Range(final double start, final double end) {
		this.setRange(start, end);
	}

	public boolean isInRange(final double value) {
		boolean inRange = true;

		if(value >= end) inRange = false;
		if(value < start) inRange = false;

		return inRange;
	}
	
	public Object getRangeValue() {
		return this.getRangeValue(Math.random() * this.rangeSize);
	}
	
	public abstract Object getRangeValue(final double value);
	
	public void setRange(final double start, final double end) {
		this.start = start;
		this.end = end;
		this.recalculateRangeSize();
	}

	public double setRange(final double start) {
		this.start = start;
		this.end = this.start + this.rangeSize;
		this.recalculateRangeSize();

		return this.end;
	}

	protected double recalculateRangeSize() {
		this.rangeSize = this.end - this.start;
		return this.rangeSize;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
		this.recalculateRangeSize();
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
		this.recalculateRangeSize();
	}

	public double getRangeSize() {
		return rangeSize;
	}

	public void setRangeSize(double rangeSize) {
		this.rangeSize = rangeSize;
		this.end = this.start + this.rangeSize;
	}
	
	public String toString() {
		return "Range: " + this.start + " -> " + this.end + " [" + this.rangeSize + "]";
	}

	public String toString(final int level) {
		final StringBuilder sb = new StringBuilder();
		for(int x=0;x<level;x++) {
			sb.append('\t');
		}
		sb.append(this.toString());

		return sb.toString();
	}
}
