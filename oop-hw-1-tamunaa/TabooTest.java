// TabooTest.java
// Taboo class tests -- nothing provided.

import junit.framework.TestCase;

import java.util.*;

public class TabooTest extends TestCase {
    public void test0() {
        List<Character> ls = Arrays.asList('a', 'b', 'c', 'a', 'd');
        Taboo<Character> t = new Taboo<>(ls);
        Set<Character> res = new HashSet<>();
        res.add('b');
        res.add('d');
        assertEquals(res, t.noFollow('a'));

        res.clear();
        res.add('c');
        assertEquals(res, t.noFollow('b'));

        res.clear();
        res.add('a');
        assertEquals(res, t.noFollow('c'));
    }

    public void test1() {
        List<Character> ls = Arrays.asList('a', 'b', 'c', 'a', 'd');
        Taboo<Character> t = new Taboo<>(ls);

        List<Character> toReduce = new ArrayList<>();
        toReduce.add('a');
        toReduce.add('b');
        toReduce.add('c');
        t.reduce(toReduce);

        List<Character> result = new ArrayList<>();
        result.add('a');
        result.add('c');
        assertTrue(result.equals(toReduce));
    }

    public void test2() {
        List<Character> ls = Arrays.asList('a', 'b', 'c', 'a', 'd');
        Taboo<Character> t = new Taboo<>(ls);

        List<Character> toReduce = new ArrayList<>();
        toReduce.add('a');
        toReduce.add('c');
        toReduce.add('a');
        toReduce.add('d');
        t.reduce(toReduce);

        List<Character> result = new ArrayList<>();
        result.add('a');
        result.add('c');
        result.add('d');
        assertTrue(result.equals(toReduce));
    }
    public void test3() {
        List<Character> ls = Arrays.asList('a', 'b', 'c', 'a', 'd');
        Taboo<Character> t = new Taboo<>(ls);

        List<Character> toReduce = new ArrayList<>();
        t.reduce(toReduce);

        List<Character> result = new ArrayList<>();

        assertTrue(result.equals(toReduce));
    }
}
