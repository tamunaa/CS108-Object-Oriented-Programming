// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import junit.framework.TestCase;

public class StringCodeTest extends TestCase {
	//
	// blowup
	//
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
		assertEquals("xxZZ", StringCode.blowup("1x1Z"));
		assertEquals("xxxabcZZZZ", StringCode.blowup("2xabc3Z"));
		assertEquals("xxZZ", StringCode.blowup("1x1Z"));
		assertEquals("tttt", StringCode.blowup("3t"));
	}
	
	public void testBlowup2() {
		// things with digits
		
		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		assertEquals("helloooooo", StringCode.blowup("hell5o5"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		assertEquals("2", StringCode.blowup("12"));
		
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}
	
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		assertEquals("", StringCode.blowup("1"));
		
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}
	
	
	//
	// maxRun
	//
	public void testRun1() {
		assertEquals(1, StringCode.maxRun("hzozozpzlza"));
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
		assertEquals(4, StringCode.maxRun("hoopplllla"));
		assertEquals(5, StringCode.maxRun("hhhoopppppllla"));
	}
	
	public void testRun2() {
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
		assertEquals(4, StringCode.maxRun("hhhooppoooo"));
		assertEquals(5, StringCode.maxRun("hhhhhooppppoooo"));
	}
	
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
		assertEquals(4, StringCode.maxRun("11223333"));
		assertEquals(5, StringCode.maxRun("111112233"));
	}

	// Need test cases for stringIntersect

	public void	testStringIntersect0() {
		assertEquals(true, StringCode.stringIntersect("a", "abb", 1));
		assertEquals(false, StringCode.stringIntersect("aaa", "bbb", 3));
		assertEquals(true, StringCode.stringIntersect("aaa", "aab", 2));
		assertEquals(false, StringCode.stringIntersect("a", "aab", 2));
		assertEquals(false, StringCode.stringIntersect("", "bbb", 1));
		assertEquals(true, StringCode.stringIntersect("abaa", "bbb", 1));
		assertEquals(true, StringCode.stringIntersect("abac", "aba", 3));
	}

	public void	testStringIntersectEdgeCases() {
		assertEquals(false, StringCode.stringIntersect("", "", 3));
		assertEquals(false, StringCode.stringIntersect("", "b", 1));
		assertEquals(false, StringCode.stringIntersect("c", "aab", 2));
		assertEquals(true, StringCode.stringIntersect("abac", "aba", 3));
		assertEquals(false, StringCode.stringIntersect("a", "m", 2));
		assertEquals(false, StringCode.stringIntersect("a", "abb", 2));
		assertEquals(false, StringCode.stringIntersect("vvv", "bbb", 1));
	}

	public void	testStringIntersectLen10() {
		assertEquals(true, StringCode.stringIntersect("bbabcbbaab", "cbcbcbaabb", 4));
		assertEquals(false, StringCode.stringIntersect("caacccbbbc", "aaacabbbba", 5));
		assertEquals(false, StringCode.stringIntersect("accbcccacb", "bccaccbbaa", 5));
		assertEquals(true, StringCode.stringIntersect("accbabcaaa", "acaccacbaa", 1));
		assertEquals(true, StringCode.stringIntersect("caaccbaaca", "bbbaacabcb", 2));
		assertEquals(false, StringCode.stringIntersect("accaabcbcc", "bacbcbaaba", 5));
		assertEquals(false, StringCode.stringIntersect("acababcaac", "bcccbaccac", 4));
		assertEquals(false, StringCode.stringIntersect("abcbaaccbc", "abccbccbcb", 7));
		assertEquals(true, StringCode.stringIntersect("cbabccaacb", "abbccbccab", 1));
		assertEquals(true, StringCode.stringIntersect("bcbabccaaa", "acbbbcccbb", 3));
	}

	public void testStringIntersectLen20(){
		assertEquals(true, StringCode.stringIntersect("bedbfbeadbcbcbfeaaee", "fcddcccbbbfaaafdbebe", 2));
		assertEquals(false, StringCode.stringIntersect("ddfcbccfafebccdccebd", "dcdcfedbfadadcdccdcb", 11));
		assertEquals(false, StringCode.stringIntersect("accadfcbaacabeeaafde", "cefdfcdaecbccbdfefed", 7));
		assertEquals(false, StringCode.stringIntersect("bdfdfdbdefdafeffcedc", "fdfaaefedafcbcdbffec", 4));
		assertEquals(false, StringCode.stringIntersect("bcbeceaefcddfbdeeffe", "ffabcbfbdbccddadcebb", 8));
		assertEquals(false, StringCode.stringIntersect("cceeeaaddfacdafcfefe", "affcedcacbddecfcafde", 3));
		assertEquals(true, StringCode.stringIntersect("eefedbbaefaceaafceac", "cdacaacfdecfdfdebced", 2));
		assertEquals(false, StringCode.stringIntersect("ffeddbacbcccaecaaabd", "abafceeddccafadbabbf", 4));
		assertEquals(false, StringCode.stringIntersect("dcbbcffafcaeaeefcbac", "eaccbdcbeccfccacfdde", 12));
		assertEquals(false, StringCode.stringIntersect("bceffdcabeefebbfbcbb", "cadeeeccbaaabeaaaaab", 5));
	}

	public void testDiffLen(){
		assertEquals(true, StringCode.stringIntersect("bca", "abceb", 2));
		assertEquals(false, StringCode.stringIntersect("ae", "babcb", 3));
		assertEquals(false, StringCode.stringIntersect("cec", "cd", 3));
		assertEquals(false, StringCode.stringIntersect("", "ccdec", 1));
		assertEquals(true, StringCode.stringIntersect("b", "edbe", 0));
		assertEquals(true, StringCode.stringIntersect("de", "adbba", 0));
		assertEquals(true, StringCode.stringIntersect("cab", "a", 1));
		assertEquals(false, StringCode.stringIntersect("cbab", "dce", 3));
		assertEquals(false, StringCode.stringIntersect("ae", "eed", 2));
		assertEquals(false, StringCode.stringIntersect("db", "de", 3));

		assertEquals(false, StringCode.stringIntersect("bca", "abcebcca", 3));
		assertEquals(false, StringCode.stringIntersect("bab", "bbdceca", 2));
		assertEquals(true, StringCode.stringIntersect("cae", "cdecdbbcedbe", 0));
		assertEquals(true, StringCode.stringIntersect("deaadbb", "bdcab", 1));
		assertEquals(true, StringCode.stringIntersect("aecba", "e", 1));
		assertEquals(true, StringCode.stringIntersect("eacaece", "dacdbddedbeeca", 0));
		assertEquals(true, StringCode.stringIntersect("ecbbeeaa", "dcbcccccbbabba", 1));
		assertEquals(true, StringCode.stringIntersect("eaeb", "cbbaecc", 0));
		assertEquals(true, StringCode.stringIntersect("dbabdee", "bcedeaddaaebd", 2));
		assertEquals(false, StringCode.stringIntersect("bbbae", "edbadaeeeecbd", 3));
	}
}
