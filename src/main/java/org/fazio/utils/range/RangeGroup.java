package org.fazio.utils.range;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Fazio
 */
public class RangeGroup<V> extends Range<V> {

	public static final double DEFAULT_SUB_RANGE_SIZE = 100.0;

	private final List<Range<V>> rangeList = new ArrayList<Range<V>>();
	private Range<V> defaultRange;
	private double subRangeSize = RangeGroup.DEFAULT_SUB_RANGE_SIZE;

	public RangeGroup() {
		super();
	}

	public RangeGroup(final double rangeSize) {
		super(rangeSize);
	}

	public RangeGroup(final double rangeSize, final double subRangeSize) {
		super(rangeSize);
		this.subRangeSize = subRangeSize;
	}

	@Override
	public V getRangeValue() {
		return this.getRangeValue(Math.random() * this.getSubRangeListSize());
	}

	@Override
	public V getRangeValue(final double value) {
		V rangeValue = (defaultRange != null) ? defaultRange.getRangeValue() : null;

		for(Range<V> checkRange : this.rangeList) {
			if(checkRange.isInRange(value)) {
				rangeValue = checkRange.getRangeValue();
			}
		}

		return rangeValue;
	}

	public RangeGroup<V> addRangeValue(final V value, final double size) {
		return this.addToRangeGroup(new RangeValue<V>(value, size));
	}

	public RangeGroup<V> addToRangeGroup(final Range<V> range) {
		this.rangeList.add(range);

		this.updateRangeSizes();

		return this;
	}

	private void updateRangeSizes() {
		double end = 0.0;
		for(Range<V> range : this.rangeList) {
			range.setStart(end);
			end += range.getRangeSize();
		}

		if(this.defaultRange != null) this.defaultRange.setRange(end, this.subRangeSize);
	}

	public RangeGroup<V> setDefaultRange(final Range<V> defaultRange) {
		this.defaultRange = defaultRange;
		this.updateRangeSizes();
		return this;
	}

	public RangeGroup<V> setDefaultRangeValue(final V defaultValue) {
		return this.setDefaultRange(new RangeValue<V>(defaultValue));
	}

	public Range<V> getDefaultRange() {
		return this.defaultRange;
	}

	public double getSubRangeListSize() {
		double size = this.defaultRange != null ? this.defaultRange.getRangeSize() : 0.0;
		for(Range<V> range : this.rangeList) {
			size += range.getRangeSize();
		}

		return size;
	}

	public double getSubRangeSize() {
		return this.subRangeSize;
	}

	public double setSubRangeSize(final double subRangeSize) {
		if(this.defaultRange != null) this.defaultRange.setEnd(subRangeSize);
		this.subRangeSize = subRangeSize;
		return subRangeSize;
	}

	public int getRangeCount() {
		return this.rangeList.size() + (this.defaultRange == null ? 0 : 1);
	}

	public String toString(final int level) {
		final StringBuilder sb = new StringBuilder();
		for(int x=0;x<level;x++) sb.append("\t");
		sb
			.append("Range Group: Size = ")
			.append(super.end - super.start)
			.append(" [")
			.append(super.start)
			.append(" -> ")
			.append(super.end)
			.append("]");
		for(Range range : this.rangeList) {
			sb
				.append('\n')
				.append(range.toString(level + 1));
		}
		if(this.defaultRange != null) {
			sb
				.append('\n')
				.append(this.defaultRange.toString(level + 1))
				.append(" (Default)");
		}

		return sb.toString();
	}

	public String toString() {
		return this.toString(0);
	}
}