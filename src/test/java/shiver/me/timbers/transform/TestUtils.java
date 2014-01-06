package shiver.me.timbers.transform;

import shiver.me.timbers.transform.iterable.IterableTransformations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import static java.util.Collections.unmodifiableList;
import static java.util.Map.Entry;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;

/**
 * Utility methods and constants to help with creating tests.
 *
 * @author Karl Bennett
 */
public final class TestUtils {

    private TestUtils() {
    }

    public static final Transformations<Transformation> EMPTY_TRANSFORMATIONS =
            new IterableTransformations<Transformation>(NULL_TRANSFORMATION);

    public static final String ONE = "one";
    public static final String TWO = "two";
    public static final String THREE = "three";
    public static final String FOUR = "four";
    public static final String FIVE = "five";
    public static final String SIX = "six";
    public static final String SEVEN = "seven";
    public static final String EIGHT = "eight";
    public static final String NINE = "nine";
    public static final String TEN = "ten";

    public static final List<String> NAMES = unmodifiableList(
            asList(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN));

    public static <T> Iterable<T> mockIterable(T... elements) {

        return mockIterable(asList(elements));
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> mockIterable(List<T> elements) {

        Iterator<T> iterator = mock(Iterator.class);

        if (0 >= elements.size()) {

            when(iterator.hasNext()).thenReturn(false);

        } else {

            final Boolean[] returns = new Boolean[elements.size()];
            fill(returns, true);
            returns[returns.length - 1] = false;

            when(iterator.hasNext()).thenReturn(true, returns);
        }

        when(iterator.next()).thenReturn(elements.get(0),
                elements.subList(1, elements.size()).toArray((T[]) new Object[elements.size() - 1]));

        final Iterable<T> transformationIterable = mock(Iterable.class);
        when(transformationIterable.iterator()).thenReturn(iterator);

        return transformationIterable;
    }

    public static Map<String, Transformation> mockTransformationMap() {

        return mockTransformationMap(NAMES);
    }

    public static Map<String, Transformation> mockTransformationMap(Collection<String> names) {

        final Map<String, Transformation> transformationMap = new HashMap<String, Transformation>(names.size());

        for (Transformation transformation : mockTransformationList(names)) {

            transformationMap.put(transformation.getName(), transformation);
        }

        return transformationMap;
    }

    public static List<Transformation> mockTransformationList() {

        return mockTransformationList(NAMES);
    }

    public static List<Transformation> mockTransformationList(Collection<String> names) {

        final List<Transformation> transformations = new ArrayList<Transformation>(names.size());

        Transformation transformation;
        for (String name : names) {

            transformation = mock(Transformation.class);
            when(transformation.getName()).thenReturn(name);

            transformations.add(transformation);
        }

        return transformations;
    }

    public static <I, T extends Transformation> Map<String, Transformer<I, T>> mockTransformerMap() {

        return mockTransformerMap(NAMES);
    }

    @SuppressWarnings("unchecked")
    public static <I, T extends Transformation> Map<String, Transformer<I, T>> mockTransformerMap(
            Collection<String> names) {

        final Iterator<String> iterator = names.iterator();

        return mockMap(new TransformerMapIterable(iterator));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> mockNameMap() {

        int index = 0;

        return mockMap(Arrays.<Entry<String, String>>asList(
                new SimpleEntry<String, String>(NAMES.get(index++), "1"),
                new SimpleEntry<String, String>(NAMES.get(index++), "2"),
                new SimpleEntry<String, String>(NAMES.get(index++), "3"),
                new SimpleEntry<String, String>(NAMES.get(index++), "4"),
                new SimpleEntry<String, String>(NAMES.get(index++), "5"),
                new SimpleEntry<String, String>(NAMES.get(index++), "6"),
                new SimpleEntry<String, String>(NAMES.get(index++), "7"),
                new SimpleEntry<String, String>(NAMES.get(index++), "8"),
                new SimpleEntry<String, String>(NAMES.get(index++), "9"),
                new SimpleEntry<String, String>(NAMES.get(index), "10")
        ));
    }

    public static <K, V> Map<K, V> mockMap(Iterable<Entry<K, V>> entries) {

        final Map<K, V> transformers = new HashMap<K, V>();

        for (Entry<K, V> entry : entries) {

            transformers.put(entry.getKey(), entry.getValue());
        }

        return transformers;
    }

    public static void assertNoIterations(Transformations<Transformation> transformations) {

        for (Transformation transformation : transformations) {

            fail("an empty " + Transformations.class.getSimpleName() + " should not iterate. Transformation: " +
                    transformation.getName());
        }
    }

    public static <T> void assertCorrectIndices(List<T> expected, Container<?, T> actual) {

        int i = 0;
        for (; i < expected.size(); i++) {

            assertThat("element at index \"" + i + "\" should be correct.", expected, hasItem(actual.get(i)));
        }

        assertEquals("the correct number of elements should be found.", expected.size(), i);
    }

    public static void assertCorrectNames(Map<String, ?> expected, Container<String, ?> actual) {

        for (String name : NAMES) {

            assertEquals("element with name " + name + " should be returned correctly.", expected.get(name),
                    actual.get(name));
        }
    }

    public static void assertNullTransformation(Transformations<Transformation> transformations, int index) {

        assertEquals("the null Transformation should be returned for the index " + index, NULL_TRANSFORMATION,
                transformations.get(index));
    }

    public static void assertNullTransformation(Transformations<Transformation> transformations, String name) {

        assertEquals("the null Transformation should be returned for the name " + name, NULL_TRANSFORMATION,
                transformations.get(name));
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

    private static class TransformerMapIterable<I, T extends Transformation>
            implements Iterable<Entry<String, Transformer<I, T>>> {

        private final Iterator<String> names;

        private TransformerMapIterable(Iterator<String> names) {

            this.names = names;
        }

        @Override
        public Iterator<Entry<String, Transformer<I, T>>> iterator() {

            return new Iterator<Entry<String, Transformer<I, T>>>() {

                @Override
                public boolean hasNext() {

                    return names.hasNext();
                }

                @SuppressWarnings("unchecked")
                @Override
                public Entry<String, Transformer<I, T>> next() {

                    return new SimpleEntry<String, Transformer<I, T>>(names.next(), mock(Transformer.class));
                }

                @Override
                public void remove() {

                    names.remove();
                }
            };
        }
    }

    private static class HashCodeComparator implements Comparator {

        @Override
        public int compare(Object first, Object second) {

            return first.hashCode() - second.hashCode();
        }
    }
}
