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
public class PairTest {

	public static final String LEFT_VALUE = "LeftValue";
	public static final String RIGHT_VALUE = "RightValue";
	public static final Integer INT_VALUE = 23;

	public static final String STRING_PAIR_TOSTRING = "Pair{left=" + LEFT_VALUE + ", right=" + RIGHT_VALUE + "}";
	public static final String INTLIST_PAIR_TOSTRING = "Pair{left=" + INT_VALUE + ", right=[]}";

	private Pair<String, String> stringPair;
	private Pair<Integer, List<String>> intListPair;



	@Before
	public void setUp() throws Exception {
		this.stringPair = new Pair<String, String>(LEFT_VALUE, RIGHT_VALUE);
		this.intListPair = new Pair<Integer, List<String>>(INT_VALUE, new ArrayList<String>());
	}

	@Test
	public void testGet() throws Exception {
		Assert.assertEquals("The left String value does not match", LEFT_VALUE, this.stringPair.getLeft());
		Assert.assertEquals("The right String value does not match", RIGHT_VALUE, this.stringPair.getRight());
		Assert.assertEquals("The left Integer value does not match", INT_VALUE, this.intListPair.getLeft());
		Assert.assertEquals("The returned value is not correct", ArrayList.class, this.intListPair.getRight().getClass());
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
		Assert.assertEquals("The toString value from the Pair is incorrect.", STRING_PAIR_TOSTRING, this.stringPair.toString());
		Assert.assertEquals("The toString value from the Pair is incorrect.", INTLIST_PAIR_TOSTRING, this.intListPair.toString());
	}
}
