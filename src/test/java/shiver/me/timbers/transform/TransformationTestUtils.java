package shiver.me.timbers.transform;

import shiver.me.timbers.transform.iterable.IterableTransformations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;
import static shiver.me.timbers.transform.TestUtils.mockMap;

/**
 * Utility methods and constants to help with creating {@code Transformation} tests.
 */
public final class TransformationTestUtils {

    private TransformationTestUtils() {
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

    @SuppressWarnings("unchecked")
    public static Map<String, String> mockNameMap() {


        return mockMap(new ArrayList<Entry<String, String>>() {{

            int number = 1;
            for (String name : NAMES) {

                add(new SimpleEntry<String, String>(name, String.valueOf(number++)));
            }
        }});
    }

    public static void assertNoIterations(Transformations<Transformation> transformations) {

        for (Transformation transformation : transformations) {

            fail("an empty " + Transformations.class.getSimpleName() + " should not iterate. Transformation: " +
                    transformation.getName());
        }
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
}
