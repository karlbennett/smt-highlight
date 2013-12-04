package shiver.me.timbers;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.NullTransformation.NULL_TRANSFORMATION;

/**
 * Utility methods and constants to help with creating tests.
 *
 * @author Karl Bennett
 */
public final class TestUtils {

    private TestUtils() {
    }

    public static final String Transformation_NAME_ONE = "one";
    public static final String Transformation_NAME_TWO = "two";
    public static final String Transformation_NAME_THREE = "three";


    public static Iterable createEmptyIterable() {

        Iterator iterator = mock(Iterator.class);

        Iterable emptyIterable = mock(Iterable.class);
        when(emptyIterable.iterator()).thenReturn(iterator);

        return emptyIterable;
    }

    /**
     * Assert that the returned {@link Transformation} is the {@link NullTransformation} for the supplied index.
     */
    public static void assertNullTransformation(Transformations transformations, int index) {

        assertEquals("the null Transformation should be returned for the index " + index, NULL_TRANSFORMATION,
                transformations.get(index));
    }

    /**
     * Assert that the returned {@link Transformation} is the {@link NullTransformation} for the supplied name.
     */
    public static void assertNullTransformation(Transformations transformations, String name) {

        assertEquals("the null Transformation should be returned for the name " + name, NULL_TRANSFORMATION,
                transformations.get(name));
    }
}
