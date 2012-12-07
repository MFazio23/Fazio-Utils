package org.fazio.utils.range;

/**
 * @author Michael Fazio
 */
public abstract class Range<V> {

	public static final double DEFAULT_RANGE_START = 0.0;
	public static final double DEFAULT_RANGE_END = 100.0;

	protected double start;
	protected double end;

	public Range() {
		this(DEFAULT_RANGE_END);
	}

	public Range(final double rangeSize) {
		this(DEFAULT_RANGE_START, rangeSize);
	}

	public Range(final double start, final double end) {
		this.setRange(start, end);
	}

	public double setRange(final double start, final double end) {
		this.start = start;
		this.end = end;
		return this.end;
	}

	public double setRangeSize(double rangeSize) {
		this.end = this.start + rangeSize;
		return this.end;
	}

	public boolean isInRange(final double value) {
		boolean inRange = true;

		if(value >= end) inRange = false;
		if(value < start) inRange = false;

		return inRange;
	}
	
	public V getRangeValue() {
		return this.getRangeValue(Math.random() * (this.end - this.start));
	}
	
	public abstract V getRangeValue(final double value);

	public double getEnd() {
		return end;
	}

	public double setEnd(double end) {
		this.end = end;
		return this.end - this.start;
	}

	public double getStart() {
		return start;
	}

	public double setStart(double start) {
		this.end = this.end - this.start + start;
		this.start = start;
		return this.end;
	}

	public double getRangeSize() {
		return this.end - this.start;
	}
	
	public String toString() {
		return "Range: Size = " + (this.end - this.start) + " [" + this.start + " -> " + this.end + "]";
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
