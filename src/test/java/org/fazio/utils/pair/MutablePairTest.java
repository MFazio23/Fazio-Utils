package org.fazio.utils.pair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Fazio
 * @since 11/21/12 8:31 PM
 */
public class MutablePairTest {

	public static final String LEFT_VALUE = "LeftValue";
	public static final String EXTRA_LEFT_VALUE = "ExtraLeftValue";
	public static final String RIGHT_VALUE = "RightValue";
	public static final String EXTRA_RIGHT_VALUE = "ExtraRightValue";
	public static final Integer INT_VALUE = 23;

	public static final String STRING_PAIR_TOSTRING = "MutablePair{left=" + LEFT_VALUE + ", right=" + RIGHT_VALUE + "}";
	public static final String EMPTY_STRING_PAIR_TOSTRING = "MutablePair{left=null, right=null}";
	public static final String INTLIST_PAIR_TOSTRING = "MutablePair{left=" + INT_VALUE + ", right=[]}";

	private MutablePair<String, String> stringPair;
	private MutablePair<String, String> emptyStringPair;
	private MutablePair<Integer, List<String>> intListPair;

	@Before
	public void setUp() throws Exception {
		this.stringPair = new MutablePair<String, String>(LEFT_VALUE, RIGHT_VALUE);
		this.emptyStringPair = new MutablePair<String, String>();
		this.intListPair = new MutablePair<Integer, List<String>>(INT_VALUE, new ArrayList<String>());
	}

	@Test
	public void testGet() throws Exception {
		Assert.assertEquals("The left String value does not match", LEFT_VALUE, this.stringPair.getLeft());
		Assert.assertEquals("The right String value does not match", RIGHT_VALUE, this.stringPair.getRight());
		Assert.assertEquals("The left Integer value does not match", INT_VALUE, this.intListPair.getLeft());
		Assert.assertEquals("The returned value is not correct", ArrayList.class, this.intListPair.getRight().getClass());
	}

	@Test
	public void testSet() throws Exception {
		Assert.assertNull("The left String value is not null, and should be.", this.emptyStringPair.getLeft());
		Assert.assertNull("The right String value is not null, and should be.", this.emptyStringPair.getRight());

		this.emptyStringPair.setLeft(LEFT_VALUE);
		this.emptyStringPair.setRight(RIGHT_VALUE);

		Assert.assertEquals("The left String value does not match", LEFT_VALUE, this.emptyStringPair.getLeft());
		Assert.assertEquals("The right String value does not match", RIGHT_VALUE, this.emptyStringPair.getRight());

		this.emptyStringPair.setValues(EXTRA_LEFT_VALUE, EXTRA_RIGHT_VALUE);

		Assert.assertEquals("The left String value does not match", EXTRA_LEFT_VALUE, this.emptyStringPair.getLeft());
		Assert.assertEquals("The right String value does not match", EXTRA_RIGHT_VALUE, this.emptyStringPair.getRight());
	}

	@Test
	public void testContains() throws Exception {
		Assert.assertTrue("The default left String value is not found in the pair", this.stringPair.contains(LEFT_VALUE));
		Assert.assertTrue("The default right String value is not found in the pair", this.stringPair.contains(RIGHT_VALUE));
		Assert.assertFalse("An incorrect value was found in the pair", this.stringPair.contains("BLARHGH"));
		Assert.assertFalse("An incorrect value was found in the pair", this.stringPair.contains(INT_VALUE));
		Assert.assertTrue("The default Integer value was not found in the pair", this.intListPair.contains(INT_VALUE));
		Assert.assertTrue("An ArrayList instance was not found in the pair", this.intListPair.contains(new ArrayList<String>()));
		Assert.assertFalse(
			"The incorrect ArrayList instance was found in the pair",
			this.intListPair.contains(new ArrayList<String>(){{add("Testing");}}));
	}

	@Test
	public void testToString() {
		Assert.assertEquals("The toString value from the MutablePair is incorrect.", STRING_PAIR_TOSTRING, this.stringPair.toString());
		Assert.assertEquals("The toString value from the MutablePair is incorrect.", EMPTY_STRING_PAIR_TOSTRING, this.emptyStringPair.toString());
		Assert.assertEquals("The toString value from the MutablePair is incorrect.", INTLIST_PAIR_TOSTRING, this.intListPair.toString());
	}
}
