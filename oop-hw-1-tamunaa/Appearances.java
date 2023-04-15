import java.util.Collection;
import java.util.HashSet;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		Collection<T> st = new HashSet<>(a);
		return st.stream().filter(elem -> (countElem(a, elem) == countElem(b, elem))).toList().size();
	}
	private static <T> int countElem(Collection<T> ls, T elem){
		return ls.stream().filter(lm -> lm.equals(elem)).toList().size();
	}


}
