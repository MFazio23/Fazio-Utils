package org.fazio.utils.range;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael Fazio
 * @since 11/20/12 10:19 AM
 */
public class RangeTest {

	public static final double DEFAULT_START = 0.0;
	public static final double DEFAULT_END = 100.0;
	public static final double TEST_RANGE_SIZE = 23.0;
	public static final double TEST_RANGE_START = 10.0;
	public static final double TEST_RANGE_END = 125.0;

	private Range range;
	private Range rangeSize;
	private Range rangeStartEnd;

	@Before
	public void setUp() throws Exception {
		this.range = new Range<String>() {
			@Override
			public String getRangeValue(final double value) {
				return "Basic Range";
			}
		};
		this.rangeSize = new Range<String>(TEST_RANGE_SIZE) {
			@Override
			public String getRangeValue(final double value) {
				return "Sized Range";
			}
		};
		this.rangeStartEnd = new Range<String>(TEST_RANGE_START, TEST_RANGE_END) {
			@Override
			public String getRangeValue(final double value) {
				return "Start/End Range";
			}
		};
	}

	@Test
	public void testConstructors() {
		Assert.assertEquals("Default start is incorrect.", DEFAULT_START, this.range.getStart(), 0);
		Assert.assertEquals("Default end is incorrect.", DEFAULT_END, this.range.getEnd(), 0);
		Assert.assertEquals("Default range is incorrect.", DEFAULT_END - DEFAULT_START, this.range.getRangeSize(), 0);

		Assert.assertEquals("Range Size start is incorrect.", DEFAULT_START, this.rangeSize.getStart(), 0);
		Assert.assertEquals("Range Size end is incorrect.", TEST_RANGE_SIZE, this.rangeSize.getEnd(), 0);
		Assert.assertEquals("Range Size range is incorrect.", TEST_RANGE_SIZE - DEFAULT_START, this.rangeSize.getRangeSize(), 0);

		Assert.assertEquals("Range Start/End start is incorrect.", TEST_RANGE_START, this.rangeStartEnd.getStart(), 0);
		Assert.assertEquals("Range Start/End end is incorrect.", TEST_RANGE_END, this.rangeStartEnd.getEnd(), 0);
		Assert.assertEquals("Range Start/End range is incorrect.", TEST_RANGE_END - TEST_RANGE_START, this.rangeStartEnd.getRangeSize(), 0);
	}

	@Test
	public void testIsInRange() throws Exception {
		Assert.assertTrue("Value is not considered in default range", this.rangeSize.isInRange(10.0));
		Assert.assertFalse("Value is considered in default range and should not.", this.range.isInRange(127.0));
		Assert.assertFalse("Value is considered in default range and should not.", this.range.isInRange(-41.5));

		Assert.assertTrue("Value is not considered in size range", this.rangeSize.isInRange(7.4));
		Assert.assertFalse("Value is considered in size range and should not.", this.rangeSize.isInRange(-1.5));
		Assert.assertFalse("Value is considered in size range and should not.", this.rangeSize.isInRange(42.0));

		Assert.assertTrue("Value is not considered in start/end range", this.rangeStartEnd.isInRange(48.4));
		Assert.assertFalse("Value is considered in start/end range and should not.", this.rangeStartEnd.isInRange(4.5));
		Assert.assertFalse("Value is considered in start/end range and should not.", this.rangeStartEnd.isInRange(175.45));
	}

	@Test
	public void testSetRange() throws Exception {
		Assert.assertEquals("Default start is incorrect.", DEFAULT_START, this.range.getStart(), 0);
		Assert.assertEquals("Default end is incorrect.", DEFAULT_END, this.range.getEnd(), 0);
		Assert.assertEquals("Default range is incorrect.", DEFAULT_END - DEFAULT_START, this.range.getRangeSize(), 0);

		this.range.setRangeSize(14.0);

		Assert.assertEquals("Default start is incorrect.", DEFAULT_START, this.range.getStart(), 0);
		Assert.assertEquals("Default end is incorrect.", 14.0, this.range.getEnd(), 0);
		Assert.assertEquals("Default range is incorrect.", 14.0 - DEFAULT_START, this.range.getRangeSize(), 0);

		this.range.setRange(21.0, 74.0);

		Assert.assertEquals("Default start is incorrect.", 21.0, this.range.getStart(), 0);
		Assert.assertEquals("Default end is incorrect.", 74.0, this.range.getEnd(), 0);
		Assert.assertEquals("Default range is incorrect.", 74.0 - 21.0, this.range.getRangeSize(), 0);
	}

	@Test
	public void testSetStartEnd() throws Exception {
		Assert.assertEquals("Default start is incorrect.", DEFAULT_START, this.range.getStart(), 0);
		Assert.assertEquals("Default end is incorrect.", DEFAULT_END, this.range.getEnd(), 0);
		Assert.assertEquals("Default range is incorrect.", DEFAULT_END - DEFAULT_START, this.range.getRangeSize(), 0);

		this.range.setStart(14.0);
		this.range.setEnd(127.0);

		Assert.assertEquals("Default start is incorrect.", 14.0, this.range.getStart(), 0);
		Assert.assertEquals("Default end is incorrect.", 127.0, this.range.getEnd(), 0);
		Assert.assertEquals("Default range is incorrect.", 127.0 - 14.0, this.range.getRangeSize(), 0);
	}

	@Test
	public void testGetSetRangeSize() throws Exception {
		Assert.assertEquals("Size range's range size is incorrect.", TEST_RANGE_SIZE, this.rangeSize.getRangeSize(), 0);

		this.range.setRangeSize(145.0);
		Assert.assertEquals("Default range's range size is incorrect.", 145.0, this.range.getRangeSize(), 0);

		Assert.assertEquals("Start/End range's range size is incorrect.", TEST_RANGE_END - TEST_RANGE_START, this.rangeStartEnd.getRangeSize(), 0);
		this.rangeStartEnd.setStart(14);
		Assert.assertEquals("Start/End range's range size is incorrect.", TEST_RANGE_END - TEST_RANGE_START, this.rangeStartEnd.getRangeSize(), 0);
		this.rangeStartEnd.setEnd(85);
		Assert.assertEquals("Start/End range's range size is incorrect.", 85.0 - 14.0, this.rangeStartEnd.getRangeSize(), 0);
		this.rangeStartEnd.setRangeSize(116);
		Assert.assertEquals("Start/End range's range size is incorrect.", 116.0, this.rangeStartEnd.getRangeSize(), 0);
		Assert.assertEquals("Start/End range's range size is incorrect.", 14.0, this.rangeStartEnd.getStart(), 0);
		Assert.assertEquals("Start/End range's range size is incorrect.", 116.0 + 14.0, this.rangeStartEnd.getEnd(), 0);
	}

	@Test
	public void testToString() throws Exception {
		Assert.assertEquals("The range's toString() is incorrect.", "Range: Size = 100.0 [0.0 -> 100.0]", this.range.toString());
	}
}
