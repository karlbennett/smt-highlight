package shiver.me.timbers.transform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.Assert.assertEquals;
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
            Arrays.fill(returns, true);
            returns[returns.length - 1] = false;

            when(iterator.hasNext()).thenReturn(true, returns);
        }

        when(iterator.next()).thenReturn(elements.get(0),
                elements.subList(1, elements.size()).toArray((T[]) new Object[elements.size() - 1]));

        final Iterable<T> transformationIterable = mock(Iterable.class);
        when(transformationIterable.iterator()).thenReturn(iterator);

        return transformationIterable;
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

    public static void assertNoIterations(Transformations<Transformation> transformations) {

        for (Transformation transformation : transformations) {

            fail("an empty " + Transformations.class.getSimpleName() + " should not iterate. Transformation: " +
                    transformation.getName());
        }
    }

    public static void assertTransformationsIndices(List<Transformation> transformationList,
                                                    Transformations transformations) {

        for (int i = 0; i < transformationList.size(); i++) {

            assertEquals("Transformation " + NAMES.get(i) + " should be returned for index " + i,
                    transformationList.get(i), transformations.get(i));
        }
    }

    public static void assertTransformationsNames(List<Transformation> transformationList,
                                                  Transformations<Transformation> transformations) {

        for (int i = 0; i < transformationList.size(); i++) {

            assertEquals("Transformation " + NAMES.get(i) + " should be returned for index " + i,
                    transformationList.get(i), transformations.get(NAMES.get(i)));
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

    public static void assertCollection(List<Transformation> transformationList,
                                        Collection<Transformation> transformations) {

        int i = 0;
        for (Transformation transformation : transformations) {

            assertEquals("Transformation " + NAMES.get(i) + " should be correct at index " + i,
                    transformationList.get(i), transformation);

            i++;
        }
    }
}
