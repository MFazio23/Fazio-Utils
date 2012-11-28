package org.fazio.utils.range;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/21/12 1:33 AM
 */
public class RangeValueTest {

	RangeValue<String> basicValue;
	RangeValue<String> sizeValue;
	RangeValue<String> startEndValue;

	@Before
	public void setUp() throws Exception {
		this.basicValue = new RangeValue<String>("Basic");
		this.sizeValue = new RangeValue<String>("Size", 74);
		this.startEndValue = new RangeValue<String>("Start/End", 23, 104);
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

		final RangeValue<String> newBasicValue = new RangeValue<String>(this.basicValue);
		assertEquals("The value is incorrect.", "Basic", newBasicValue.getValue());
		assertEquals("The start of the range is incorrect.", Range.DEFAULT_RANGE_START, newBasicValue.getStart());
		assertEquals("The end of the range is incorrect.", Range.DEFAULT_RANGE_END, newBasicValue.getEnd());
		assertEquals("The range size is incorrect.", Range.DEFAULT_RANGE_END - Range.DEFAULT_RANGE_START, newBasicValue.getRangeSize());

	}

	@Test
	public void testGetRangeValue() throws Exception {
		assertEquals("The returned range value is incorrect.", "Basic", this.basicValue.getRangeValue());
		assertEquals("The returned range value is incorrect.", "Size", this.sizeValue.getRangeValue());
		assertEquals("The returned range value is incorrect.", "Start/End", this.startEndValue.getRangeValue());
	}

	@Test
	public void testEquals() throws Exception {
		RangeValue<String> basic2 = new RangeValue<String>("Basic");
		RangeValue<String> basic3 = new RangeValue<String>("Basic", 14, 102);
		RangeValue<String> notBasic = new RangeValue<String>("Not Basic");
		RangeValue<String> nullValue = new RangeValue<String>(null, 100);
		RangeValue<String> nullValue2 = new RangeValue<String>(null, 75);
		RangeValue<String> nullRange = null;
		Double doubleObj = 24.53;
		Integer nullInt = null;

		assertTrue("The values are not considered equal and should be.", this.basicValue.equals(this.basicValue));
		assertTrue("The values are not considered equal and should be.", this.basicValue.equals(basic2));
		assertTrue("The values are not considered equal and should be.", this.basicValue.equals(basic3));
		assertFalse("The values are considered equal and should not be.", this.basicValue.equals(notBasic));
		assertFalse("The values are considered equal and should not be.", this.basicValue.equals(this.sizeValue));
		assertFalse("The values are considered equal and should not be.", this.basicValue.equals(nullValue));
		assertFalse("The values are considered equal and should not be.", nullValue.equals(this.basicValue));
		assertTrue("The values are not considered equal and should be.", nullValue.equals(nullValue2));
		assertFalse("The values are considered equal and should not be.", this.basicValue.equals(nullRange));
		assertFalse("The values are considered equal and should not be.", this.basicValue.equals(doubleObj));
		assertFalse("The values are considered equal and should not be.", this.basicValue.equals(nullInt));
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
