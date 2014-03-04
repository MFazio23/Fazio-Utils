package org.fazio.utils.range;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
		Assert.assertEquals("The value is incorrect.", "Basic", this.basicValue.getValue());
		Assert.assertEquals("The start of the range is incorrect.", Range.DEFAULT_RANGE_START, this.basicValue.getStart(), 0);
		Assert.assertEquals("The end of the range is incorrect.", Range.DEFAULT_RANGE_END, this.basicValue.getEnd(), 0);
		Assert.assertEquals("The range size is incorrect.", Range.DEFAULT_RANGE_END - Range.DEFAULT_RANGE_START, this.basicValue.getRangeSize(), 0);

		Assert.assertEquals("The value is incorrect.", "Size", this.sizeValue.getValue());
		Assert.assertEquals("The start of the range is incorrect.", 0.0, this.sizeValue.getStart(), 0);
		Assert.assertEquals("The end of the range is incorrect.", 74.0, this.sizeValue.getEnd(), 0);
		Assert.assertEquals("The end of the range is incorrect.", 74.0 - Range.DEFAULT_RANGE_START, this.sizeValue.getRangeSize(), 0);

		Assert.assertEquals("The value is incorrect.", "Start/End", this.startEndValue.getValue());
		Assert.assertEquals("The start of the range is incorrect.", 23.0, this.startEndValue.getStart(), 0);
		Assert.assertEquals("The end of the range is incorrect.", 104.0, this.startEndValue.getEnd(), 0);
		Assert.assertEquals("The range size is incorrect.", 104.0-23.0, this.startEndValue.getRangeSize(), 0);

		final RangeValue<String> newBasicValue = new RangeValue<String>(this.basicValue);
		Assert.assertEquals("The value is incorrect.", "Basic", newBasicValue.getValue());
		Assert.assertEquals("The start of the range is incorrect.", Range.DEFAULT_RANGE_START, newBasicValue.getStart(), 0);
		Assert.assertEquals("The end of the range is incorrect.", Range.DEFAULT_RANGE_END, newBasicValue.getEnd(), 0);
		Assert.assertEquals("The range size is incorrect.", Range.DEFAULT_RANGE_END - Range.DEFAULT_RANGE_START, newBasicValue.getRangeSize(), 0);

	}

	@Test
	public void testGetRangeValue() throws Exception {
		Assert.assertEquals("The returned range value is incorrect.", "Basic", this.basicValue.getRangeValue());
		Assert.assertEquals("The returned range value is incorrect.", "Size", this.sizeValue.getRangeValue());
		Assert.assertEquals("The returned range value is incorrect.", "Start/End", this.startEndValue.getRangeValue());
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

		Assert.assertTrue("The values are not considered equal and should be.", this.basicValue.equals(this.basicValue));
		Assert.assertTrue("The values are not considered equal and should be.", this.basicValue.equals(basic2));
		Assert.assertTrue("The values are not considered equal and should be.", this.basicValue.equals(basic3));
		Assert.assertFalse("The values are considered equal and should not be.", this.basicValue.equals(notBasic));
		Assert.assertFalse("The values are considered equal and should not be.", this.basicValue.equals(this.sizeValue));
		Assert.assertFalse("The values are considered equal and should not be.", this.basicValue.equals(nullValue));
		Assert.assertFalse("The values are considered equal and should not be.", nullValue.equals(this.basicValue));
		Assert.assertTrue("The values are not considered equal and should be.", nullValue.equals(nullValue2));
		Assert.assertFalse("The values are considered equal and should not be.", this.basicValue.equals(nullRange));
		Assert.assertFalse("The values are considered equal and should not be.", this.basicValue.equals(doubleObj));
		Assert.assertFalse("The values are considered equal and should not be.", this.basicValue.equals(nullInt));
	}

	@Test
	public void testToString() throws Exception {
		Assert.assertEquals(
			"The toString() call is incorrect.",
			"Range Value: Value = Basic, Size = 100.0 [0.0 -> 100.0]",
			this.basicValue.toString());
		Assert.assertEquals(
			"The toString() call is incorrect.",
			"Range Value: Value = Size, Size = 74.0 [0.0 -> 74.0]",
			this.sizeValue.toString());
		Assert.assertEquals(
			"The toString() call is incorrect.",
			"Range Value: Value = Start/End, Size = 81.0 [23.0 -> 104.0]",
			this.startEndValue.toString());
		Assert.assertEquals(
			"The toString() call was incorrect.",
			"Range Value: Value = null, Size = 25.0 [0.0 -> 25.0]",
			new RangeValue<String>(null, 0, 25).toString());
	}

	@Test
	public void testHashCode() throws Exception {
		Assert.assertTrue("The hash code is invalid", this.startEndValue.hashCode() != 0);
		Assert.assertEquals("The hash code is invalid.", 0, new RangeValue<String>(null, 24).hashCode());
	}
}
