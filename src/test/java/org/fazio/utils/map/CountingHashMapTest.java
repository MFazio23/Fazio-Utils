package org.fazio.utils.map;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Michael Fazio <michael.fazio@kohls.com>
 * @since 11/20/12 8:34 PM
 */
public class CountingHashMapTest {

	private CountingMap<String, Integer> map;

	@Before
	public void setUp() throws Exception {
		this.map = new CountingHashMap<String, Integer>(){{
			this.put("Lakers", 8);
			this.put("Celtics", 13);
			this.put("Magic", 8);
			this.put("76ers", 0);
			this.put("Bucks", 2);
			this.put("Pistons", 4);
			this.put("Bulls", 8);
		}};
	}

	@Test
	public void testCountingMap() throws Exception {
		assertEquals("The map's size is incorrect", 7, this.map.size());
		assertTrue("The map does not contain \"Bucks\"", this.map.containsKey("Bucks"));
		assertEquals("The value \"Bucks\" has an incorrect count", 2, this.map.get("Bucks"));
	}

	@Test
	public void testIncreaseCount() throws Exception {
		final String key = "Celtics";
		assertEquals("The count is incorrect.", 13, this.map.get(key).intValue());
		this.map.increaseCount(key);
		assertEquals("The count is incorrect.", 14, this.map.get(key).intValue());
		this.map.increaseCount(key, 4);
		assertEquals("The count is incorrect.", 18, this.map.get(key).intValue());
	}

	@Test
	public void testDecreaseCount() throws Exception {
		final String key = "Lakers";
		assertEquals("The count is incorrect.", 8, this.map.get(key).intValue());
		this.map.decreaseCount(key);
		assertEquals("The count is incorrect.", 7, this.map.get(key).intValue());
		this.map.decreaseCount(key, 3);
		assertEquals("The count is incorrect.", 4, this.map.get(key).intValue());
	}

	@Test
	public void testPut() throws Exception {
		final String key = "Warriors";
		assertFalse("The map contains \"" + key + "\" and should not.", this.map.containsKey(key));
		this.map.put(key, 12);
		assertEquals("The number of items in the map is incorrect.", 8, this.map.size());
		assertEquals("The value for \"" + key + "\" is incorrect.", 12, this.map.get(key).intValue());

		final String key2 = "Heat";
		assertFalse("The map contains \"" + key2 + "\" and should not.", this.map.containsKey(key2));
		this.map.put(key2);
		assertEquals("The number of items in the map is incorrect.", 9, this.map.size());
		assertEquals("The value for \"" + key2 + "\" is incorrect.", 0, this.map.get(key2).intValue());
	}

	@Test
	public void testReset() throws Exception {
		int total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		assertEquals("The total value of items in the map is incorrect.", 43, total);
		assertEquals("The value for \"Celtics\" is incorrect.", 13, this.map.get("Celtics"));
		assertEquals("The value for \"Bucks\" is incorrect.", 2, this.map.get("Bucks"));
		assertEquals("The value for \"Pistons\" is incorrect.", 4, this.map.get("Pistons"));
		assertEquals("The value for \"Bulls\" is incorrect.", 8, this.map.get("Bulls"));

		this.map.reset();

		total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		assertEquals("The total value of items in the map is incorrect.", 0, total);
		assertEquals("The value for \"Celtics\" is incorrect.", 0, this.map.get("Celtics"));
		assertEquals("The value for \"Bucks\" is incorrect.", 0, this.map.get("Celtics"));
		assertEquals("The value for \"Pistons\" is incorrect.", 0, this.map.get("Celtics"));
		assertEquals("The value for \"Bulls\" is incorrect.", 0, this.map.get("Celtics"));
	}

	@Test
	public void testSetAll() throws Exception {
		int total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		assertEquals("The total value of items in the map is incorrect.", 43, total);
		assertEquals("The value for \"Celtics\" is incorrect.", 13, this.map.get("Celtics"));
		assertEquals("The value for \"Bucks\" is incorrect.", 2, this.map.get("Bucks"));
		assertEquals("The value for \"Pistons\" is incorrect.", 4, this.map.get("Pistons"));
		assertEquals("The value for \"Bulls\" is incorrect.", 8, this.map.get("Bulls"));

		this.map.setAll(10);

		total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		assertEquals("The total value of items in the map is incorrect.", 70, total);
		assertEquals("The value for \"Celtics\" is incorrect.", 10, this.map.get("Celtics"));
		assertEquals("The value for \"Bucks\" is incorrect.", 10, this.map.get("Celtics"));
		assertEquals("The value for \"Pistons\" is incorrect.", 10, this.map.get("Celtics"));
		assertEquals("The value for \"Bulls\" is incorrect.", 10, this.map.get("Celtics"));
	}
}
