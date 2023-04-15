// CS108 HW1 -- String static methods


import java.util.*;

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if (str.length() == 0)return 0;
		int res = 1;

		char prev = '\n';
		int cur = 1;

		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) == prev){
				cur++;
			}else {
				cur = 1;
			}
			res = Math.max(res, cur);
			prev = str.charAt(i);
		}

		return res;
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		StringBuilder result = new StringBuilder();

		int ind = 0;
		char prev = '\n';

		while (ind < str.length()){
			char c = str.charAt(ind);

			if (Character.isDigit(prev)){
				int repeat = prev - '0';
				result.append(String.valueOf(c).repeat(Math.max(0, repeat)));
			}

			if (!Character.isDigit(c)) {
				result.append(c);
			}

			prev = c;
			ind++;
		}

		return result.toString();
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */

	private static final long systemCode =26;
	private static final long BIG_PRIME = 1000000009;

	private static long countHashValue(String str, int ind, int len, long prevVal){
		int indOfFirst = ind - len;
		int charVal = str.charAt(indOfFirst);

		long toSub = ((long) Math.pow(systemCode, len - 1) * charVal) % BIG_PRIME;
		prevVal -= toSub;
		if (prevVal < 0)prevVal += BIG_PRIME;

		prevVal *= systemCode;
		prevVal += (int)str.charAt(ind);
		prevVal %= BIG_PRIME;

		return prevVal;
	}

	public static boolean stringIntersect(String a, String b, int len) {
		HashSet<Long> st = new HashSet<>();
		if (a.length() < len || b.length() < len)return false;

		long hashValue = 0;

		for (int i = 0; i < len; i++){
			int charIntValue = (int)a.charAt(i);
			hashValue *= systemCode;
			hashValue += charIntValue;
			hashValue %= BIG_PRIME;
		}

		st.add(hashValue);

		for (int i = len; i < a.length(); i++){
			hashValue = countHashValue(a, i, len, hashValue);
			st.add(hashValue);
		}

		hashValue = 0;
		for (int i = 0; i < len; i++){
			int charIntValue = b.charAt(i);
			hashValue *= systemCode;
			hashValue += charIntValue;
			hashValue %= BIG_PRIME;
		}

		if (st.contains(hashValue))return true;
		for (int i = len; i < b.length(); i++){
			hashValue = countHashValue(b, i, len, hashValue);
			if(st.contains(hashValue))return true;
		}

		return false;
	}

}
