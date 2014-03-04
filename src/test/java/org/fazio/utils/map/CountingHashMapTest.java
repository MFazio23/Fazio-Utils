package org.fazio.utils.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Fazio
 * @since 11/20/12 8:34 PM
 */
public class CountingHashMapTest {

	private CountingMap<String, Integer> map;

	@Before
	public void setUp() throws Exception {
		this.map = new CountingHashMap<String, Integer>(){{
			this.put("Lakers", 8);
			this.put("Celtics", 13);
			this.put("Magic", 7);
			this.put("76ers", 0);
			this.put("Bucks", 2);
			this.put("Pistons", 4);
			this.put("Bulls", 5);
		}};
	}

	@Test
	public void testCountingMap() throws Exception {
		Assert.assertEquals("The map's size is incorrect", 7, this.map.size());
		Assert.assertTrue("The map does not contain \"Bucks\"", this.map.containsKey("Bucks"));
		Assert.assertEquals("The value \"Bucks\" has an incorrect count", 2, this.map.get("Bucks"));
	}

	@Test
	public void testIncreaseCount() throws Exception {
		final String key = "Celtics";
		Assert.assertEquals("The count is incorrect.", 13, this.map.get(key).intValue());
		this.map.increaseCount(key);
		Assert.assertEquals("The count is incorrect.", 14, this.map.get(key).intValue());
		this.map.increaseCount(key, 4);
		Assert.assertEquals("The count is incorrect.", 18, this.map.get(key).intValue());
		final String newKey = "NewItem";
		this.map.increaseCount(newKey, 8);
		Assert.assertEquals("The count is incorrect.", 8, this.map.get(newKey).intValue());
	}

	@Test
	public void testDecreaseCount() throws Exception {
		final String key = "Lakers";
		Assert.assertEquals("The count is incorrect.", 8, this.map.get(key).intValue());
		this.map.decreaseCount(key);
		Assert.assertEquals("The count is incorrect.", 7, this.map.get(key).intValue());
		this.map.decreaseCount(key, 3);
		Assert.assertEquals("The count is incorrect.", 4, this.map.get(key).intValue());
	}

	@Test
	public void testPut() throws Exception {
		final String key = "Warriors";
		Assert.assertFalse("The map contains \"" + key + "\" and should not.", this.map.containsKey(key));
		this.map.put(key, 12);
		Assert.assertEquals("The number of items in the map is incorrect.", 8, this.map.size());
		Assert.assertEquals("The value for \"" + key + "\" is incorrect.", 12, this.map.get(key).intValue());

		final String key2 = "Heat";
		Assert.assertFalse("The map contains \"" + key2 + "\" and should not.", this.map.containsKey(key2));
		this.map.put(key2);
		Assert.assertEquals("The number of items in the map is incorrect.", 9, this.map.size());
		Assert.assertEquals("The value for \"" + key2 + "\" is incorrect.", 0, this.map.get(key2).intValue());
	}

	@Test
	public void testPutAll() throws Exception {
		final List<String> keys = new ArrayList<String>() {{
			this.add("Spurs");
			this.add("Hawks");
			this.add("Thunder");
		}};

		for(String key : keys) Assert.assertFalse("The map contains \"" + key + "\" and should not.", this.map.containsKey(key));
		this.map.putAll(keys);
		Assert.assertEquals("The number of items in the map is incorrect.", 10, this.map.size());
		for(String key : keys) Assert.assertEquals("The value for \"" + key + "\" is incorrect.", 0, this.map.get(key).intValue());
	}

	@Test
	public void testReset() throws Exception {
		int total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		Assert.assertEquals("The total value of items in the map is incorrect.", 39, total);
		Assert.assertEquals("The value for \"Celtics\" is incorrect.", 13, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Bucks\" is incorrect.", 2, this.map.get("Bucks"));
		Assert.assertEquals("The value for \"Pistons\" is incorrect.", 4, this.map.get("Pistons"));
		Assert.assertEquals("The value for \"Bulls\" is incorrect.", 5, this.map.get("Bulls"));

		this.map.reset();

		total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		Assert.assertEquals("The total value of items in the map is incorrect.", 0, total);
		Assert.assertEquals("The value for \"Celtics\" is incorrect.", 0, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Bucks\" is incorrect.", 0, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Pistons\" is incorrect.", 0, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Bulls\" is incorrect.", 0, this.map.get("Celtics"));
	}

	@Test
	public void testSetAll() throws Exception {
		int total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		Assert.assertEquals("The total value of items in the map is incorrect.", 39, total);
		Assert.assertEquals("The value for \"Celtics\" is incorrect.", 13, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Bucks\" is incorrect.", 2, this.map.get("Bucks"));
		Assert.assertEquals("The value for \"Pistons\" is incorrect.", 4, this.map.get("Pistons"));
		Assert.assertEquals("The value for \"Bulls\" is incorrect.", 5, this.map.get("Bulls"));

		this.map.setAll(10);

		total = 0;
		for(Number value : this.map.values()) {
			total += value.intValue();
		}
		Assert.assertEquals("The total value of items in the map is incorrect.", 70, total);
		Assert.assertEquals("The value for \"Celtics\" is incorrect.", 10, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Bucks\" is incorrect.", 10, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Pistons\" is incorrect.", 10, this.map.get("Celtics"));
		Assert.assertEquals("The value for \"Bulls\" is incorrect.", 10, this.map.get("Celtics"));
	}

	@Test
	public void testSortByKey() throws Exception {
		final String[] sortedKeys = {"76ers", "Bucks", "Bulls", "Celtics", "Lakers", "Magic", "Pistons"};

		this.map.sortByKeys();

		List<String> keys = new ArrayList<String>(this.map.keySet());
		for(int x=0;x<this.map.size();x++) {
			Assert.assertEquals("The value in the sorted map is not what was expected.", sortedKeys[x], keys.get(x));
		}

		this.map.sortByKeys(false);

		keys = new ArrayList<String>(this.map.keySet());
		for(int x=0;x<this.map.size();x++) {
			Assert.assertEquals("The value in the sorted map is not what was expected.", sortedKeys[x], keys.get(this.map.size() - 1 - x));
		}
	}

	@Test
	public void testSortByCounts() throws Exception {
		final String[] sortedKeys = {"76ers", "Bucks", "Pistons", "Bulls", "Magic", "Lakers", "Celtics"};

		this.map.sortByCounts();

		List<String> keys = new ArrayList<String>(this.map.keySet());
		for(int x=0;x<this.map.size();x++) {
			Assert.assertEquals("The value in the sorted map is not what was expected.", sortedKeys[x], keys.get(x));
		}

		final String[] updatedKeys = {"76ers", "Bucks", "Pistons", "Bulls", "MagicThunder", "MagicThunder", "Lakers", "Celtics"};
		this.map.put("Thunder", 7);
		this.map.sortByCounts();

		keys = new ArrayList<String>(this.map.keySet());
		for(int x=0;x<this.map.size();x++) {
			if(keys.get(x).equals("Magic") || keys.get(x).equals("Thunder"))
				Assert.assertEquals("The value in the sorted map is not what was expected.", updatedKeys[x], "MagicThunder");
			else
				Assert.assertEquals("The value in the sorted map is not what was expected.", updatedKeys[x], keys.get(x));
		}
	}

	@Test
	public void testSortByCountsHighToLow() throws Exception {
		final String[] sortedKeys = {"76ers", "Bucks", "Pistons", "Bulls", "Magic", "Lakers", "Celtics"};

		this.map.sortByCounts(false);

		List<String> keys = new ArrayList<String>(this.map.keySet());
		for(int x=0;x<this.map.size();x++) {
			Assert.assertEquals("The value in the sorted map is not what was expected.", sortedKeys[x], keys.get(this.map.size() - 1 - x));
		}
	}

	@Test
	public void testToString() throws Exception {
		final StringBuilder sb = new StringBuilder()
			.append("Lakers = 8\n")
			.append("Celtics = 13\n")
			.append("Magic = 7\n")
			.append("76ers = 0\n")
			.append("Bucks = 2\n")
			.append("Pistons = 4\n")
			.append("Bulls = 5\n");
		
		Assert.assertEquals("The returned string is incorrect.", sb.toString(), this.map.toString());
		
		this.map.increaseCount("Raptors");

		sb.append("Raptors = 1\n");
		
		Assert.assertEquals("The returned string is incorrect.", sb.toString(), this.map.toString());

		this.map.increaseCount("Hawks", 3);

		sb.append("Hawks = 3\n");
		
		Assert.assertEquals("The returned string is incorrect.", sb.toString(), this.map.toString());
	}
}
