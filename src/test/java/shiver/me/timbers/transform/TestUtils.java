package shiver.me.timbers.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Utility methods and constants to help with creating tests.
 */
public final class TestUtils {

    private TestUtils() {
    }

    public static <K, V> Map<K, V> mockMap(Iterable<Entry<K, V>> entries) {

        final Map<K, V> transformers = new HashMap<K, V>();

        for (Map.Entry<K, V> entry : entries) {

            transformers.put(entry.getKey(), entry.getValue());
        }

        return transformers;
    }

    public static <T> void assertCorrectIndices(List<T> expected, Container<?, T> actual) {

        int i = 0;
        for (; i < expected.size(); i++) {

            assertThat("element at index \"" + i + "\" should be correct.", expected, hasItem(actual.get(i)));
        }

        assertEquals("the correct number of elements should be found.", expected.size(), i);
    }

    public static void assertIterableEquals(Iterable expected, Iterable actual) {

        assertIteratorEquals(expected.iterator(), actual.iterator());
    }

    public static void assertIteratorEquals(Iterator expected, Iterator actual) {

        final List expectedList = iteratorAsList(expected);
        Collections.sort(expectedList, new HashCodeComparator());

        final List actualList = iteratorAsList(actual);
        Collections.sort(actualList, new HashCodeComparator());

        assertEquals("both iterators should be equal.", expectedList, actualList);
    }

    private static List iteratorAsList(Iterator iterator) {

        final List<Object> list = new ArrayList<Object>();

        while (iterator.hasNext()) {

            list.add(iterator.next());
        }

        return list;
    }

    private static class HashCodeComparator implements Comparator {

        @Override
        public int compare(Object first, Object second) {

            return first.hashCode() - second.hashCode();
        }
    }
}
