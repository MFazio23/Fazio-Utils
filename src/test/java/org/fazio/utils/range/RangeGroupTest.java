package org.fazio.utils.range;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/26/12 7:34 PM
 */
public class RangeGroupTest {

	private RangeGroup<String> basicGroup;
	private RangeGroup<String> basicDefaultGroup;
	private RangeGroup<String> rangeSizeGroup;
	private RangeGroup<String> multiLevelGroup;
	private RangeGroup<String> subRangeGroup;

	@Before
	public void setUp() throws Exception {
		this.basicGroup	= new RangeGroup<String>()
			.addRangeValue("40 basic", 40)
			.addRangeValue("20 basic", 20)
			.addRangeValue("15 basic", 15);

		this.basicDefaultGroup = new RangeGroup<String>()
			.addRangeValue("22 basicD", 22)
			.addRangeValue("16 basicD", 16)
			.addRangeValue("42 basicD", 42)
			.setDefaultRangeValue("Default basicD");

		this.rangeSizeGroup = new RangeGroup<String>(150)
			.addRangeValue("25 size", 25)
			.addRangeValue("30 size", 30)
			.addRangeValue("45 size", 45)
			.addRangeValue("10 size", 10);

		this.multiLevelGroup = new RangeGroup<String>(75)
			.addRangeValue("25 multiLevel", 25)
			.addRangeValue("30 multiLevel", 30)
			.addToRangeGroup(
				new RangeGroup<String>(50)
					.addRangeValue("Inner 20", 20)
					.addRangeValue("Inner 35", 35)
					.addRangeValue("Inner 15", 15)
			);

		this.subRangeGroup = new RangeGroup<String>(110, 150)
			.addRangeValue("17 subRange", 17)
			.addRangeValue("21 subRange", 21)
			.addRangeValue("37 subRange", 37)
			.addRangeValue("7.5 subRange", 7.5);
	}

	@Test
	public void testRangeGroupConstructors() throws Exception {
		Assert.assertEquals("The number of items in the basic group is incorrect.", 3, this.basicGroup.getRangeCount());
		Assert.assertEquals("The number of items in the range size group is incorrect.", 4, this.rangeSizeGroup.getRangeCount());
		Assert.assertEquals("The number of items in the sub range group is incorrect.", 3, this.multiLevelGroup.getRangeCount());
		Assert.assertEquals("The number of items in the sub range group is incorrect.", 4, this.basicDefaultGroup.getRangeCount());
		Assert.assertEquals("The number of items in the sub range group is incorrect.", 4, this.subRangeGroup.getRangeCount());

		Assert.assertEquals("The size of the basic group is incorrect.", 100.0, this.basicGroup.getRangeSize(), 0);
		Assert.assertEquals("The size of the range size group is incorrect.", 150.0, this.rangeSizeGroup.getRangeSize(), 0);
		Assert.assertEquals("The size of the multi-level range group is incorrect.", 75.0, this.multiLevelGroup.getRangeSize(), 0);
		Assert.assertEquals("The size of the subrange group is incorrect.", 110.0, this.subRangeGroup.getRangeSize(), 0);

		Assert.assertEquals("The size of the basic subrange is incorrect.", 100.0, this.basicGroup.getSubRangeSize(), 0);
		Assert.assertEquals("The size of the range size subrange is incorrect.", 100.0, this.rangeSizeGroup.getSubRangeSize(), 0);
		Assert.assertEquals("The size of the multi-level subrange is incorrect.", 100.0, this.multiLevelGroup.getSubRangeSize(), 0);
		Assert.assertEquals("The size of the subrange group subrange is incorrect.", 150.0, this.subRangeGroup.getSubRangeSize(), 0);
	}

	@Test
	public void testGetRangeValue() throws Exception {
		Assert.assertEquals("The returned value from the subrange is incorrect.", "17 subRange", this.subRangeGroup.getRangeValue(14));
		Assert.assertEquals("The returned value from the subrange is incorrect.", "21 subRange", this.subRangeGroup.getRangeValue(33));
		Assert.assertEquals("The returned value from the subrange is incorrect.", "37 subRange", this.subRangeGroup.getRangeValue(49.14));
		Assert.assertEquals("The returned value from the subrange is incorrect.", "7.5 subRange", this.subRangeGroup.getRangeValue(80.94));
		Assert.assertEquals("The returned value from the subrange is incorrect.", null, this.subRangeGroup.getRangeValue(175.14));
		Assert.assertEquals("The returned value from the subrange is incorrect.", null, this.subRangeGroup.getRangeValue(-479));

		Assert.assertEquals("The returned value from the multi-level range is incorrect.", "25 multiLevel", this.multiLevelGroup.getRangeValue(12.1));
		Assert.assertEquals("The returned value from the multi-level range is incorrect.", "30 multiLevel", this.multiLevelGroup.getRangeValue(42.12));
		final List<String> possibleValues = new ArrayList<String>() {{
			add("Inner 20");
			add("Inner 35");
			add("Inner 15");
		}};
		Assert.assertTrue("The returned value from the multi-level range is incorrect.", possibleValues.contains(this.multiLevelGroup.getRangeValue(95)));
	}

	@Test
	public void testAddRangeValue() throws Exception {
		Assert.assertEquals("The number of items in the basic group is incorrect.", 3, this.basicGroup.getRangeCount());
		this.basicGroup.addRangeValue("Extra Value", 17.2);
		Assert.assertEquals("The number of items in the basic group is incorrect.", 4, this.basicGroup.getRangeCount());
		Assert.assertEquals("The number of items in the basic group is incorrect.", 92.2, this.basicGroup.getSubRangeListSize(), 0);
	}

	@Test
	public void testAddToRangeGroup() throws Exception {
		Assert.assertEquals("The number of items in the basic group is incorrect.", 3, this.basicGroup.getRangeCount());
		this.basicGroup.addToRangeGroup(new RangeValue<String>("new RG Value", 17.2));
		Assert.assertEquals("The number of items in the basic group is incorrect.", 4, this.basicGroup.getRangeCount());
		Assert.assertEquals("The number of items in the basic group is incorrect.", 92.2, this.basicGroup.getSubRangeListSize(), 0);
		this.basicGroup.addToRangeGroup(new RangeGroup<String>(43.2));
		Assert.assertEquals("The number of items in the basic group is incorrect.", 5, this.basicGroup.getRangeCount());
		Assert.assertEquals("The number of items in the basic group is incorrect.", 135.4, this.basicGroup.getSubRangeListSize(), 0);
	}

	@Test
	public void testDefaultRange() throws Exception {
		Assert.assertEquals("The default value is not correct.", new RangeValue<String>("Default basicD"), this.basicDefaultGroup.getDefaultRange());
		Assert.assertEquals("The default range size is not correct.", 20.0, this.basicDefaultGroup.getDefaultRange().getRangeSize(), 0);

		Assert.assertEquals("The number of items in the basic range is incorrect.", 3, this.basicGroup.getRangeCount());
		this.basicGroup.setDefaultRange(new RangeValue<String>("Default basic"));
		Assert.assertEquals("The number of items in the basic range is incorrect.", 4, this.basicGroup.getRangeCount());
		Assert.assertEquals("The value returned from the basic range is incorrect.", "40 basic", this.basicGroup.getRangeValue(25));
		Assert.assertEquals("The value returned from the basic range is incorrect.", "20 basic", this.basicGroup.getRangeValue(51));
		Assert.assertEquals("The value returned from the basic range is incorrect.", "15 basic", this.basicGroup.getRangeValue(66));
		Assert.assertEquals("The value returned from the basic range is incorrect.", "Default basic", this.basicGroup.getRangeValue(89));
		Assert.assertEquals("The value returned from the basic range is incorrect.", "Default basic", this.basicGroup.getRangeValue(-5));
		Assert.assertEquals("The value returned from the basic range is incorrect.", "Default basic", this.basicGroup.getRangeValue(107));

		Assert.assertEquals("The number of items in the sized range is incorrect.", 4, this.rangeSizeGroup.getRangeCount());
		this.rangeSizeGroup.setDefaultRangeValue("Default size");
		Assert.assertEquals("The number of items in the sized range is incorrect.", 5, this.rangeSizeGroup.getRangeCount());
		Assert.assertEquals("The value returned from the sized range is incorrect.", "25 size", this.rangeSizeGroup.getRangeValue(15));
		Assert.assertEquals("The value returned from the sized range is incorrect.", "30 size", this.rangeSizeGroup.getRangeValue(43));
		Assert.assertEquals("The value returned from the sized range is incorrect.", "45 size", this.rangeSizeGroup.getRangeValue(94));
		Assert.assertEquals("The value returned from the sized range is incorrect.", "10 size", this.rangeSizeGroup.getRangeValue(103));
		Assert.assertEquals("The value returned from the sized range is incorrect.", "Default size", this.rangeSizeGroup.getRangeValue(140));
		Assert.assertEquals("The value returned from the sized range is incorrect.", "Default size", this.rangeSizeGroup.getRangeValue(-5));
		Assert.assertEquals("The value returned from the sized range is incorrect.", "Default size", this.rangeSizeGroup.getRangeValue(254));
	}

	@Test
	public void testSubRangeSize() throws Exception {
		Assert.assertEquals("The basic range's range is an incorrect size", 100.0, this.basicGroup.getRangeSize(), 0);
		Assert.assertEquals("The basic range's subrange is an incorrect size", 100.0, this.basicGroup.getSubRangeSize(), 0);
		Assert.assertEquals("The basic range's subrange list is an incorrect size", 75.0, this.basicGroup.getSubRangeListSize(), 0);
		Assert.assertEquals("The sized range's range is an incorrect size", 150.0, this.rangeSizeGroup.getRangeSize(), 0);
		Assert.assertEquals("The sized range's subrange is an incorrect size", 100.0, this.rangeSizeGroup.getSubRangeSize(), 0);
		Assert.assertEquals("The sized range's subrange list is an incorrect size", 110.0, this.rangeSizeGroup.getSubRangeListSize(), 0);
		Assert.assertEquals("The sub range's range is an incorrect size", 110.0, this.subRangeGroup.getRangeSize(), 0);
		Assert.assertEquals("The sub range's subrange is an incorrect size", 150.0, this.subRangeGroup.getSubRangeSize(), 0);
		Assert.assertEquals("The sub range's subrange list is an incorrect size", 82.5, this.subRangeGroup.getSubRangeListSize(), 0);

		this.basicGroup.setDefaultRangeValue("Basic Default");
		Assert.assertEquals("The default range's size is incorrect", 25.0, this.basicGroup.getDefaultRange().getRangeSize(), 0);
		Assert.assertEquals("The basic range's subrange list is an incorrect size", 100.0, this.basicGroup.getSubRangeListSize(), 0);
		this.basicGroup.setSubRangeSize(125);
		Assert.assertEquals("The default range's size is incorrect", 50.0, this.basicGroup.getDefaultRange().getRangeSize(), 0);
		Assert.assertEquals("The basic range's subrange list is an incorrect size", 125.0, this.basicGroup.getSubRangeListSize(), 0);
		Assert.assertEquals("The returned default range is incorrect.", "Basic Default", this.basicGroup.getRangeValue(121.154));

		this.subRangeGroup.setDefaultRange(new RangeValue<String>("SubRange Default"));
		Assert.assertEquals("The subrange range's size is incorrect", 67.5, this.subRangeGroup.getDefaultRange().getRangeSize(), 0);
		Assert.assertEquals("The subrange range's sublist size is incorrect", 150.0, this.subRangeGroup.getSubRangeListSize(), 0);
		Assert.assertEquals("The returned default range is incorrect.", "SubRange Default", this.subRangeGroup.getRangeValue(117.364));
		this.subRangeGroup.setSubRangeSize(175);
		Assert.assertEquals("The default range's size is incorrect", 92.5, this.subRangeGroup.getDefaultRange().getRangeSize(), 0);
		Assert.assertEquals("The basic range's subrange list is an incorrect size", 175.0, this.subRangeGroup.getSubRangeListSize(), 0);
		Assert.assertEquals("The returned default range is incorrect.", "SubRange Default", this.subRangeGroup.getRangeValue(161.843));

		Assert.assertNull("The default range should not exist", this.rangeSizeGroup.getDefaultRange());
		Assert.assertEquals("The range size group's subrange list is an incorrect size", 110.0, this.rangeSizeGroup.getSubRangeListSize(), 0);
		this.rangeSizeGroup.setSubRangeSize(175);
		Assert.assertNull("The default range should not exist", this.rangeSizeGroup.getDefaultRange());
		Assert.assertEquals("The range size group's subrange list is an incorrect size", 110.0, this.rangeSizeGroup.getSubRangeListSize(), 0);
		Assert.assertNull("The returned range should be null.", this.rangeSizeGroup.getRangeValue(161.154));

	}

	@Test
	public void testToString() throws Exception {
		final String basicString = "Range Group: Size = 100.0 [0.0 -> 100.0]\n" +
			"\tRange Value: Value = 40 basic, Size = 40.0 [0.0 -> 40.0]\n" +
			"\tRange Value: Value = 20 basic, Size = 20.0 [40.0 -> 60.0]\n" +
			"\tRange Value: Value = 15 basic, Size = 15.0 [60.0 -> 75.0]";
		Assert.assertEquals("The basic string output is not correct.", basicString, this.basicGroup.toString());

		final String basicDefaultString = "Range Group: Size = 100.0 [0.0 -> 100.0]\n" +
			"\tRange Value: Value = 22 basicD, Size = 22.0 [0.0 -> 22.0]\n" +
			"\tRange Value: Value = 16 basicD, Size = 16.0 [22.0 -> 38.0]\n" +
			"\tRange Value: Value = 42 basicD, Size = 42.0 [38.0 -> 80.0]\n" +
			"\tRange Value: Value = Default basicD, Size = 20.0 [80.0 -> 100.0] (Default)";
		Assert.assertEquals("The basic w/ default string output is not correct.", basicDefaultString, this.basicDefaultGroup.toString());

		final String multiLevelString = "Range Group: Size = 75.0 [0.0 -> 75.0]\n" +
			"\tRange Value: Value = 25 multiLevel, Size = 25.0 [0.0 -> 25.0]\n" +
			"\tRange Value: Value = 30 multiLevel, Size = 30.0 [25.0 -> 55.0]\n" +
			"\tRange Group: Size = 50.0 [55.0 -> 105.0]\n" +
			"\t\tRange Value: Value = Inner 20, Size = 20.0 [0.0 -> 20.0]\n" +
			"\t\tRange Value: Value = Inner 35, Size = 35.0 [20.0 -> 55.0]\n" +
			"\t\tRange Value: Value = Inner 15, Size = 15.0 [55.0 -> 70.0]";
		Assert.assertEquals("The multi-level string output is not correct.", multiLevelString, this.multiLevelGroup.toString());
	}
}
