package org.fazio.utils.range;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/21/12 1:33 AM
 */
public class RangeValueTest {

	RangeValue basicValue;
	RangeValue sizeValue;
	RangeValue startEndValue;

	@Before
	public void setUp() throws Exception {
		this.basicValue = new RangeValue("Basic");
		this.sizeValue = new RangeValue(74, "Size");
		this.startEndValue = new RangeValue(23, 104, "Start/End");
	}

	@Test
	public void testRangeValueConstructors() throws Exception {
		assertEquals("The value is incorrect.", "Basic", this.basicValue.getValue());
		assertEquals("The start of the range is incorrect.", Range.DEFAULT_RANGE_START, this.basicValue.getStart());
		assertEquals("The end of the range is incorrect.", Range.DEFAULT_RANGE_END, this.basicValue.getEnd());
		assertEquals("The range size is incorrect.", Range.DEFAULT_RANGE_END - Range.DEFAULT_RANGE_START, this.basicValue.getRangeSize());

		assertEquals("The value is incorrect.", "Size", this.sizeValue.getValue());
		assertEquals("The start of the range is incorrect.", 0.0, this.sizeValue.getStart());
		assertEquals("The end of the range is incorrect.", 74.0, this.sizeValue.getEnd());
		assertEquals("The end of the range is incorrect.", 74.0 - Range.DEFAULT_RANGE_START, this.sizeValue.getRangeSize());

		assertEquals("The value is incorrect.", "Start/End", this.startEndValue.getValue());
		assertEquals("The start of the range is incorrect.", 23.0, this.startEndValue.getStart());
		assertEquals("The end of the range is incorrect.", 104.0, this.startEndValue.getEnd());
		assertEquals("The range size is incorrect.", 104.0-23.0, this.startEndValue.getRangeSize());
	}

	@Test
	public void testGetRangeValue() throws Exception {
		assertEquals("The returned range value is incorrect.", "Basic", this.basicValue.getRangeValue());
		assertEquals("The returned range value is incorrect.", "Size", this.sizeValue.getRangeValue());
		assertEquals("The returned range value is incorrect.", "Start/End", this.startEndValue.getRangeValue());
	}

	@Test
	public void testToString() throws Exception {
		assertEquals(
			"The toString() call is incorrect.",
			"Range Value: Value = Basic, Size = 100.0[0.0 -> 100.0]",
			this.basicValue.toString());
		assertEquals(
			"The toString() call is incorrect.",
			"Range Value: Value = Size, Size = 74.0[0.0 -> 74.0]",
			this.sizeValue.toString());
		assertEquals(
			"The toString() call is incorrect.",
			"Range Value: Value = Start/End, Size = 81.0[23.0 -> 104.0]",
			this.startEndValue.toString());
	}
}
