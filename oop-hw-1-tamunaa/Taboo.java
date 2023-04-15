
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */

	private Map<T, List<T>> rulesMap; //noFollows
	public Taboo(List<T> rules) {
		rulesMap = new HashMap<>();

		fillRulesMap(rules);
	}

	private void fillRulesMap(List<T> rules){
		for (int i = 0; i < rules.size()-1; i++){
			T curElem = rules.get(i);
			if (curElem == null)continue;
			T nextElem = rules.get(i+1);

			if (!rulesMap.containsKey(curElem)){
				List<T> newList = new ArrayList<>();
				newList.add(nextElem);
				rulesMap.put(curElem, newList);
			}else{
				rulesMap.get(curElem).add(nextElem);
			}
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		return new HashSet<>(rulesMap.get(elem));
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		if (list.size() == 0)return;

		List<T> newList = new ArrayList<>();
		newList.add(list.get(0));

		for (int i = 1; i < list.size(); i ++){
			T curElem = list.get(i);
			T lastElem = newList.get(newList.size() - 1);

			if (!rulesMap.get(lastElem).contains(curElem)){
				newList.add(curElem);
			}
		}
		list.clear();
		list.addAll(newList);
	}
}
