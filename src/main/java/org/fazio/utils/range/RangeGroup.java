package org.fazio.utils.range;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Fazio
 */
public class RangeGroup extends Range {

	private final List<Range> rangeList = new ArrayList<Range>();
	private Range defaultRange;

	private static final String DEFAULT_RANGE_VALUE = "Default";

	public RangeGroup() {
		super();
	}

	public RangeGroup(final double rangeSize) {
		super(rangeSize);
	}

	public RangeGroup(final double start, final double end) {
		super(start, end);
	}

	@Override
	public Object getRangeValue(final double value) {
		Object rangeValue = defaultRange == null ? null : defaultRange.getRangeValue();

		for(Range checkRange : this.rangeList) {
			if(checkRange.isInRange(value)) {
				rangeValue = checkRange.getRangeValue();
			}
		}

		return rangeValue;
	}

	private void recalculateRanges() {
		double lastEndRange = DEFAULT_RANGE_START;
		this.rangeSize = 0;
		for(Range range : this.rangeList) {
			this.rangeSize += range.getRangeSize();
			lastEndRange = range.setRange(lastEndRange);
		}

		if(this.defaultRange == null) {
			this.defaultRange = new RangeValue(DEFAULT_RANGE_VALUE);
			this.defaultRange.setRange(lastEndRange, 0.0);
		}

		this.end = this.start + this.rangeSize;
	}

	public RangeGroup addRangeValue(final Object value) {
		return this.addToRangeGroup(new RangeValue(value));
	}

	public RangeGroup addRangeValue(final Object value, final double size) {
		return this.addToRangeGroup(new RangeValue(size, value));
	}

	public RangeGroup addRangeValue(final Object value, final double start, final double end) {
		return this.addToRangeGroup(new RangeValue(start, end, value));
	}

	public RangeGroup addToRangeGroup(final Range range) {
		this.rangeList.add(range);

		this.recalculateRanges();

		return this;
	}

	public Range getDefaultRange() {
		return this.defaultRange;
	}

	public RangeGroup setDefaultRange(final Range range) {
		this.defaultRange = range;

		this.recalculateRanges();

		return this;
	}

	public String toString(final int level) {
		StringBuilder sb = new StringBuilder();
		for(int x=0;x<level;x++) sb.append("\t");
		sb.append("Range Group: Size = ");
		sb.append(super.rangeSize);
		sb.append("[");
		sb.append(super.start);
		sb.append(" -> ");
		sb.append(super.end);
		sb.append("]\n");
		for(Range range : this.rangeList) {
			sb.append(range.toString(level + 1));
			sb.append('\n');
		}

		sb.append(this.defaultRange == null ? "" : this.defaultRange.toString(level + 1));
		sb.append(" (Default)");

		return sb.toString();
	}

	public String toString() {
		return this.toString(0);
	}
}