import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppearancesTest extends TestCase {
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
		}
		return list;
	}
	
	public void testSameCount1() {
		List<String> a = stringToList("abccv");
		List<String> b = stringToList("abc");
		assertEquals(2, Appearances.sameCount(a, b));
	}
	
	public void testSameCount2() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
	}

	public void testSameCount3() {
		List<Integer> a = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3)));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 2, 2, 3, 3, 1, 1)));
		assertEquals(4, Appearances.sameCount(a, Arrays.asList(1, 2, 3, 4)));
		assertEquals(5, Appearances.sameCount(a, Arrays.asList(1, 3, 4, 2, 7)));
	}

	public void testSameCount4() {
		List<Integer> a = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 2, 2)));
		assertEquals(3, Appearances.sameCount(a, Arrays.asList(2, 2, 3, 3, 3, 1)));
		assertEquals(4, Appearances.sameCount(a, Arrays.asList(1, 2, 2, 4, 4, 4, 4, 3, 3, 3)));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 1, 2, 2, 2)));
	}
}
