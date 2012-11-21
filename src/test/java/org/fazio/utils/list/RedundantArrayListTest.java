package org.fazio.utils.list;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/20/12 9:31 PM
 */
public class RedundantArrayListTest {

	private RedundantList<String> list;

	@Before
	public void setUp() throws Exception {
		this.list = new RedundantArrayList<String>(){{
			add("Lakers");
			add("Nets");
			add("Heat");
			add("Lakers");
			add("Heat");
			add("Nets");
			add("76ers");
			add("Mavericks");
			add("Spurs");
			add("Lakers");
			add("Spurs");
		}};
	}

	@Test
	public void testRemoveEachObj() throws Exception {
		assertEquals(
			"There is an incorrect number of items in the list",
			11,
			this.list.size());

		this.list.removeEach("Nets");

		assertEquals(
			"There is an incorrect number of items in the list",
			9,
			this.list.size());

		this.list.removeEach("Lakers");

		assertEquals(
			"There is an incorrect number of items in the list",
			6,
			this.list.size());
	}

	@Test
	public void testRemoveEachIndex() throws Exception {
		assertEquals(
			"There is an incorrect number of items in the list",
			11,
			this.list.size());

		this.list.removeEach(this.list.indexOf("Lakers"));

		assertEquals(
			"There is an incorrect number of items in the list",
			8,
			this.list.size());
	}
}
